package com.coding.imagesliderwithdotindicatorviewpager2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coding.imagesliderwithdotindicatorviewpager2.R

class HourlyWeatherAdapter(
    private var hourlyItems: List<HourlyWeather>
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
        holder.tempText.text = item.likes
        holder.weatherIcon.setImageResource(item.iconRes)

        // Configurar el icono de like según el estado
        val likeIconRes = if (item.isLiked) {
            R.drawable.baseline_sunny_24 // Icono cuando está likeado
        } else {
            R.drawable.baseline_grain_24 // Icono normal
        }
        holder.weatherIcon.setImageResource(likeIconRes)

        if (item.rainProbability.isEmpty()) {
            holder.rainText.visibility = View.GONE
        } else {
            holder.rainText.visibility = View.VISIBLE
            holder.rainText.text = item.rainProbability
        }

        // Configurar el clic en iconRest
        holder.weatherIcon.setOnClickListener {
            val currentLikes = item.likes.toIntOrNull() ?: 0
            val newLikes = if (item.isLiked) {
                currentLikes - 1
            } else {
                currentLikes + 1
            }

            // Actualizar la lista
            val newList = hourlyItems.toMutableList()
            newList[position] = item.copy(
                likes = newLikes.toString(),
                isLiked = !item.isLiked
            )
            hourlyItems = newList

            // Notificar el cambio
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = hourlyItems.size

    data class HourlyWeather(
        val hour: String,
        val condition: String,
        val id: Long,
        val rainProbability: String,
        val likes: String,
        val iconRes: Int,
        var isLiked: Boolean = false // Nuevo campo para estado de like
    )
}