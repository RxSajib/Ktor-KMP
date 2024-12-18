package org.example.project.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.component.FoodItem
import org.example.project.data.model.meal.MealResponse
import org.example.project.utils.NetworkResult
import org.example.project.viewmodel.MyViewModel

@Composable
fun MyMeal(viewmodel : MyViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize()) {

        val state = viewmodel.stateFlowMealRep.collectAsState()
        when(state.value){
            is NetworkResult.Success -> {
                MealSuccess(state.value.data)
            }
            is NetworkResult.Loading -> {
                Progress()
            }
            is NetworkResult.Empty -> {
            }
            is NetworkResult.Error -> {
                ErrorMessage(state.value.message)
            }
        }
    }

}

@Composable
fun MealSuccess(data: MealResponse?) {
    data?.let {
        LazyColumn {
            items(data.meals.size){ index ->
                FoodItem(data.meals[index])
            }
        }
    }
}