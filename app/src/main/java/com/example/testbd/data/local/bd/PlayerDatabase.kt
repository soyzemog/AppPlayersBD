package com.example.testbd.data.local.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testbd.data.local.entities.PlayerDao
import com.example.testbd.data.local.entities.PlayerEntity


@Database(entities = [PlayerEntity::class], version = 1)
abstract class PlayerDatabase: RoomDatabase() {

    abstract val dao: PlayerDao

}



/**
 * - usualmente no se instancia la bd, de eso se encarga room
 * - dentro de entities, van todas las clases entidad que tengamos
 * - version, es para avisar a room si hubieron cambios en la entidades
 *      o si se agregaron nuevas, etc
 */