package io.github.coden.telegram.run

import com.andreapivetta.kolor.Color
import com.andreapivetta.kolor.Color.GREEN
import com.andreapivetta.kolor.Kolor
import com.andreapivetta.kolor.lightWhiteBackground
import io.github.coden.telegram.abilities.RunnableLongPollingBot
import io.github.coden.utils.invoke
import org.apache.logging.log4j.kotlin.Logging
import org.apache.logging.log4j.kotlin.logger
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
                logger.info("Started ${Color.LIGHT_GREEN(bot.name())}!")
            }
        } catch (e: TelegramApiException) {
            logger.error("Telegram bot got exception: ${e.message}", e)
        }
    }

    override fun stop() {
        logger.info("Stopping telegram bot console.")
    }
}