package com.example.testbd.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbd.data.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
/**
class NewPlayerViewModel: ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
           _state.value = UiState(surname = "")
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

    fun editSurname(surname: String) {
        _state.value.surname = surname
    }


    data class UiState(
        //val item: Player = Player()
        var surname: String = ""
    )

} **/

class NewPlayerViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    var surname by mutableStateOf("")
        private set
    var nation by mutableStateOf("")
        private set
    var team by mutableStateOf("")
        private set
    var position by mutableStateOf("")
        private set
    var wear by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            val idPlayer = savedStateHandle.get<Int>("playerId") ?: 0
            if (idPlayer != 0) {

                val editPlayer = loadPlayer(idPlayer)

                surname = editPlayer.surname
                nation = editPlayer.nationality.toString()
                team = editPlayer.team?.first() ?: ""
                position = editPlayer.position?.first() ?: ""
                wear = editPlayer.wear?.first().toString()

            }
        }
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

    fun editSurname(surnameIN: String) {
        surname = surnameIN
    }
    fun editNation(nationIN: String) {
        nation = nationIN
    }
    fun editTeam(teamIN: String) {
        team = teamIN
    }
    fun editPosition(positionIN: String) {
        position = positionIN
    }
    fun editWear(wearIN: String) {
        wear = wearIN
    }

    fun savePlayer(player: Player) {
        /** guardar en bd **/
    }

}