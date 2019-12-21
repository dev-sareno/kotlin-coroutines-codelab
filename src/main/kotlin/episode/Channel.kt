package episode

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Channel {

    suspend fun test1() {
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

    suspend fun test2() {
        val channel = Channel<Int>()
        GlobalScope.launch {
            for (i in 6..10) channel.send(i)
            channel.close()
        }

        for (i in channel) println(i) // Print until the channel is closed
        println("Done")
    }

}