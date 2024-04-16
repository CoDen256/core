package io.github.coden.telegram.abilities

import io.github.coden.telegram.commands.CallbackCommand
import io.github.coden.telegram.commands.CommandSerializer
import io.github.coden.telegram.commands.replyOnCallbackCommand
import io.github.coden.telegram.db.BotDB
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.api.objects.Update

open class CommandableTelegramBot<D : BotDB, C : CallbackCommand>(
    config: TelegramBotConfig,
    botDB: D,
    protected val cmdSerializer: CommandSerializer<C>,
    options: DefaultBotOptions = optionsOf()
) : BaseTelegramBot<D>(config, botDB, options) {

    open fun onCallbackCommand() = cmdSerializer.replyOnCallbackCommand { update, data, result ->
        result
            .onSuccess { handleCallback(update, it) }
            .onFailure { handleInvalidCallback(update, data, it) }
    }

    open fun handleCallback(update: Update, command: C) {}
    open fun handleInvalidCallback(update: Update, callback: String, throwable: Throwable) {
        throw throwable
    }

}