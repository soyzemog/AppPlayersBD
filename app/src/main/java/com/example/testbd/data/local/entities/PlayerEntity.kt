package com.example.testbd.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val surname: String?,
    val nationality: String?,
    val team: String?,
    val position: String?,
    val wear: Int?
)

/**
 * x defecto id es null, xq lo genera automaticamente room
 */

/**
 * al nombre de la clase le agrego Entity
 * para diferenciar la clase entidad (bd),
 * de la clase de la aplicacion
 */