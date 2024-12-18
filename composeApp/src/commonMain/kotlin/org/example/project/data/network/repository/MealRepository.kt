package org.example.project.data.network.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.buildUrl
import io.ktor.http.path
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.data.model.meal.MealResponse
import org.example.project.utils.NetworkResult

class MealRepository (
    private val clint : HttpClient
){

    private val mutableStateFlow = MutableStateFlow<NetworkResult<MealResponse>>(NetworkResult.Empty())
    val stateFlow = mutableStateFlow.asStateFlow()

    suspend fun getMeals(search : String){
        mutableStateFlow.emit(NetworkResult.Loading())
        try {
            val response = clint.get(
                buildUrl {
                    protocol = URLProtocol.HTTPS
                    host= "themealdb.com/"
                    path("api/json/v1/1/filter.php")
                    parameters.append("c", search)
                }
            )

            if(response.status == HttpStatusCode.OK){
                val data : MealResponse = response.body()
                mutableStateFlow.emit(NetworkResult.Success(data = data))
            }else {
                mutableStateFlow.emit(NetworkResult.Error(message = "Somethings went wrong data can't fetch please try again"))
            }

        }catch (e : Exception){
            mutableStateFlow.emit(NetworkResult.Error(message = "Somethings went wrong please try again"))
        }
    }
}