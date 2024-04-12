package io.github.coden.telegram.senders

data class StyledString(
    val text: String,
    val parseMode: ParseMode = ParseMode.MARKDOWN,
): CharSequence by text{
    operator fun plus(other: StyledString): StyledString {
        if (other.parseMode != ParseMode.PLAIN && this.parseMode != ParseMode.PLAIN && other.parseMode != this.parseMode) {
            throw IllegalArgumentException("Concatenation of different parse modes ${this.toObjectString()} and ${other.toObjectString()}")
        }
        return StyledString(this.text + other.text, this.parseMode)
    }

    operator fun plus(other: String): StyledString {
        return StyledString(this.text + other, parseMode)
    }

    override fun toString(): String { return text }
    fun toObjectString(): String { return "$parseMode(${text.take(10)}...)" }
}

enum class ParseMode(val mode: String?) {
    PLAIN(null),
    HTML(org.telegram.telegrambots.meta.api.methods.ParseMode.HTML),
    MARKDOWN(org.telegram.telegrambots.meta.api.methods.ParseMode.MARKDOWN),
    MARKDOWN_V2(org.telegram.telegrambots.meta.api.methods.ParseMode.MARKDOWNV2)
}

operator fun String.plus(other: StyledString): StyledString {
    return StyledString(this + other.text, other.parseMode)
}

fun String.styled(parseMode: ParseMode = ParseMode.PLAIN): StyledString {
    return StyledString(this, parseMode)
}

fun StyledString.snippet() = copy(text = text.snippet(parseMode))
fun String.snippet(parseMode: ParseMode = ParseMode.MARKDOWN) = when (parseMode) {
    ParseMode.HTML -> "<pre>$this</pre>"
    ParseMode.MARKDOWN,
    ParseMode.MARKDOWN_V2 -> "```$this```"
    ParseMode.PLAIN -> this
}

fun StyledString.code() = copy(text = text.code(parseMode))
fun String.code(parseMode: ParseMode = ParseMode.MARKDOWN) = when (parseMode) {
    ParseMode.HTML -> "<code>$this</code>"
    ParseMode.MARKDOWN,
    ParseMode.MARKDOWN_V2 -> "`$this`"
    ParseMode.PLAIN -> this
}

fun String.bold(parseMode: ParseMode = ParseMode.MARKDOWN) = when (parseMode) {
    ParseMode.HTML -> "<b>$this</b>"
    ParseMode.MARKDOWN, ParseMode.MARKDOWN_V2 -> "*$this*"
    ParseMode.PLAIN -> this
}
fun String.italic(parseMode: ParseMode = ParseMode.MARKDOWN) = when (parseMode) {
    ParseMode.HTML -> "<pre>$this</pre>"
    ParseMode.MARKDOWN, ParseMode.MARKDOWN_V2 -> "```$this```"
    ParseMode.PLAIN -> this
}

fun String.underline(parseMode: ParseMode = ParseMode.MARKDOWN) = when (parseMode) {
    ParseMode.HTML -> "<u>$this</u>"
    ParseMode.MARKDOWN, ParseMode.MARKDOWN_V2 -> "__${this}__"
    ParseMode.PLAIN -> this
}

fun String.strike(parseMode: ParseMode = ParseMode.MARKDOWN) = when (parseMode) {
    ParseMode.HTML -> "<s>$this</s>"
    ParseMode.MARKDOWN, ParseMode.MARKDOWN_V2 -> "~~$this~~"
    ParseMode.PLAIN -> this
}


