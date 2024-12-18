package org.example.project.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.component.FoodItem
import org.example.project.component.Item
import org.example.project.data.model.post.PostResponseItem
import org.example.project.utils.NetworkResult
import org.example.project.viewmodel.MyViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

private const val TAG = "Home"
@Preview
@Composable
fun Home(viewModel: MyViewModel = viewModel()) {

    val vm = viewModel.stateFlow.collectAsState()
    val context = Locale.current

        val view = vm.value
        when(view){
            is NetworkResult.Success -> {
                SuccessFully(view.data)
            }
            is NetworkResult.Error -> {
                ErrorMessage(view.message)
            }
            is NetworkResult.Loading -> {
            Progress()
            }
            is NetworkResult.Empty -> {
                Progress()
            }
    }
}

@Composable
fun Progress() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun SuccessFully(data: List<PostResponseItem>?) {
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn {
            items(data?.size ?: 0){ index ->
                data?.let { data ->
                    Item(data[index])
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(message: String?) {
    Box(modifier = Modifier.fillMaxSize()){
        Text("$message")
    }
}