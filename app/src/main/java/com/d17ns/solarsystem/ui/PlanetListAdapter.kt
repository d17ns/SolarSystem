package com.d17ns.solarsystem.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d17ns.solarsystem.databinding.ListViewItemBinding
import com.d17ns.solarsystem.network.Planet

/*
 * class PlanetListAdapter sebagai adapter yang akan menampilkan List data
 * dengan menggunakan RecyclerView dan Data Binding
 */
class PlanetListAdapter(val clickListener: PlanetListener) :
    ListAdapter<Planet, PlanetListAdapter.PlanetViewHolder>(DiffCallback) {

    class PlanetViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: PlanetListener, planet: Planet) {
            binding.planet = planet
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Planet>() {

        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem.description == newItem.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlanetViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet = getItem(position)
        holder.bind(clickListener, planet)
    }
}

// class PlanetListener yang akan menampilkan detail informasi dari planet yang diklik
class PlanetListener(val clickListener: (planet: Planet) -> Unit) {
    fun onClick(planet: Planet) = clickListener(planet)
}

