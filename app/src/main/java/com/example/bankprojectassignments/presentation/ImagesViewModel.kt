package com.example.bankprojectassignments.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankprojectassignments.common.Resources
import com.example.bankprojectassignments.domain.model.Image
import com.example.bankprojectassignments.domain.repository.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ImagesViewModel @Inject constructor(private val imagesRepository: ImagesRepository) :
    ViewModel() {

    private val _imagesSlider:MutableLiveData<Resources<List<Image>>> = MutableLiveData()
    val imagesSlider : LiveData<Resources<List<Image>>> = _imagesSlider

    private val _imageScroll:MutableLiveData<Resources<List<Image>>> = MutableLiveData()
    val imageScroll : LiveData<Resources<List<Image>>> = _imageScroll

    fun getImagesSlider(query:String, itemNumber :Int = 10) = viewModelScope.launch{
         imagesRepository.getImages(query , itemNumber).collectLatest {
             _imagesSlider.value = it
         }
    }

    fun getImagesScroll(query:String, itemNumber :Int = 15) = viewModelScope.launch{
        imagesRepository.getImages(query , itemNumber).collectLatest {
            _imageScroll.value = it
        }
    }
}
