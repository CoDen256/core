package io.github.coden.telegram.abilities

import io.github.coden.telegram.db.BotDB
import org.telegram.abilitybots.api.bot.AbilityBot
import org.telegram.abilitybots.api.objects.Ability
import org.telegram.abilitybots.api.util.AbilityUtils.EMPTY_USER
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.api.objects.ChatJoinRequest
import org.telegram.telegrambots.meta.api.objects.Update

open class BaseTelegramBot<DB : BotDB>(
    private val config: TelegramBotConfig,
    private val botDB: DB,
    options: DefaultBotOptions = optionsOf()
) : AbilityBot(config.token, config.username, botDB, options), RunnableLongPollingBot{

    protected open val db = botDB
    override fun db(): DB = botDB

    override fun creatorId(): Long { return config.target }

    override fun run() {
        greet()
        sendDebugUpdates()
    }

    open fun sendDebugUpdates() { onUpdatesReceived(debugUpdates()) }
    open fun debugUpdates(): List<Update>{ return listOf() }

    open fun start(): Ability = ability("start") { greet() }
    open fun greet() { silent.sendMd(config.intro, config.target) }

    override fun onUpdateReceived(update: Update?) {
        // library does not see a valid user on reactions
        // hack to force library to think it has a valid user, but supplying fake info
        // it'll return EMPTY_USER
        // we'll sacrifice chatjoin types of messages
        if (update?.messageReaction != null){
            update.chatJoinRequest = ChatJoinRequest().apply { user = EMPTY_USER }
        }
        super.onUpdateReceived(update)
    }
}