package com.example.bankprojectassignments.presentation.adapter

import com.example.bankprojectassignments.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bankprojectassignments.databinding.ScrollItemBindingImpl
import com.example.bankprojectassignments.domain.model.Image
import com.example.bankprojectassignments.presentation.adapter.ScrollAdapter.ScrollItemsHolder


class ScrollAdapter() : ListAdapter<Image, ScrollItemsHolder>(DiffImageObject) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrollItemsHolder {
        val binding = ScrollItemBindingImpl.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScrollItemsHolder(binding as ScrollItemBindingImpl)
    }

    override fun onBindViewHolder(holder: ScrollItemsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ScrollItemsHolder(private val binding: ScrollItemBindingImpl) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {

            binding.titleTv.text = image.title ?: "Title"
            binding.subTitleTv.text = image.subTitle ?: "SubTitle"

            Glide.with(binding.root.context)
                .load(image.imageUrl)
                .placeholder(R.drawable.gray_place_holder)
                .into(binding.imageIv)

        }
    }
}
