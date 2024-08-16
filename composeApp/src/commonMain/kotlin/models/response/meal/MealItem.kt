package models.response.meal


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealItem(
    @SerialName("meals")
    val meals: List<MealX>
)