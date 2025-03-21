package io.github.coden256.telegram.abilities

data class TelegramBotConfig (
    val token: String,
    val target: Long,
    val username: String,
    val intro: String
)