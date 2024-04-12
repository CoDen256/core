package io.github.coden.telegram.keyboard

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton


data class Keyboard(
    val lines: List<KeyboardLine> = listOf(),
)

data class KeyboardLine(
    val buttons: List<KeyboardButton>,
)

open class KeyboardButton(
    val text: String,
    val data: String? = null,
    val switch: String? = null
)

class KeyboardLineBuilder {
    private val line = ArrayList<KeyboardButton>()

    fun b(text: String, data: String = text): KeyboardLineBuilder {
        return b(KeyboardButton(text, data))
    }

    fun b(button: KeyboardButton): KeyboardLineBuilder {
        line.add(button)
        return this
    }

    internal fun build(): KeyboardLine {
        return KeyboardLine(line)
    }
}

class KeyboardBuilder {
    private val lines = ArrayList<KeyboardLine>()

    fun row(line: KeyboardLineBuilder.() -> Unit): KeyboardBuilder {
        val new = KeyboardLineBuilder()
        line.invoke(new)
        lines.add(new.build())
        return this
    }

    internal fun build(): Keyboard {
        return Keyboard(lines)
    }
}

fun keyboard(keyboard: KeyboardBuilder.() -> Unit): Keyboard {
    val new = KeyboardBuilder()
    new.keyboard()
    return new.build()
}


fun Keyboard.asInlineKeyboardMarkup(): InlineKeyboardMarkup? {
    if (lines.isEmpty()) return null
    val markup = InlineKeyboardMarkup.builder()

    for (line in lines) {
        val row = ArrayList<InlineKeyboardButton>()
        for (button in line.buttons) {
            val b = InlineKeyboardButton.builder().apply {
                text(button.text)
                switchInlineQueryCurrentChat(button.switch)
                callbackData(button.data)
            }.build()
            row.add(b)
        }
        markup.keyboardRow(row)
    }

    return markup.build()
}

