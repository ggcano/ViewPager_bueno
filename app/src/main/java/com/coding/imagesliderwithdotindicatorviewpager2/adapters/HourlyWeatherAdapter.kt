package com.coding.imagesliderwithdotindicatorviewpager2.adapters

import android.app.AlertDialog
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
        val commentText: TextView = itemView.findViewById(R.id.commentText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = hourlyItems[position]
        val context = holder.itemView.context

        holder.hourText.text = item.hour
        holder.conditionText.text = item.condition
        holder.tempText.text = item.likes
        holder.weatherIcon.setImageResource(item.iconRes)

        // Configurar visibilidad del comentario
        holder.commentText.visibility = if (item.showComment) View.VISIBLE else View.GONE

        // Configurar el icono de like según el estado
        val likeIconRes = if (item.isLiked) {
            R.drawable.baseline_sunny_24
        } else {
            R.drawable.baseline_grain_24
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

            val newList = hourlyItems.toMutableList()
            newList[position] = item.copy(
                likes = newLikes.toString(),
                isLiked = !item.isLiked
            )
            hourlyItems = newList
            notifyItemChanged(position)
        }

        // Configurar el clic en hourText para mostrar/ocultar comentario
        holder.hourText.setOnClickListener {
            val newList = hourlyItems.toMutableList()
            newList[position] = item.copy(
                showComment = !item.showComment
            )
            hourlyItems = newList
            notifyItemChanged(position)
        }

        // Configurar el clic largo en el comentario para eliminarlo
        holder.commentText.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Eliminar comentario")
                .setMessage("¿Quieres eliminar este comentario?")
                .setPositiveButton("Eliminar") { _, _ ->
                    // Actualizar la lista para ocultar el comentario
                    val newList = hourlyItems.toMutableList()
                    newList[position] = item.copy(
                        showComment = false
                    )
                    hourlyItems = newList
                    notifyItemChanged(position)
                }
                .setNegativeButton("Cancelar", null)
                .show()
            true
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
        var isLiked: Boolean = false,
        var showComment: Boolean = false
    )
}