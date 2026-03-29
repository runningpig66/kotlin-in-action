package ch15

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-29
 * @time 23:31
 * 15.2.8 Keep cancellation in mind when acquiring resources
 * 15.2.8 获取资源时请牢记取消机制
 */
class DatabaseConnection : AutoCloseable {
    fun write(s: String) = println("writing $s!")
    override fun close() {
        println("Closing!")
    }
}

fun main() {
    runBlocking {
        // Listing 15.6 Using a finally block to close resources
        /*val dbTask = launch {
            val db = DatabaseConnection()
            try {
                delay(500.milliseconds)
                db.write("I love coroutines!")
            } finally {
                db.close()
            }
        }*/

        // Listing 15.7 Using use to automatically close resources
        val dbTask = launch {
            DatabaseConnection().use {
                delay(500.milliseconds)
                it.write("I love coroutines!")
            }
        }

        delay(200.milliseconds)
        dbTask.cancel()
    }
    println("I leaked a resource!")
}
/* Output:
Closing!
I leaked a resource!
 */
