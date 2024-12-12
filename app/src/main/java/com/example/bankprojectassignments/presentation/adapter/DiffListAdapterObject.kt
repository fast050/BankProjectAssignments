package com.example.bankprojectassignments.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.bankprojectassignments.domain.model.Image

object DiffImageObject: DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
        oldItem == newItem
}
