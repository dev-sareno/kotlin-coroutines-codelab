package episode

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker
import kotlin.concurrent.thread

fun main() = runBlocking {
    val job = withTimeoutOrNull<String?>(1000) {
        for (i in 1..100) {
//            delay(500)
            println("$i")
        }
        "Success"
    }
    println(job ?: "Timeout!")
}