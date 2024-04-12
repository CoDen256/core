package io.github.coden.telegram.db

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

data class Message(val id: Int): Serializable{
    companion object {
        fun MaybeInaccessibleMessage.asMessage(): Message {
            return Message(this.messageId)
        }
        fun Number.asMessage(): Message {
            return Message(this.toInt())
        }
    }
}

data class OwnerMessage(val id: Int): Serializable {
    companion object {
        fun MaybeInaccessibleMessage.asOwner(): OwnerMessage {
            return OwnerMessage(this.messageId)
        }
        fun Number.asOwner(): OwnerMessage {
            return OwnerMessage(this.toInt())
        }
    }

}

data class BotMessage(val id: Int): Serializable {
    companion object {
        fun MaybeInaccessibleMessage.asBot(): BotMessage {
            return BotMessage(this.messageId)
        }
        fun Number.asBot(): BotMessage {
            return BotMessage(this.toInt())
        }
    }
}