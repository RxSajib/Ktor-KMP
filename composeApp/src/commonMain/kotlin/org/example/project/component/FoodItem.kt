package org.example.project.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ktorkmm.composeapp.generated.resources.Res
import ktorkmm.composeapp.generated.resources.sajib
import org.jetbrains.compose.resources.painterResource

@Composable
fun FoodItem(modifier: Modifier = Modifier) {



    Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth() .height(IntrinsicSize.Min) ) {
            AsyncImage(
                model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwHJ_2dgv6JdsBEdVV5SOPEw2BvXVf6Ne4vA&s",
                contentDescription = null,
                modifier = Modifier.width(100.dp).aspectRatio(1f).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                error = painterResource(Res.drawable.sajib)
            )

            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text("Baked salmon with fennel & tomatoes", style = TextStyle(color = Color.Black.copy(0.5f), fontWeight = FontWeight.SemiBold))

                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {}){
                    Text("Add Cart")
                }
            }
        }
    }
}
