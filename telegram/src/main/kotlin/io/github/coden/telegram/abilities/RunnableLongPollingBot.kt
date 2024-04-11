package io.github.coden.telegram.abilities

import org.apache.logging.log4j.kotlin.Logging
import org.telegram.telegrambots.meta.generics.LongPollingBot

interface RunnableLongPollingBot: LongPollingBot, Logging {
    fun name(): String { return this.javaClass.simpleName }
    fun run()
}