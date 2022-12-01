package com.d17ns.solarsystem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.d17ns.solarsystem.R
import com.d17ns.solarsystem.databinding.FragmentPlanetListBinding


// class PlanetListFragment sebagai fragment untuk menampilkan list data planet
class PlanetListFragment : Fragment() {

    private val viewModel: PlanetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val binding = FragmentPlanetListBinding.inflate(inflater)
        viewModel.getPlanetList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PlanetListAdapter(PlanetListener { planet ->
            viewModel.onPlanetClicked(planet)
            findNavController()
                .navigate(R.id.action_planetListFragment_to_planetDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}