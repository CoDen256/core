package io.github.coden.telegram.abilities

data class TelegramBotConfig (
    val token: String,
    val target: Long,
    val username: String,
    val intro: String
)