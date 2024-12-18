package org.example.project.data.model.meal


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MealResponse(
    @SerialName("meals")
    val meals: List<Meal>
)