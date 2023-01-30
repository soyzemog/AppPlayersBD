package com.example.testbd.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbd.data.domain.Player
import com.example.testbd.data.repositories.PlayerRepository
import kotlinx.coroutines.launch

/**
 * savedStateHandle
 * . fue la solucion al problema de llenado de datos en los textfields
 * . ya que el if (q tengo en el init) lo tenia en el composable 'NewPlayersScreen'
 *      y si bien completaba, no dejaba modificar el textfield
 */
class NewPlayerViewModel(
    private val playerRepository: PlayerRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

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
            /**
             * verificamos si el idPlayer tiene valor
             * . si tiene es porque vamos a editar un player (completa los textfield)
             * . si no tiene es porque vamos a crear uno nuevo
             */
            val idPlayer = savedStateHandle.get<Int>("playerId") ?: 0
            if (idPlayer != 0) {

                val editPlayer = playerRepository.getPlayer(idPlayer)

                surname = editPlayer.surname
                nation = editPlayer.nationality.toString()
                team = editPlayer.team.toString()
                position = editPlayer.position.toString()
                wear = editPlayer.wear.toString()

            }
        }
    }

    /** fun loadPlayer(idIn: Int): Player {
        /**return Player(
            1,
            "Messi",
            "Argentina",
            "PSG",
            "MCA",
            30
        ) **/

        viewModelScope.launch {
            player = playerRepository.getPlayer(idIn)
        }
        return player
    } **/

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
        viewModelScope.launch {

            playerRepository.insertPlayer(player)

        }
    }

}