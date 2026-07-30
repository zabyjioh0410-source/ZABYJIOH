package com.example.ecochoice

data class EcoProduct(
    val name: String,
    val description: String,
    val imageResId: Int,
    val ecoScore: Int // Рейтинг экологичности от 0 до 100
)
