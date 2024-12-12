package com.example.bankprojectassignments.presentation

import android.app.Activity
import android.content.Context
import com.example.bankprojectassignments.R;
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bankprojectassignments.common.Resources
import com.example.bankprojectassignments.databinding.ActivityMainBinding
import com.example.bankprojectassignments.presentation.adapter.ScrollAdapter
import com.example.bankprojectassignments.presentation.adapter.SliderAdapter
import com.example.bankprojectassignments.presentation.util.RecyclerViewIndicatorManager
import com.example.bankprojectassignments.presentation.util.showBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ImagesViewModel>()
    private lateinit var binding: ActivityMainBinding
    private val sliderAdapter = SliderAdapter()
    private val scrollAdapter = ScrollAdapter()
    private val snapHelper = LinearSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getImagesSlider("cat")
        viewModel.getImagesScroll("dog")
        setupRecyclerView()

        viewModel.imagesSlider.observe(this) {
            binding.sliderRV.isVisible = it is Resources.Success || !it.data.isNullOrEmpty()
            it.data?.let { images ->
                sliderAdapter.submitList(images)
                setIndicatorView(it.data.size)
            }
        }

        viewModel.imageScroll.observe(this) {
            binding.scrollRv.isVisible = it is Resources.Success || !it.data.isNullOrEmpty()
            it.data?.let { images ->
                scrollAdapter.submitList(it.data)
            }
        }


        binding.SearchEdit.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val query = binding.SearchEdit.text.toString()
                if (query.isNotBlank())
                    viewModel.getImagesScroll(query = query)
                hideKeyboard()
                true
            } else {
                false
            }
        }

        binding.floatingActionButton.setOnClickListener {
            showBottomSheetDialog(
                viewModel.imagesSlider.value?.data ?: emptyList(),
                viewModel.imageScroll.value?.data ?: emptyList()
            )
        }
    }

    fun setupRecyclerView() {
        binding.sliderRV.adapter = sliderAdapter
        snapHelper.attachToRecyclerView(binding.sliderRV)

        binding.scrollRv.adapter = scrollAdapter
    }

    fun setIndicatorView(itemsCount: Int) {
        val indicator = RecyclerViewIndicatorManager(
            context = this,
            indicatorLayout = binding.indicatorView,
            selectedDrawableRes = R.drawable.indicator_selected,
            unSelectedDrawableRes = R.drawable.indicator_unseleted
        )

        indicator.setupIndicators(itemsCount)

        // Attach Scroll Listener
        binding.sliderRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                indicator.updateIndicators(position)
            }
        })
    }

    private fun hideKeyboard() {
        val inputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val view = this.currentFocus
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}