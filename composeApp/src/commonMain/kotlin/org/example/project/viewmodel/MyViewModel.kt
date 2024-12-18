package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.model.post.PostResponseItem
import org.example.project.data.network.clint.NetworkClint
import org.example.project.data.network.repository.MealRepository
import org.example.project.data.network.repository.Repository
import org.example.project.utils.NetworkResult


private const val TAG = "MyViewModel"
class MyViewModel(
    private val repository: Repository = NetworkClint.repository,
    private val mealRep: MealRepository = MealRepository(NetworkClint.httpClint)
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

       getMealList("Seafood")

   }

    //todo meal repository imp
    val stateFlowMealRep = mealRep.stateFlow
    fun getMealList(search : String){
        try {
            viewModelScope.launch(Dispatchers.Default) {
                mealRep.getMeals(search = search)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
    //todo meal repository imp
}