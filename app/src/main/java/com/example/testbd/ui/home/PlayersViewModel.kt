package com.example.testbd.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbd.data.domain.Player
import com.example.testbd.data.repositories.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayersViewModel(
    private val playerRepository: PlayerRepository
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    /** init {
        viewModelScope.launch {

            _state.value = UiState(loading = true)

            delay(2000)

            _state.value = UiState(items = listOf(
                Player(1, "Messi", "Argentina",
                    "PSG", "MCA", 30),
                Player(2, "Riquelme", "Argentina",
                    "BOCA Jrs", "MCA", 10),
                Player(3, "Ronaldinho", "Brazil",
                    "Barcelona", "MCA", 10),
                Player(4, "Pirlo", "Italia",
                    "Milan", "MCD", 21),
                Player(5, "Zidane", "Francia",
                    "Real Madrid", "MC", 5)
                ))
        }
    } **/

    init {
        viewModelScope.launch {
            if (playerRepository.getPlayers().isNotEmpty()) {

                _state.value = UiState(
                    items = playerRepository.getPlayers()
                )

            } else {

                _state.value = UiState(items = listOf(
                    Player( "Messi", "Argentina",
                        "PSG", "MCA", 30),
                    Player( "Riquelme", "Argentina",
                        "BOCA Jrs", "MCA", 10),
                    Player( "Ronaldinho", "Brazil",
                        "Barcelona", "MCA", 10),
                    Player( "Pirlo", "Italia",
                        "Milan", "MCD", 21),
                    Player( "Zidane", "Francia",
                        "Real Madrid", "MC", 5)
                ))

            }
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