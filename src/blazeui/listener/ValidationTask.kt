package blazeui.listener

fun interface ValidationTask {
    fun performValidation(component: Any) : Boolean
}