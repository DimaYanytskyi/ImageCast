package com.yanytskyi.dima.imagecast.presentation.cast

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanytskyi.dima.imagecast.data.ResultWrapper
import com.yanytskyi.dima.imagecast.domain.interactor.IImageInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val imageInteractor: IImageInteractor,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _image = MutableStateFlow(CastState())
    val image: StateFlow<CastState> = _image

    init {
        getImage()
    }

    private fun getImage() {
        viewModelScope.launch {
            imageInteractor.fetchImage().collect { result ->
                when(result) {
                    is ResultWrapper.Loading -> {
                        _image.value = CastState(loading = true)
                    }
                    is ResultWrapper.Success -> {
                        val image = result.data
                        val bitmap: Bitmap = if(image?.localImagePath != ""){
                            val inputStream: InputStream? = image?.localImagePath?.toUri()
                                ?.let { context.contentResolver.openInputStream(it) }
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            inputStream?.close()
                            bitmap
                        } else {
                            image.imageBitmap
                        }
                        _image.value = CastState(image = bitmap)
                    }
                    is ResultWrapper.Error -> {
                        _image.value = CastState(message = result.message ?: "An unexpected error was occurred")
                    }
                }
            }
        }
    }
}