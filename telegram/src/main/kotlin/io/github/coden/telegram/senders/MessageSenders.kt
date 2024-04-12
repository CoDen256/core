package io.github.coden.telegram.senders

import io.github.coden.telegram.db.BotMessage
import io.github.coden.telegram.db.BotMessage.Companion.asBot
import io.github.coden.telegram.db.Chat
import io.github.coden.telegram.db.Message
import io.github.coden.telegram.keyboard.Keyboard
import io.github.coden.telegram.keyboard.asInlineKeyboardMarkup
import io.github.coden.utils.success
import org.apache.logging.log4j.kotlin.logger
import org.telegram.abilitybots.api.sender.MessageSender
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText.EditMessageTextBuilder
import org.telegram.telegrambots.meta.api.objects.Update
import java.io.Serializable

fun MessageSender.sendSilently(
    text: String,
    chat: Chat,
    replyTo: Message? = null,
    keyboard: Keyboard = Keyboard()
): Result<BotMessage>{
    return sendSilently(text.styled(), chat, replyTo, keyboard)
}

fun MessageSender.sendSilently(
    text: StyledString,
    chat: Chat,
    replyTo: Message? = null,
    keyboard: Keyboard = Keyboard()
): Result<BotMessage>{
    return try {
        send(text, chat, replyTo, keyboard).success()
    }catch (e:Exception){
        logger.error("Could not send a text message ${e.message}")
        Result.failure(e)
    }
}

fun MessageSender.send(
    text: String,
    chat: Chat,
    replyTo: Message? = null,
    keyboard: Keyboard = Keyboard()
): BotMessage { return send(text.styled(), chat, replyTo, keyboard) }

fun MessageSender.send(
    text: StyledString,
    chat: Chat,
    replyTo: Message? = null,
    keyboard: Keyboard = Keyboard()
): BotMessage {
    val message = SendMessage.builder().apply {
        parseMode(text.parseMode.mode)
        text(text.toString())
        chatId(chat.toString())
        replyToMessageId(replyTo?.id)
        replyMarkup(keyboard.asInlineKeyboardMarkup())
    }.build()
    return execute(message).asBot()
}

fun MessageSender.editBuilder(
    text: StyledString?,
    chat: Chat?,
    message: Message?, //TODO Put chat as message's part
    keyboard: Keyboard = Keyboard()
): EditMessageTextBuilder {
    return EditMessageText.builder().apply {
        parseMode(text?.parseMode?.mode)
        text(text.toString())
        messageId(message?.id)
        chatId(chat.toString())
        replyMarkup(keyboard.asInlineKeyboardMarkup())
    }
}

fun MessageSender.edit(text: String,
                       chat: Chat,
                       message: Message,
                       keyboard: Keyboard = Keyboard()): Serializable {
    return edit(text.styled(), chat, message, keyboard)
}

fun MessageSender.edit(text: StyledString,
                       chat: Chat,
                       message: Message,
                       keyboard: Keyboard = Keyboard()): Serializable {
    val request: EditMessageText = editBuilder(text, chat, message, keyboard).build()
    return execute(request)
}

fun MessageSender.answerCallback(
    update: Update,
    text: String
): Boolean {
    return execute(
        AnswerCallbackQuery.builder().apply {
            callbackQueryId(update.callbackQuery.id)
            text(text)
        }.build(),
    )
}

fun MessageSender.deleteMessage(
    target: BotMessage,
    chat: Chat
): Boolean {
    return execute(DeleteMessage.builder().apply {
        messageId(target.id)
        chatId(chat.id)
    }.build())
}

