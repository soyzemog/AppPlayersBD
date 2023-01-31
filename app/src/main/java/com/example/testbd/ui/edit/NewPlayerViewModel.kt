package com.example.testbd.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbd.data.domain.Player
import com.example.testbd.data.repositories.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPlayerViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var id: Int? by mutableStateOf(null)
        private set

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

                /**
                 * lo necesito para saber a quien actualizar cuando edite un player
                 */
                id = idPlayer

                surname = editPlayer.surname.toString()
                nation = editPlayer.nationality.toString()
                team = editPlayer.team.toString()
                position = editPlayer.position.toString()
                wear = editPlayer.wear.toString()

            }
        }
    }

    /**
     * estas funciones son para utilizarlos desde NewPlayerScreen
     * la cual completan los campos con datos de un Player a editar
     */
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


    /**
     * ahora es el viewModel quien se ocupa de crear el objeto Player
     * ya q antes lo hacia en la ui (lo cual no es correcto)
     * y luego lo inserta en la bd
     */
   fun savePlayer() {
       viewModelScope.launch {
           val player = Player(id, surname, nation, team, position, wear.toInt())
           playerRepository.insertPlayer(player)
       }
   }

    /**
     * antes lo hacia en ui
     * ahora le saque esa responsabilidad y se la asigno al viewmodel
     * - limpio los campos de los textfields
     */
    fun cleanFields() {
       editSurname("")
        editNation("")
        editTeam("")
        editPosition("")
        editWear("")
    }

}


/**
 * savedStateHandle
 * . fue la solucion al problema de llenado de datos en los textfields
 * . ya que el if (q tengo en el init) lo tenia en el composable 'NewPlayersScreen'
 *      y si bien completaba, no dejaba modificar el textfield
 */