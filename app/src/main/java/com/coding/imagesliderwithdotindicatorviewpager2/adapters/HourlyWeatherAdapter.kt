package com.coding.imagesliderwithdotindicatorviewpager2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coding.imagesliderwithdotindicatorviewpager2.R

class HourlyWeatherAdapter(
    private val hourlyItems: List<HourlyWeather>
) : RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hourText: TextView = itemView.findViewById(R.id.hourText)
        val conditionText: TextView = itemView.findViewById(R.id.hourCondition)
        val rainText: TextView = itemView.findViewById(R.id.rainProbability)
        val tempText: TextView = itemView.findViewById(R.id.hourTemp)
        val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = hourlyItems[position]

        holder.hourText.text = item.hour
        holder.conditionText.text = item.condition
        holder.tempText.text = item.temperature

        // Configurar icono (todos usan el mismo)
        holder.weatherIcon.setImageResource(item.iconRes)

        if(item.rainProbability.isEmpty()) {
            holder.rainText.visibility = View.GONE
        } else {
            holder.rainText.visibility = View.VISIBLE
            holder.rainText.text = item.rainProbability
        }
    }

    override fun getItemCount() = hourlyItems.size

    data class HourlyWeather(
        val hour: String,
        val condition: String,
        val rainProbability: String,
        val temperature: String,
        val iconRes: Int // Nuevo campo para el recurso del icono
    )
}