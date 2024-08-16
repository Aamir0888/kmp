package repository

import models.response.meal.MealItem
import models.response.meal.Meals
import models.response.post.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import models.request.login.LoginRequest
import models.response.login.LoginResponse
import utils.Response

class Repository(private val client: HttpClient) {
    fun fetchMeals(location: String = "Indian"): Flow<Response<Meals>> = flow {
        emit(Response.Loading())
        val mealDto = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "themealdb.com"
                path("api/json/v1/1/filter.php")
                parameters.append("a", location)
            }
        }.body<Meals>()
        emit(Response.Success(mealDto))
    }.catch { error ->
        emit(Response.Error(error))
    }

    fun fetchMealById(id: String): Flow<Response<MealItem>> = flow {
        emit(Response.Loading())
        val mealDto = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "themealdb.com"
                path("api/json/v1/1/lookup.php")
                parameters.append("i", id)
            }
        }.body<MealItem>()
        emit(Response.Success(mealDto))
    }.catch { error ->
        emit(Response.Error(error))
    }

    fun getPostList(): Flow<Response<List<Post>>> = flow {
        emit(Response.Loading())
        val postList = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "jsonplaceholder.typicode.com"
                path("posts")
            }
        }.body<List<Post>>()
        emit(Response.Success(postList))
    }.catch {
        emit(Response.Error(it))
    }

    fun login(loginRequest: LoginRequest): Flow<Response<LoginResponse>> = flow {
        emit(Response.Loading())
        val loginResponse = client.post {
            url {
                protocol = URLProtocol.HTTPS
                host = "hreactive.teckhubsolutions.com"
                path("employee/signIn")
            }
            setBody(loginRequest)
        }.body<LoginResponse>()
        emit(Response.Success(loginResponse))
    }.catch {
        emit(Response.Error(it))
    }
}