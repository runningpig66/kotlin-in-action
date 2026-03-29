package coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @author runningpig66
 * @date 2026-03-25
 * @time 1:28
 */
fun main() {
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            /*val forecast: Deferred<String> = async {
                getForecast()
            }
            val temperature = async {
                getTemperature()
            }
            println("${forecast.await()} ${temperature.await()}")*/
            println(getWeatherReport())
            println("Have a good day!")
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}

suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast(): String {
    delay(3000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(2000)
    return "30\u00b0C"
}
/* Output:
Weather forecast
Sunny 30°C
Have a good day!
Execution time: 3.1 seconds
 */
