package models.response.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("error")
    val error: String,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result,
    @SerialName("token")
    val token: String
) {
    @Serializable
    data class Result(
        @SerialName("dob")
        val dob: String,

        @SerialName("email")
        val email: String,

        @SerialName("firstName")
        val firstName: String,

        @SerialName("gender")
        val gender: String,

        @SerialName("id")
        val id: Int,

        @SerialName("lastName")
        val lastName: String,

        @SerialName("mobileNumber")
        val mobileNumber: String,

        @SerialName("profilePicturePath")
        val profilePicturePath: String,
    )
}