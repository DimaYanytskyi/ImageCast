package com.yanytskyi.dima.imagecast.presentation.image_picker

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanytskyi.dima.imagecast.domain.interactor.IImageInteractor
import com.yanytskyi.dima.imagecast.domain.model.Cast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePickerViewModel @Inject constructor(
    private val imageInteractor: IImageInteractor,
    private val imagePath: String
) : ViewModel() {

    fun saveImage(path: String, bitmap: Bitmap) {
        viewModelScope.launch {
            val cast = Cast(
                path,
                imagePath,
                bitmap
            )
            imageInteractor.updateImage(cast)
        }
    }
}