package com.irfan.rockpaperscissor.enum

enum class PlayerPick(val value: Int) {
    ROCK(0),
    PAPER(1),
    SCISSOR(2);
    companion object {
        fun fromInt(value: Int) = values().first(){it.value == value}
    }
}