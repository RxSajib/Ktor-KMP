package org.example.project.data.network.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.example.project.data.datamanager.DataManager
import org.example.project.data.model.post.PostResponseItem
import org.example.project.data.network.model.MealX
import org.example.project.data.network.model.Meals
import org.example.project.utils.NetworkResult

private const val TAG = "Repository"
class Repository(
    private val clint : HttpClient
) {

    fun fetchMeals(location : String) : Flow<NetworkResult<List<PostResponseItem>>> =
        flow {
            emit(NetworkResult.Loading())
           val response =  clint.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = DataManager.BASEURL
                    path(DataManager.POST)
                }
            }.body<List<PostResponseItem>>()
            emit(NetworkResult.Success(response))


        }.catch {
            emit(NetworkResult.Error(message = "No data found"))
        }

    fun getSingleMeals(id : String) : Flow<NetworkResult<MealX>> =
        flow {
            emit(NetworkResult.Loading())
            val response = clint.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = DataManager.BASEURL
                    path(DataManager.MEAL_DETAILS)
                    parameter("i", id)
                }
            }.body<MealX>()
            emit(NetworkResult.Success(response))
        }.catch {
            emit(NetworkResult.Error("No data found"))
        }
}