package io.github.coden256.utils


inline fun <reified T: Any> T.read(file: String) : String = this::class.java.getResourceAsStream(file)?.bufferedReader()?.readText()!!
