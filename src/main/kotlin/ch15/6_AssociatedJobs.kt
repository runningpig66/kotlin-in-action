package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.*

/**
 * @author runningpig66
 * @date 2026-03-27
 * @time 21:58
 * 15.1.4 Coroutine contexts and structured concurrency
 * 15.1.4 协程上下文与结构化并发
 * {java -Dkotlinx.coroutines.debug ch15._6_AssociatedJobsKt}
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking(CoroutineName("A")) {
    log("A's job: ${coroutineContext.job}")
    launch(CoroutineName("B")) {
        log("B's job: ${coroutineContext.job}")
        log("B's parent: ${coroutineContext.job.parent}")
    }
    log("A's children: ${coroutineContext.job.children.toList()}")
}
/* Output: // 标注格式："协程名称#ID":具体实现类名{当前内部状态}@内存哈希地址
0 [main @A#1] A's job: "A#1":BlockingCoroutine{Active}@68f7aae2
24 [main @A#1] A's children: ["B#2":StandaloneCoroutine{Active}@5f150435]
26 [main @B#2] B's job: "B#2":StandaloneCoroutine{Active}@5f150435
26 [main @B#2] B's parent: "A#1":BlockingCoroutine{Completing}@68f7aae2
 */
