package com.yanytskyi.dima.imagecast.presentation.cast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yanytskyi.dima.imagecast.R
import com.yanytskyi.dima.imagecast.databinding.FragmentCastBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CastFragment : Fragment(R.layout.fragment_cast) {

    private lateinit var binding: FragmentCastBinding
    private val viewModel: CastViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCastBinding.bind(view)

        lifecycleScope.launch {
            viewModel.image.collect {
                binding.progressBar.visibility = if(it.loading) View.VISIBLE else View.GONE
                binding.castImage.setImageBitmap(it.image)
                if(it.message != "") {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getImage()
    }
}