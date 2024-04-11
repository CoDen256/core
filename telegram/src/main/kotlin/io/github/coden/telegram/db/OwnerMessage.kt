package io.github.coden.telegram.db

import org.telegram.telegrambots.meta.api.objects.MaybeInaccessibleMessage
import java.io.Serializable

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