package com.yanytskyi.dima.imagecast.presentation.image_picker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yanytskyi.dima.imagecast.R
import com.yanytskyi.dima.imagecast.databinding.FragmentImagePickerBinding
import dagger.hilt.android.AndroidEntryPoint
import org.checkerframework.checker.units.qual.s
import java.io.FileNotFoundException


@AndroidEntryPoint
class ImagePickerFragment : Fragment(R.layout.fragment_image_picker) {

    private lateinit var binding: FragmentImagePickerBinding
    private val viewModel: ImagePickerViewModel by viewModels()

    companion object {
        private const val IMAGE_PICKER_SELECT = 100
    }

    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagePickerBinding.bind(view)

        binding.pickImage.setOnClickListener {
            val photoPickerIntent = Intent(
                Intent.ACTION_PICK
            )
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, IMAGE_PICKER_SELECT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        when (requestCode) {
            IMAGE_PICKER_SELECT -> {
                if (resultCode == RESULT_OK) {
                    try {
                        val selectedImage = data?.data
                        val imageStream =
                            selectedImage?.let {
                                requireContext().contentResolver.openInputStream(
                                    it
                                )
                            }
                        val yourSelectedImage = BitmapFactory.decodeStream(imageStream)
                        if (selectedImage != null) {
                            selectedImage.path?.let { viewModel.saveImage(it, yourSelectedImage) }
                        }
                    } catch (e: FileNotFoundException) {
                        Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }

        }

    }
}