package io.github.coden.utils

import org.apache.logging.log4j.kotlin.KotlinLogger


fun <T> Result<T>.logInteraction(logger: KotlinLogger, operation: (T) -> String): Result<T> {
    return this
        .onSuccess { logger.info("${operation(it)} - Success!") }
        .onFailure { logger.error("Failed! - " + it.message, it) }
}

fun <T : Any> T.success(): Result<T> = Result.success(this)
fun success(): Result<Unit> = Unit.success()

fun <T : Any> T?.notNullOrElse(default: T): Result<T> {
    return this?.success() ?: default.success()
}

fun <T : Any> T?.notNullOrFailure(exception: Exception): Result<T> {
    return this?.success() ?: Result.failure(exception)
}

inline fun <reified T : Any> T?.notNullOrFailure(): Result<T> {
    return this?.success()
        ?: Result.failure(IllegalArgumentException("Element was not found ${T::class.simpleName}"))
}

inline fun <R, T> Result<T>.flatMap(transform: (value: T) -> Result<R>): Result<R> {
    return this.mapCatching { transform(it).getOrThrow() }
}

inline fun <reified E, R> Result<R>.recover(transform: (exception: E) -> R): Result<R> {
    return this.recoverCatching {
        if (it is E){
            return@recoverCatching transform(it)
        }
        else {
            throw it
        }
    }
}