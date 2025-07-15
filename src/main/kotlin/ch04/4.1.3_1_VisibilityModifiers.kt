package ch04.ex1_3_1_VisibilityModifiers

// 代码清单 P.75
interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

internal fun TalkativeButton.giveSpeech() {
//    yell()
//    whisper()
}