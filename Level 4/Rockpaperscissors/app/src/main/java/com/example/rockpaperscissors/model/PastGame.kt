package com.example.rockpaperscissors.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PastGame(
    @PrimaryKey
    val id:Long? = null,
    val epoch: Long,
    val playerChoice: Choice,
    val computerChoice: Choice,
    val winner: Winner
)

enum class Choice{
    ROCK,PAPER,SCISSORS
}

enum class Winner{
    PLAYER, DRAW, COMPUTER
}