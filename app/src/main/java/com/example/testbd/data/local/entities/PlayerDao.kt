package com.example.testbd.data.local.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * FROM PlayerEntity")
    fun getPlayers(): Flow<List<PlayerEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: PlayerEntity)


    @Delete
    suspend fun deletePlayer(player: PlayerEntity)


    @Query("SELECT * FROM PlayerEntity WHERE id = :idIn")
    suspend fun getPlayer(idIn: Int): PlayerEntity

}

/**
 * fun getPlayers(): Flow<List<PlayerEntity>>
 *     - usar FLOW
 *     - de esta forma soluciono el problema de hacer un refresh de datos
 *     es decir, cuando cambian o se agregan nuevos datos a la bd
 *     automaticamente se ve reflejado en pantalla
 *     - tambien hay q agregarlo en el Repository
 */