package com.example.testbd.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbd.data.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewPlayerViewModel: ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
           // _state.value = UiState(item = )
        }
    }

    fun savePlayer(player: Player) {
        /** guardar en bd **/
    }

    fun loadPlayer(id: Int): Player {
        return Player(
            1,
            "Messi",
            "Argentina",
            listOf("PSG"),
            listOf("MCA"),
            listOf(30)
        )
    }


    data class UiState(
        //val item: Player = Player()
        val item: String = ""
    )

}