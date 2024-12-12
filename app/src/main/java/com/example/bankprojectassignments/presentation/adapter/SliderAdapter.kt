package com.example.bankprojectassignments.presentation.adapter

import com.example.bankprojectassignments.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bankprojectassignments.databinding.SliderItemBindingImpl
import com.example.bankprojectassignments.domain.model.Image
import com.example.bankprojectassignments.presentation.adapter.SliderAdapter.ImagesViewHolder


class SliderAdapter() : ListAdapter<Image, ImagesViewHolder>(DiffImageObject) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = SliderItemBindingImpl.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding as SliderItemBindingImpl)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImagesViewHolder(private val binding: SliderItemBindingImpl) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {

            Glide.with(binding.root.context)
                .load(image.imageUrl)
                .placeholder(R.drawable.gray_place_holder)
                .into(binding.image)

        }
    }
}
