package com.example.testbd.data.domain

data class Player(
    val id: Int? = null,
    val surname: String? = null,
    val nationality: String? = null,
    val team: String? = null,
    val position: String? = null,
    val wear: Int? = null
)

/**
 * - usualmente la app no usa una clase entidad, usan objetos propios
 * - ya q, sino todos tendrian acceso al id de dicho objeto (no recomendable)
 * - x lo q se crea un objeto q represente al entity pero sin el parametro id
 *      val id: Int
 * - en este caso lo necesito, x eso lo agrego (depende del contexto de la app)
 */
