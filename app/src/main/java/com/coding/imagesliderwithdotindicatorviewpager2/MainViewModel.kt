package com.coding.imagesliderwithdotindicatorviewpager2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.imagesliderwithdotindicatorviewpager2.adapters.HourlyWeatherAdapter
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _likesUpdates = MutableLiveData<List<HourlyWeatherAdapter.HourlyWeather>>()
    val likesUpdates: LiveData<List<HourlyWeatherAdapter.HourlyWeather>> = _likesUpdates


    fun updateLikes(updatedItems: List<HourlyWeatherAdapter.HourlyWeather>) {
        _likesUpdates.value = updatedItems
        // Aquí podrías llamar a tu servicio para guardar los cambios
        // saveLikesToService(updatedItems)
    }


    private fun saveLikesToService(items: List<HourlyWeatherAdapter.HourlyWeather>) {
        viewModelScope.launch {
            try {
                // Aquí iría tu llamada al servicio real
                // service.updateLikes(items)
            } catch (e: Exception) {
                // Manejo de errores
            }
        }
    }



    data class HourlyWeatherLikeUpdate(
        val id: Long,
        val likes: Int,
        val isLiked: Boolean
    )
}