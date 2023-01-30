package com.example.testbd.data.repositories

import com.example.testbd.data.local.entities.PlayerEntity
import com.example.testbd.data.domain.Player

/**
 * convierto el PlayerEntity en Player
 */
fun PlayerEntity.toDomain(): Player {
    return Player(
        surname = this.surname,
        nationality = this.nationality,
        team = this.team,
        position = this.position,
        wear = this.wear
    )
}

/**
 * convierto Player en PlayerEntity
 */
fun Player.toEntity(): PlayerEntity {
    return PlayerEntity(
        surname = this.surname,
        nationality = this.nationality,
        team = this.team,
        position = this.position,
        wear = this.wear
    )
}