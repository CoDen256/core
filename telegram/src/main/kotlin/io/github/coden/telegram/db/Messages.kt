package io.github.coden256.telegram.db

import org.telegram.telegrambots.meta.api.objects.MaybeInaccessibleMessage
import java.io.Serializable

data class Chat(val id: Long): Serializable {
    companion object {
        fun org.telegram.telegrambots.meta.api.objects.Chat.asChat(): Chat {
            return Chat(this.id)
        }

        fun Number.asChat(): Chat {
            return Chat(this.toLong())
        }
    }

    override fun toString(): String {
        return id.toString()
    }
}

sealed interface Message: Serializable{
    val id: Int
}

data class OwnerMessage(override val id: Int): Message {
    companion object {
        fun MaybeInaccessibleMessage.asOwner(): OwnerMessage {
            return OwnerMessage(this.messageId)
        }
        fun Number.asOwner(): OwnerMessage {
            return OwnerMessage(this.toInt())
        }
    }

}

data class BotMessage(override val id: Int): Message {
    companion object {
        fun MaybeInaccessibleMessage.asBot(): BotMessage {
            return BotMessage(this.messageId)
        }
        fun Number.asBot(): BotMessage {
            return BotMessage(this.toInt())
        }
    }
}