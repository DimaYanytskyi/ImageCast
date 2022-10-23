package com.yanytskyi.dima.imagecast.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yanytskyi.dima.imagecast.presentation.cast.CastFragment
import com.yanytskyi.dima.imagecast.presentation.image_picker.ImagePickerFragment

class TabAdapter(
    fragment: FragmentActivity,
    private val count: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment {
        val fragment = when(position) {
            1 -> {
                CastFragment()
            }
            else -> ImagePickerFragment()
        }
        return fragment
    }
}