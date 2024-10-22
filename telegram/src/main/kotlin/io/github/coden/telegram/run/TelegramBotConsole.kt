package io.github.coden.telegram.run


import com.github.ajalt.mordant.rendering.TextColors
import io.github.coden.telegram.abilities.RunnableLongPollingBot
import org.apache.logging.log4j.kotlin.Logging
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class TelegramBotConsole(
    private vararg val bots: RunnableLongPollingBot
) : Console, Logging {

    private val api = TelegramBotsApi(DefaultBotSession::class.java)

    override fun start() {
        logger.info("Launching multiple telegram bots")
        try {
            for (bot in bots) {
                api.registerBot(bot)
                bot.run()
                logger.info("Started ${TextColors.brightGreen(bot.name())}!")
            }
        } catch (e: TelegramApiException) {
            logger.error("Telegram bot got exception: ${e.message}", e)
        }
    }

    override fun stop() {
        logger.info("Stopping telegram bot console.")
    }
}