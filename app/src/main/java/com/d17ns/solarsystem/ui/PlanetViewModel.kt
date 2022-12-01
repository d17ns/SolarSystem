package com.d17ns.solarsystem.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d17ns.solarsystem.network.Planet
import com.d17ns.solarsystem.network.PlanetApi
import kotlinx.coroutines.launch

// enum class PlanetApiStatus untuk menampilkan status request ke user
enum class PlanetApiStatus { LOADING, ERROR, DONE }

/*
 * class PlanetViewModel sebagai ViewModel yang mencakup
 * variabel-variabel LiveData dan implementasi PlanetAPiStatus
 */
class PlanetViewModel : ViewModel() {

    // LiveData variabel _status untuk menampilkan status dari PlanetApiStatus
    private val _status = MutableLiveData<PlanetApiStatus>()
    val status: LiveData<PlanetApiStatus> = _status

    // LiveData variabel _planets untuk menampilkan list data planet
    private val _planets = MutableLiveData<List<Planet>>()
    val planets: LiveData<List<Planet>> = _planets

    // LiveData variabel _planet untuk detail informasi dari setiap planet
    private val _planet = MutableLiveData<Planet>()
    val planet: LiveData<Planet> = _planet

    /*
     * method untuk mengambil list data planet yang ada dari file JSON menggunakan
     * LiveData variabel _planets dan implementasi LiveData variabel _status untuk
     * menampilkan status request ke user
     */
    fun getPlanetList() {
        _status.value = PlanetApiStatus.LOADING
        viewModelScope.launch {
            try {
                _planets.value = PlanetApi.retrofitService.getPlanets()
                _status.value = PlanetApiStatus.DONE
            } catch (e: Exception) {
                _planets.value = emptyList()
                _status.value = PlanetApiStatus.ERROR
            }
        }
    }

    /*
     * method untuk menampilkan detail informasi dari planet yang
     * diklik / dipilih dari list dengan memanggil LiveData variabel _planet
     */
    fun onPlanetClicked(planet: Planet) {
        _planet.value = planet
    }
}