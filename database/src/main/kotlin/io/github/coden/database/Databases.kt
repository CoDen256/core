package io.github.coden256.database
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

fun database(config: DatasourceConfig): Database {
    return Database.connect(
        url = config.url,
        user = config.user,
        password = config.password
    )
}

fun Instant.asDBInstant(): kotlinx.datetime.Instant{
    return kotlinx.datetime.Instant.fromEpochMilliseconds(toEpochMilli())
}

fun kotlinx.datetime.Instant.asInstant(): Instant{
    return Instant.ofEpochMilli(toEpochMilliseconds())
}

fun <T : Any> Database.transaction(query: Transaction.() -> T): Result<T> {
    return try {
        Result.success(transaction(this) { query() })
    } catch (e: Exception) {
        Result.failure(e)
    }
}