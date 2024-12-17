package org.example.project.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MealsX(
    @SerialName("meals")
    val meals: List<MealX>
)