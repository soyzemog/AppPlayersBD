package com.example.testbd.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testbd.data.model.Player

@Composable
fun PlayerItem(
    player: Player,
    onEditPlayerClick: () -> Unit,
    onDeletePlayerClick: () -> Unit
) {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 16.dp),
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = player.surname,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2 //x si hay nombres largos
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = player.nationality.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2 //x si hay nombres largos
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = player.team.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2 //x si hay nombres largos
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = player.position.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2 //x si hay nombres largos
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = player.wear.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2 //x si hay nombres largos
                )
            }
            Row {
                IconButton(onClick = onEditPlayerClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }
                IconButton(onClick =  onDeletePlayerClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}