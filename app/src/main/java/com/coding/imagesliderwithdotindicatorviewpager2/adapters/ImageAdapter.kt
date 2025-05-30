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
import com.coding.imagesliderwithdotindicatorviewpager2.models.ImageItem

class ImageAdapter  (private val items: List<ImageItem>) : RecyclerView.Adapter<ImageAdapter.ItemViewHolder>() {

    // ViewHolder class
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val titleView: TextView = itemView.findViewById(R.id.item_title)
        val subtitleView: TextView = itemView.findViewById(R.id.item_subtitle)
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
    }

    override fun getItemCount(): Int {
        return items.size
    }
}