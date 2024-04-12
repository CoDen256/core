package io.github.coden.telegram.abilities

import io.github.coden.utils.notNullOrFailure
import org.apache.logging.log4j.kotlin.logger
import org.telegram.abilitybots.api.bot.BaseAbilityBot
import org.telegram.abilitybots.api.objects.*
import org.telegram.abilitybots.api.sender.MessageSender
import org.telegram.abilitybots.api.util.AbilityUtils.getChatId
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText.EditMessageTextBuilder
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.reactions.ReactionType
import org.telegram.telegrambots.meta.api.objects.reactions.ReactionTypeEmoji
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import java.util.function.Predicate

fun optionsOf(allowedUpdates: List<String> = ALLOWED_UPDATES): DefaultBotOptions {
    return DefaultBotOptions()
        .apply { this.allowedUpdates = allowedUpdates }
}

val ALLOWED_UPDATES = listOf(
    "message_reaction",
    "update_id",
    "edited_message",
    "message",
    "callback_query",
    "chosen_inline_result",
    "inline_query"
)


fun replyOn(predicate: Predicate<Update>, handle: (Update) -> Unit): Reply {
    return replyOn({ predicate.test(it) }, { it -> handle(it) })
}

fun replyOn(filter: (Update) -> Boolean, handle: (Update) -> Unit): Reply {
    return Reply.of({ bot, u -> tryHandle(handle, u, bot) }, filter)
}

fun replyOn(predicate: Predicate<Update>, handle: (BaseAbilityBot, Update) -> Unit): Reply {
    return replyOn({ predicate.test(it) }, { bot, upd -> handle(bot, upd) })
}

fun replyOn(filter: (Update) -> Boolean, handle: (BaseAbilityBot, Update) -> Unit): Reply {
    return Reply.of({ bot, u -> tryHandle(handle, u, bot) }, filter)
}

fun replyOnCallback(handle: (Update, String) -> Unit): Reply = replyOn(Flag.CALLBACK_QUERY) {
    upd: Update -> handle(upd, upd.callbackQuery.data)
}

fun replyOnCallback(handle: (BaseAbilityBot, Update, String) -> Unit): Reply = replyOn(Flag.CALLBACK_QUERY) {
        bot, upd -> handle(bot, upd, upd.callbackQuery.data)
}

fun replyOnReaction(vararg emojis: String, handle: (Update) -> Unit): Reply {
    return replyOn({ it.newEmojis.any { emoji -> emojis.contains(emoji) } },
        { upd: Update ->
            logger("replyOnReaction").info("Handling emojis ${upd.newEmojis}")
            handle(upd)
        })
}

val Update.newReactions: List<ReactionType>
    get() = messageReaction?.newReaction ?: emptyList()

val Update.newEmojis: List<String>
    get() = newReactions
        .filterIsInstance<ReactionTypeEmoji>()
        .map { it.emoji }

fun ability(cmd: String, handle: (Update) -> Unit): Ability {
    return Ability.builder()
        .name(cmd)
        .input(0)
        .action { tryHandle(handle, it.update(), it.bot()) }
        .locality(Locality.USER)
        .privacy(Privacy.ADMIN)
        .build()
}

fun tryHandle(handle: (Update) -> Unit, update: Update, bot: BaseAbilityBot) {
    return tryHandle({ _, u -> handle(u) }, update, bot)
}

fun tryHandle(handle: (BaseAbilityBot, Update) -> Unit, update: Update, bot: BaseAbilityBot) {
    try {
        handle(bot, update)
    } catch (e: Exception) {
        bot.silent().send("⚠ ${e.message}\n\n$e", update.chatId())
    }
}

fun Update.chatId(): Long =
    if (this.messageReaction != null) {
        messageReaction.chat.id
    } else {
        getChatId(this)
    }

fun Update.strChatId(): String = chatId().toString()

fun MessageSender.sendHtml(text: String, chatId: Long, replyMarkup: ReplyKeyboard? = null): Message {
    val message = SendMessage.builder().apply {
        parseMode("html")
        text(text)
        chatId(chatId.toString())
        replyMarkup(replyMarkup)
    }.build()
    return execute(message)
}

fun MessageSender.sendMd(
    text: String,
    chatId: Long,
    replyMarkup: ReplyKeyboard? = null,
    replyTo: Int? = null
): Message {
    val message = SendMessage.builder().apply {
        parseMode("Markdown")
        text(text)
        chatId(chatId.toString())
        replyToMessageId(replyTo)
        replyMarkup(replyMarkup)
    }.build()
    return execute(message)
}

fun MessageSender.editMdRequest(
    text: String,
    chatId: Long,
    replyMarkup: InlineKeyboardMarkup? = null,
    messageId: Int? = null
): EditMessageTextBuilder {
    return EditMessageText.builder().apply {
        parseMode("Markdown")
        messageId(messageId)
        text(text)
        chatId(chatId.toString())
        replyMarkup(replyMarkup)

    }
}

fun MessageSender.editMd(messageId: Int, text: String, chatId: Long, replyMarkup: InlineKeyboardMarkup? = null) {
    val request = editMdRequest(text = text, chatId, replyMarkup, messageId).build()
    execute(request)
}

fun String.asCodeSnippet() = "<pre>$this</pre>"

fun justText(update: Update): Boolean {
    return Flag.TEXT.test(update) && !isCommand(update) && !isId(update)
}

fun isCommand(update: Update) = update.message.text.startsWith("/")
fun isId(update: Update) = update.message.text.startsWith("#")

fun getId(update: Update): Result<String> {
    if (!isId(update)) return Result.failure(IllegalArgumentException("${update.message.text} does not represent an id"))

    return update.message.text
        .split(" ")
        .firstOrNull()
        ?.drop(1)
        .notNullOrFailure(IllegalArgumentException("Cannot parse ${update.message.text} as id"))
}

fun cleanText(u: Update): String {
    return cleanText(u.message)
}

fun cleanText(message: Message): String {
    if (message.text.startsWith("/")) {
        if (!message.text.contains(" ")) return ""
        return message.text.split(" ", limit = 2)[1]
    }
    return message.text
}
