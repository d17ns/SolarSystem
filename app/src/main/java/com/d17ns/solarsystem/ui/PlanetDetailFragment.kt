package com.d17ns.solarsystem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.d17ns.solarsystem.databinding.FragmentPlanetDetailBinding

// class PlanetDetailFragment yang akan menampilkan detail informasi dari setiap planet
class PlanetDetailFragment : Fragment() {

    private val viewModel: PlanetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPlanetDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Inflate the layout for this fragment
        return binding.root
    }
}