package episode

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
//    var cur = numbersFrom(2)
//    for (i in 1..10) {
//        val prime = cur.receive()
//        println(prime)
//        cur = filter(cur, prime)
//    }
//    coroutineContext.cancelChildren() // cancel all children to let main finish

    val cur = numbersFrom(2)
    for (i in cur) println(i)
    coroutineContext.cancelChildren() // cancel all children to let main finish
}

fun CoroutineScope.numbersFrom(start: Int): ReceiveChannel<Int> {
    return this.produce<Int> {
        var x = start
        while (true) {
            delay(500)
            this.send(x++) // infinite stream of integers from start
        }
    }
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers) if (x % prime != 0) send(x)
}