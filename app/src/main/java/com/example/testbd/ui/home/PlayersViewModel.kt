package com.example.testbd.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbd.data.model.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayersViewModel: ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {

            _state.value = UiState(loading = true)

            delay(2000)

            _state.value = UiState(items = listOf(
                Player(1, "Messi", "Argentina",
                    listOf("Barcelona", "PSG"), listOf("MCA", "ED"),
                    listOf(10, 30)),
                Player(2, "Riquelme", "Argentina",
                    listOf("BOCA Jrs", "Villareal"), listOf("MCA"),
                    listOf(10, 8)),
                Player(3, "Ronaldinho", "Brazil",
                    listOf("PSG", "Barcelona"), listOf("MCA", "EI"),
                    listOf(10, 7)),
                Player(4, "Pirlo", "Italia",
                    listOf("Milan", "Juventus"), listOf("MCD", "MC"),
                    listOf(21)),
                Player(5, "Zidane", "Francia",
                    listOf("Juventus", "Real Madrid"), listOf("MC", "MCO"),
                    listOf(10, 5))
                ))
        }
    }


    fun deletePlayer(player: Player) {
        val index = _state.value.items.indexOf(player)
        val updatedPlayers = _state.value.items.toMutableList()
        updatedPlayers.removeAt(index)
        _state.value = _state.value.copy(
            items = updatedPlayers
        )
    }


    data class UiState(
        val loading: Boolean = false,
        var items: List<Player> = emptyList()
    )

}