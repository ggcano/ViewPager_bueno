package com.coding.imagesliderwithdotindicatorviewpager2.models

import java.io.Serializable

data class ImageItem(
    val id: String,
    val url: String,
    val title: String,
    val description: String
) : Serializable