package io.github.coden.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

val singleThreadScope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
