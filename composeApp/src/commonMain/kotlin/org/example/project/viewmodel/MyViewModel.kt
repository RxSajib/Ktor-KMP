package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.model.post.PostResponseItem
import org.example.project.data.network.clint.NetworkClint
import org.example.project.data.network.model.Meals
import org.example.project.data.network.repository.Repository
import org.example.project.utils.NetworkResult
import kotlin.math.log


private const val TAG = "MyViewModel"
class MyViewModel constructor(
    private val repository : Repository = NetworkClint.repository
): ViewModel() {

    private val mutableStateFlow  = MutableStateFlow<NetworkResult<List<PostResponseItem>>>(NetworkResult.Empty())
    val stateFlow = mutableStateFlow.asStateFlow()

   init {
       viewModelScope.launch {
           repository.fetchMeals("Canadian").collect{
                when(it){
                    is NetworkResult.Success -> {
                        mutableStateFlow.emit(NetworkResult.Success(data = it.data!!))
                        print(it.data)
                    }
                    is NetworkResult.Error -> {
                        print(it.message)
                        mutableStateFlow.emit(NetworkResult.Error(message = "No data found"))
                    }
                    is NetworkResult.Loading -> {
                        print("loading")
                        mutableStateFlow.emit(NetworkResult.Loading())
                    }
                    is NetworkResult.Empty -> {

                    }
                }
           }
       }

   }
}