package com.example.testbd.data.domain

data class Player(
     /**
     * - usualmente la app no usa una clase entidad, usan objetos propios
     * - ya q, sino todos tendrian acceso al id de dicho objeto (no recomendable)
     * - x lo q se crea un objeto q represente al entity pero sin el parametro id
     *      val id: Int
     */
    val surname: String,
    val nationality: String?,
    val team: String?,
    val position: String?,
    val wear: Int?
)
