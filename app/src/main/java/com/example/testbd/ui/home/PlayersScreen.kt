package com.example.testbd.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testbd.data.model.Player

/** @Composable
fun PlayersScreen(
    players: List<Player>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(players) {
            PlayerItem(
                player = it,
                //onClick = { onPersonClick(it) }
            )
        }
    }
} **/

@Composable
fun PlayersScreen(
    onEditPlayerClick: (Player) -> Unit,
    viewModel: PlayersViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.loading) {
            CircularProgressIndicator()
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(state.items) { player ->

            PlayerItem(
                player = player,
                onEditPlayerClick = { onEditPlayerClick(player) },
                onDeletePlayerClick = { viewModel.deletePlayer(player) }
            )
        }
    }

}