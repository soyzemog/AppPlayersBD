package com.example.testbd.data.repositories

import com.example.testbd.data.local.entities.PlayerDao
import com.example.testbd.data.domain.Player
import com.example.testbd.data.mapper.toDomain
import com.example.testbd.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * - sirve para convertir 'PlayerEntity' en 'Player'
 *      y al mismo tiempo llamar a la BD
 * - para acceder a la BD, uso el 'playerDao'
 */
class PlayerRepository(
    private val playerDao: PlayerDao
) {

    /**
     * devuelve un listado de Flow de players (conviertiendo de entity a dominio)
     * - uso Flow para q se refresque la info q muestro en pantalla con respecto
     * a lo q tengo en bd (automaticamente)
     * - continua en PlayerViewModel
     */
    fun getPlayers(): Flow<List <Player>> {
        return playerDao.getPlayers().map { it.map { playerEntity -> playerEntity.toDomain() } }
    }


    suspend fun insertPlayer(player: Player) {
        playerDao.insertPlayer(player.toEntity())
    }


    suspend fun deletePlayer(player: Player) {
        playerDao.deletePlayer(player.toEntity())
    }


    suspend fun getPlayer(idIn: Int): Player {
        val playerEntity = playerDao.getPlayer(idIn)
        return playerEntity.toDomain()
    }


}