package io.github.coden.telegram.run

import java.io.Closeable

interface Console: Closeable {
    fun start()
    fun stop()
    override fun close() { stop() }
}