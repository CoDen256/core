package io.github.coden256.telegram.run

import java.io.Closeable

interface Console: Closeable {
    fun start()
    fun stop()
    override fun close() { stop() }
}