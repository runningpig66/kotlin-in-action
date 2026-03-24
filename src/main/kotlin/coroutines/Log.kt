package kia2e.coroutines

/**
 * @author runningpig66
 * @date 3月18日 周三
 * @time 22:49
 * 14.6.2 Creating start-and-forget coroutines: The launch function
 * 14.6.2 创建“启动即忘记”协程：launch 函数
 */
private var zeroTime = System.currentTimeMillis()
fun log(message: Any?) =
    println("${System.currentTimeMillis() - zeroTime} " + "[${Thread.currentThread().name}] $message")
