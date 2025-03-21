package io.github.coden256.telegram.debug

import io.github.coden256.telegram.abilities.chat
import io.github.coden256.telegram.db.Message
import io.github.coden256.telegram.db.OwnerMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.api.objects.reactions.MessageReactionUpdated
import org.telegram.telegrambots.meta.api.objects.reactions.ReactionTypeEmoji
import org.telegram.telegrambots.meta.api.objects.Chat as TelegramApiChat
import org.telegram.telegrambots.meta.api.objects.Message as TelegramApiMessage

fun messageUpdate(text: String, user: User = DEFAULT_USER, message: Message = DEFAULT_MESSAGE): Update {
    return Update().apply {
        this.message = message(text, user, message)
    }
}

fun editUpdate(text: String, user: User = DEFAULT_USER, message: Message = DEFAULT_MESSAGE): Update {
    return Update().apply {
        editedMessage = message(text, user, message)
    }
}

fun reactionUpdate(emoji: String, user: User = DEFAULT_USER, message: Message = DEFAULT_MESSAGE): Update {
    return Update().apply {
        this.messageReaction = messageReaction(emoji, user, message)
    }
}

fun callbackUpdate(callbackData: String,
                   user: User = DEFAULT_USER,
                   message: TelegramApiMessage = message("CALLED BACK MESSAGE: $callbackData", user)): Update {
    return Update().apply {
        this.callbackQuery = callbackQuery(callbackData, user, message)
    }
}

fun callbackQuery(callbackData: String, user: User, message: TelegramApiMessage): CallbackQuery {
    return CallbackQuery().apply {
        this.data = callbackData
        this.message = message
        this.from = user
    }
}


fun messageReaction(emoji: String, user: User = DEFAULT_USER, message: Message = DEFAULT_MESSAGE): MessageReactionUpdated {
    return MessageReactionUpdated().apply {
        this.newReaction = listOf(ReactionTypeEmoji("emoji", emoji))
        this.user = user
        this.chat = chat(user)
        this.messageId = message.id
    }
}

fun message(text: String, user: User = DEFAULT_USER, message: Message = DEFAULT_MESSAGE): TelegramApiMessage {
    return TelegramApiMessage().apply {
        this.text = text
        this.messageId = message.id
        from = user
        chat = chat(user)
    }
}
val DEFAULT_MESSAGE = OwnerMessage(0)
val DEFAULT_USER = user(283382228, "Denys", "Chernyshov", "coden256")
val DEFAULT_CHAT = chat(DEFAULT_USER)

fun chat(user: User): Chat {
    return TelegramApiChat().apply {
        this.id = user.id
        type = "private"
        userName = user.userName
    }
}

fun user(id: Long, firstName: String, lastName: String, userName: String): User {
    return User().apply {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName
    }
}
