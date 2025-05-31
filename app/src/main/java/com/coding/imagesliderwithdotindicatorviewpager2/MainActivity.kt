package com.coding.imagesliderwithdotindicatorviewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.imagesliderwithdotindicatorviewpager2.adapters.ImageAdapter
import com.coding.imagesliderwithdotindicatorviewpager2.databinding.ActivityMainBinding
import com.coding.imagesliderwithdotindicatorviewpager2.models.Category
import com.coding.imagesliderwithdotindicatorviewpager2.models.ImageItem
import com.coding.imagesliderwithdotindicatorviewpager2.models.VideoObjectResponse

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val categorylist = listOf(
            Category(
                "pepe",
                "Descrripcion de Categoria Pepe",
                "text 1"
            ),
            Category(
                "pepe",
                "Des cat pepe 2",
                "text 1",
            ),
            Category(
                "paco",
                "Paco Description",
                "Paco 1",

                ),
            Category(
                "pepe",
                "pajsdjdasjkdsajk;ldasjk;adsjk;dasja",
                "Pepe",

                ),
            Category(
                "paco",
                "des pacoooo",
                "text 5",

                ),
            Category(
                "otro",
                "des otro",
                "Otro",

                )

        )
        val videosList = listOf(
            VideoObjectResponse(
                "pepe",
                "https://fastly.picsum.photos/id/778/500/500.jpg?hmac=jZLZ6WV_OGRxAIIYPk7vGRabcAGAILzxVxhqSH9uLas"
            ),
            VideoObjectResponse(
                "pepe",
                "https://fastly.picsum.photos/id/95/500/500.jpg?hmac=0aldBQ7cQN5D_qyamlSP5j51o-Og4gRxSq4AYvnKk2U"
            ),
            VideoObjectResponse(
                "otro",
                "https://fastly.picsum.photos/id/798/500/500.jpg?hmac=Bmzk6g3m8sUiEVHfJWBscr2DUg8Vd2QhN7igHBXLLfo"
            ),
            VideoObjectResponse(
                "otro",
                "https://fastly.picsum.photos/id/798/500/500.jpg?hmac=Bmzk6g3m8sUiEVHfJWBscr2DUg8Vd2QhN7igHBXLLfo"
            ),
            VideoObjectResponse(
                "paco",
                "https://fastly.picsum.photos/id/320/500/500.jpg?hmac=2iE7TIF9kIqQOHrIUPOJx2wP1CJewQIZBeMLIRrm74s"
            ),
        )
        val list: List<ImageItem> = combineLists(categorylist,videosList)
        setupRyclerView(list,videosList)


    }

    private fun setupRyclerView(exampleList: List<ImageItem>,videoList: List<VideoObjectResponse>,) {


        // LayoutManager horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager


        val adapter = ImageAdapter(exampleList,videoList)
        binding.recyclerView.adapter = adapter

    }

    fun combineLists(categories: List<Category>, videos: List<VideoObjectResponse>): List<ImageItem> {
        // Agrupar los videos por ID para un acceso más eficiente
        val videosByCategoryId = videos.groupBy { it.id }
        // Procesar las categorías
        return categories.distinctBy { it.id }.map { category ->
            val videoForCategory = videosByCategoryId[category.id]?.firstOrNull()

            ImageItem(
                id = category.id,
                url = videoForCategory?.url ?: "", // Usar URL del video o cadena vacía si no hay
                title = category.title,
                description = category.description
            )
        }
    }


}