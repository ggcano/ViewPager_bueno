package com.coding.imagesliderwithdotindicatorviewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.imagesliderwithdotindicatorviewpager2.adapters.HourlyWeatherAdapter
import com.coding.imagesliderwithdotindicatorviewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Datos de ejemplo
        val recyclerView = findViewById<RecyclerView>(R.id.hourlyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) // Vertical por defecto

        // Datos de ejemplo con el mismo icono para todos
        val hourlyData = listOf(
            HourlyWeatherAdapter.HourlyWeather(
                "Ahora",
                "Parcialmente lluvioso",
                "Probabilidad de lluvia: 25%",
                "24°",
                R.drawable.ic_launcher_background
            ),
            HourlyWeatherAdapter.HourlyWeather(
                "19:00",
                "Lluvia",
                "Probabilidad de lluvia: 60%",
                "24°",
                R.drawable.baseline_grain_24
            ),
            HourlyWeatherAdapter.HourlyWeather(
                "20:00h",
                "Mayormente nublado",
                "",
                "24°",
                R.drawable.baseline_sunny_24
            ),
            // Resto de items con el mismo icono...
        )

        recyclerView.adapter = HourlyWeatherAdapter(hourlyData)
    }

}