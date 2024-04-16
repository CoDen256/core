package io.github.coden.utils

import com.andreapivetta.kolor.Color
import org.apache.logging.log4j.kotlin.KotlinLogger


fun <T> Result<T>.logResult(logger: KotlinLogger, operation: (T) -> String): Result<T> {
    return this
        .onSuccess { logger.info("${operation(it)} - ${Color.LIGHT_GREEN("Success!")}") }
        .onFailure { logger.error("${Color.LIGHT_RED("Failed!")} - " + it.message, it) }
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

fun <R, T> Result<T>.flatMap(transform: (T) -> Result<R>): Result<R> {
    fold(
        { return transform(it) },
        { return Result.failure(it) }
    )
}

fun <T, R> Result<T>.combine(transform: (T) -> Result<R>): Result<Pair<T, R>> {
    fold(
        { first -> return transform(first).map { second -> first to second } },
        { return Result.failure(it) }
    )
}

fun <T, R, E> Result<Pair<T,R>>.combine(transform: (T, R) -> Result<E>): Result<Triple<T, R, E>> {
    fold(
        { (f, s) -> return transform(f, s).map { third -> Triple(f, s, third) } },
        { return Result.failure(it) }
    )
}

fun <T, R> Result<T>.concat(second: Result<R>): Result<Pair<T, R>> {
    return concatParallel { second }
}

fun <T, R> Result<T>.concat(supply: () -> Result<R>): Result<Pair<T, R>> {
    fold(
        {f -> supply().fold(
            {s -> return Result.success(f to s)},
            {s -> return Result.failure(s) }
        )},
        {f ->  return Result.failure(f)},
        )

}

fun <T, R> Result<T>.concatParallel(second: Result<R>): Result<Pair<T, R>> {
    return concatParallel { second }
}

fun <T, R> Result<T>.concatParallel(supply: () -> Result<R>): Result<Pair<T, R>> {
    val second = supply()
    fold(
        {f -> second.fold(
            {s -> return Result.success(f to s)},
            {s -> return Result.failure(s) }
        )},
        {f -> second.fold(
            {s -> return Result.failure(f)},
            {s -> return Result.failure(ThrowableAggregate(f, s)) }
        )}
    )
}

class ThrowableAggregate(vararg val exceptions: Throwable) :
    Exception("Throwable Aggregate:\n" + exceptions.joinToString("\n"))

inline fun <reified E, R> Result<R>.recover(transform: (E) -> R): Result<R> {
    return this.recoverCatching {
        if (it is E) {
            return@recoverCatching transform(it)
        } else {
            throw it
        }
    }
}