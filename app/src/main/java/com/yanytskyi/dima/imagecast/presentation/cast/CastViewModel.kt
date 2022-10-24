package com.yanytskyi.dima.imagecast.presentation.cast

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanytskyi.dima.imagecast.R
import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.domain.interactor.IImageInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class CastViewModel @Inject constructor(
    private val imageInteractor: IImageInteractor,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _image = MutableStateFlow(CastState())
    val image = _image.asStateFlow()

    fun getImage() {
        viewModelScope.launch {
            imageInteractor.fetchImage().collect { result ->
                when(result) {
                    is ResultWrapper.Loading -> {
                        _image.value = CastState(loading = true)
                    }
                    is ResultWrapper.Success -> {
                        val image = result.data
                        val bitmap: Bitmap = if(image?.imageBitmap != null){
                            image.imageBitmap
                        } else {
                            BitmapFactory.decodeResource(context.resources, R.drawable.no_image)
                        }
                        _image.value = CastState(image = bitmap)
                    }
                    is ResultWrapper.Error -> {
                        _image.value = CastState(
                            message = result.message ?: "An unexpected error was occurred",
                            image = BitmapFactory.decodeResource(context.resources, R.drawable.no_image)
                        )
                    }
                }
            }
        }
    }
}