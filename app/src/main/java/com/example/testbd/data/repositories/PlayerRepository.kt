package com.example.testbd.data.repositories

import com.example.testbd.data.local.entities.PlayerDao
import com.example.testbd.data.domain.Player

/**
 * - sirve para convertir 'PlayerEntity' en 'Player'
 *      y al mismo tiempo llamar a la BD
 * - para acceder a la BD, uso el 'playerDao'
 */
class PlayerRepository(
    private val playerDao: PlayerDao
) {

    suspend fun getPlayers(): List<Player> {
        val entities = playerDao.getPlayers()
        return entities.map { playerEntity ->
            playerEntity.toDomain()
        }
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