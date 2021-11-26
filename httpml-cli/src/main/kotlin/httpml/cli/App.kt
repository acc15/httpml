package httpml.cli

import LibraryClass

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    println(App().greeting)
    println(LibraryClass().value)
}
