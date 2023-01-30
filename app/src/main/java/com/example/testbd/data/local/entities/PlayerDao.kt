package com.example.testbd.data.local.entities

import androidx.room.*

@Dao
interface PlayerDao {

    @Query("SELECT * FROM Player")
    suspend fun getPlayers(): List<PlayerEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: PlayerEntity)


    @Delete
    suspend fun deletePlayer(player: PlayerEntity)


    @Query("SELECT * FROM Player WHERE id = :idIn")
    suspend fun getPlayer(idIn: Int): PlayerEntity

}