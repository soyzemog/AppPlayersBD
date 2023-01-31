package com.example.testbd

import android.app.Application
import androidx.room.Room
import com.example.testbd.data.local.bd.PlayerDatabase
import com.example.testbd.data.local.entities.PlayerDao
import com.example.testbd.data.repositories.PlayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlayerDao(application: Application): PlayerDao {
        val db = Room.databaseBuilder(
            application,
            PlayerDatabase::class.java,
            "player_db"
        ).build()
        return db.dao
    }


    @Provides
    @Singleton
    fun provideRepository(dao: PlayerDao): PlayerRepository {
        return PlayerRepository(dao)
    }

}

/**
 * su nombre usualmente es el nombre del paquete o feature
 * con la palabra 'Module'
 */

/**
 * normalmente las funciones deben llamarse 'provide'
 * ya q proveen una dependencia
 */


/**
 * instanciamos la base de datos,
 * requiere de un contexto, el cual lo podemos sacar de:
 * - una activity
 * - fragment
 * - aplicacion (es de hilt)
 *      - entonces usamos application
 */