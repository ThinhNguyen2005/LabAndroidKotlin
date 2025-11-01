package com.hoangiathinh.smarttasks.model

import kotlinx.serialization.SerialName // Thêm import này
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("ID") // Ánh xạ "ID" từ JSON vào thuộc tính "id"
    val id: String,

    @SerialName("Name") // Ánh xạ "Name" từ JSON vào thuộc tính "name"
    val name: String,

    @SerialName("Price") // Ánh xạ "Price" từ JSON vào thuộc tính "price"
    val price: String,

    @SerialName("Description") // Ánh xạ "Description" từ JSON vào thuộc tính "description"
    val description: String,

    @SerialName("ImageURL") // Ánh xạ "ImageURL" từ JSON vào thuộc tính "imageUrl"
    val imageUrl: String
)