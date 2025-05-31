package com.coding.imagesliderwithdotindicatorviewpager2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.coding.imagesliderwithdotindicatorviewpager2.R
import com.coding.imagesliderwithdotindicatorviewpager2.models.Category
import com.coding.imagesliderwithdotindicatorviewpager2.models.ImageItem
import com.coding.imagesliderwithdotindicatorviewpager2.models.VideoObjectResponse

class ImageAdapter(
    private val items: List<ImageItem>,
    private val videosList: List<VideoObjectResponse>
) : RecyclerView.Adapter<ImageAdapter.ItemViewHolder>() {
    private var conteoVideos: Map<String, Int> = emptyMap()

    // ViewHolder class
    init {
        calcularConteo()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val titleView: TextView = itemView.findViewById(R.id.item_title)
        val subtitleView: TextView = itemView.findViewById(R.id.item_subtitle)
        val blackVideos: TextView = itemView.findViewById(R.id.textView_black_size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(holder.imageView.context)
            .load(currentItem.url) // o currentItem.imageUrl si usas URLs
            .centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView)

        holder.titleView.text = currentItem.title
        holder.subtitleView.text = currentItem.description
        val cantidad = conteoVideos[currentItem.id] ?: 0
        holder.blackVideos.text = cantidad.toString() + " Videos"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun contarIdsVideos(videos: List<Category>): Map<String, Int> {
        return videos.groupingBy { it.id }.eachCount()
    }

    fun formatearConteo(conteo: Map<String, Int>): String {
        return conteo.entries.joinToString(", ") { "${it.value} ${it.key}" }
    }

    private fun calcularConteo() {
        conteoVideos = videosList.groupingBy { it.id }.eachCount()
    }

}