package com.yanytskyi.dima.imagecast.presentation.image_picker

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yanytskyi.dima.imagecast.R
import com.yanytskyi.dima.imagecast.databinding.FragmentImagePickerBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileNotFoundException

@AndroidEntryPoint
class ImagePickerFragment : Fragment(R.layout.fragment_image_picker) {

    private lateinit var binding: FragmentImagePickerBinding
    private val viewModel: ImagePickerViewModel by viewModels()

    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagePickerBinding.bind(view)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
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

        binding.pickImage.setOnClickListener {
            val photoPickerIntent = Intent(
                Intent.ACTION_PICK
            )
            photoPickerIntent.type = "image/*"
            resultLauncher.launch(photoPickerIntent)
        }
    }
}