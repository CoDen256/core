package io.github.coden.utils

import com.andreapivetta.kolor.Color
import com.andreapivetta.kolor.Kolor
import org.apache.commons.lang3.RandomStringUtils
import java.util.concurrent.ThreadLocalRandom

fun randomPronouncable(min: Int, max: Int): String{
    val current = ThreadLocalRandom.current()
    val result = RandomStringUtils.random(    current.nextInt(min, 2*max),
        vowels.repeat(current.nextInt(10)) + consontants.repeat(current.nextInt(3))
    ).lowercase()
    return result
        .replace(Regex("([$vowels]{2})(?=([$vowels]))")){it.groupValues[1]+ randomConsonant() }
        .replace(Regex("([$consontants]{2})(?=([$consontants]))")){it.groupValues[1]+ randomVowel() }
        .take(max)
}

const val vowels = "aeiou"
const val consontants = "bcdfghjklmnpqrstvwxyz"

fun String.lastVowel(): Boolean{
    return this.lastOrNull()?.let { vowels.contains(it) } == true
}

fun randomVowel(): String{
    return RandomStringUtils.random(1, vowels)
}

fun randomConsonant(): String{
    return RandomStringUtils.random(1, consontants)
}

fun randomNumber(): String{
    return RandomStringUtils.randomNumeric(1)
}

operator fun Color.invoke(input: String): String {
    return Kolor.foreground(input, this)
}
fun Color.bg(input: String): String {
    return Kolor.background(input, this)
}
fun Color.fg(input: String): String {
    return Kolor.foreground(input, this)
}