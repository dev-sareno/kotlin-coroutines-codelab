package episode

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    test1()
}

private suspend fun test1() {
    val channel = Channel<Int>()
    GlobalScope.launch {
        for (i in 1..5) {
            delay(1000)
            channel.send(i)
        }
    }
    repeat(5) {
        println(channel.receive())
    }
    println("Done")
}

private suspend fun test2() {
    val channel = Channel<Int>()
    GlobalScope.launch {
        for (i in 6..10) channel.send(i)
        channel.close()
    }

    for (i in channel) println(i) // Print until the channel is closed
    println("Done")
}