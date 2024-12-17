package org.example.project.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Meals(
    @SerialName("meals")
    val meals: List<Meal>
)