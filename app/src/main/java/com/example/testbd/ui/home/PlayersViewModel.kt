package com.example.testbd.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbd.data.domain.Player
import com.example.testbd.data.repositories.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
): ViewModel() {

    /**
     * utilizo StateFlow (no tiene nada q ver con Flow)
     * - q es lo q obtengo del playerRepository, al llamar al getPlayers()
     * - _state, se usa en el viewmodel
     * - state, es el q uso desde afuera (es decir, en PlayersScreen)
     */
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            /**
             * .collect
             * - indica que alguien del otro lado espera resultados (este caso PlayersScreen)
             *  por lo tanto flow puede emitirlos (el listado de player)
             */
            playerRepository.getPlayers().collect {
                if (it.isNotEmpty()) {

                    _state.value = UiState(loading = true)

                    _state.value = UiState(items = it)

                } else {
                    // mostrar msj si bd esta vacia
                    _state.value = UiState(showMessage = "The DB is empty")
                }
            }
        }
    }


    fun deletePlayer(player: Player) {
        /**
         * 1ero lo borramos de la list que se pinta en pantalla
         */
        /** val index = _state.value.items.indexOf(player)
        val updatedPlayers = _state.value.items.toMutableList()
        updatedPlayers.removeAt(index)

        /**
         * luego lo borro de la bd
         */
        viewModelScope.launch {
            if (_state)
            if (playerRepository.getPlayers().isNotEmpty()) {
                playerRepository.deletePlayer(player)
            }
        }

        _state.value = _state.value.copy(
            items = updatedPlayers
        ) **/

        /**
         * ya no es necesario borrarlo de la vista, basta con borrarlo de la bd
         * ya q al usar flow, se detecta q hay cambios en la bd y se refresca la pantalla
         */

        if (_state.value.items.contains(player)) {
            viewModelScope.launch { playerRepository.deletePlayer(player) }
        }
    }

    /**
     * esta funcion es util, si no se utiliza Flow
     * fun reloadPlayers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                items = playerRepository.getPlayers()
            )
        }
        }
     */

    data class UiState(
        val loading: Boolean = false,
        val showMessage: String = "",
        var items: List<Player> = emptyList()
    )

}