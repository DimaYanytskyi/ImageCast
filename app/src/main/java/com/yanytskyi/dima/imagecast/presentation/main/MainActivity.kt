package com.yanytskyi.dima.imagecast.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.yanytskyi.dima.imagecast.databinding.ActivityMainBinding
import com.yanytskyi.dima.imagecast.presentation.cast.CastFragment
import com.yanytskyi.dima.imagecast.presentation.image_picker.ImagePickerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
            ImagePickerFragment(),
            CastFragment()
        )
        val tabAdapter = TabAdapter(supportFragmentManager, fragments, listOf("Image Picker", "Cast Image"))
        binding.viewPager.adapter = tabAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}