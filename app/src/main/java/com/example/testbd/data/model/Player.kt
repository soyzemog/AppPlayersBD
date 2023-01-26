package com.example.testbd.data.model

data class Player(
    val id: Int,
    val surname: String,
    val nationality: String?,
    val team: List<String>?,
    val position: List<String>?,
    val wear: List<Int>?
)
