package org.example.project.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.data.model.post.PostResponseItem

@Composable
fun Item(item : PostResponseItem) {
    Box(modifier = Modifier.fillMaxWidth().padding(10.dp).clickable {

    }) {
        Column {
            Text(
                text = item.title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            )
            Text(
                text = item.body,
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}

