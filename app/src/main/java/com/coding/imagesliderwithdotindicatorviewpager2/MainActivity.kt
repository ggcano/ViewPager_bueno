package com.coding.imagesliderwithdotindicatorviewpager2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.imagesliderwithdotindicatorviewpager2.adapters.HourlyWeatherAdapter
import com.coding.imagesliderwithdotindicatorviewpager2.databinding.ActivityMainBinding
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shimmerViewContainer = findViewById<ShimmerFrameLayout>(R.id.shimmerViewContainer)
        val contentLayout = findViewById<LinearLayout>(R.id.contentLayout)
        val recyclerView = findViewById<RecyclerView>(R.id.hourlyRecyclerView)

        // Iniciar animación Shimmer
        shimmerViewContainer.startShimmer()

        // Simular carga de datos (en una app real sería una llamada a API o base de datos)
        Handler(Looper.getMainLooper()).postDelayed({
            // Detener Shimmer y mostrar contenido real
            shimmerViewContainer.stopShimmer()
            shimmerViewContainer.visibility = View.GONE
            contentLayout.visibility = View.VISIBLE

            // Configurar RecyclerView con datos reales
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
                )

                // ... resto de tus datos ...
            )

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = HourlyWeatherAdapter(hourlyData)

        }, 2000) // 2 segundos de simulación de carga
    }

    override fun onPause() {
        super.onPause()
        findViewById<ShimmerFrameLayout>(R.id.shimmerViewContainer)?.stopShimmer()
    }
}