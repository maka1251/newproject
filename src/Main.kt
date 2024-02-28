import kotlin.random.Random

class GuessNumberGame(private val isComputerGuessing: Boolean = false) {

    private var secretNumber = 0

    init {
        if (!isComputerGuessing) {
            secretNumber = Random.nextInt(0, 101)
        }
    }

    fun playGame() {
        if (isComputerGuessing) {
            computerGuess()
        } else {
            playerGuess()
        }
    }

    private fun playerGuess() {
        println("Гра 'Вгадай число' розпочалася! Загадане число від 0 до 100.")
        var playerGuess: Int
        do {
            print("Твій варіант: ")
            playerGuess = readlnOrNull()?.toIntOrNull() ?: -1
            if (playerGuess in 0..100) {
                if (playerGuess == secretNumber) {
                    println("Вітаю! Ти вгадав число. Гравці міняються місцями.")
                    return
                } else {
                    println(if (playerGuess < secretNumber) "Більше" else "Менше")
                }
            } else {
                println("Введи число від 0 до 100.")
            }
        } while (true)
    }

    private fun computerGuess() {
        println("Гра 'Вгадай число' розпочалася! Загадайте число від 0 до 100.")
        var lowerBound = 0
        var upperBound = 100
        do {
            val computerGuess = (lowerBound + upperBound) / 2
            println("Мій варіант: $computerGuess")
            print("Вказати (б), менше (м) чи більше (б)? ")
            when (readlnOrNull()?.trim()?.lowercase()) {
                "б" -> lowerBound = computerGuess + 1
                "м" -> upperBound = computerGuess - 1
                else -> {
                    println("Невірний ввід. Використовуй 'б', 'м' або 'в'.")
                    continue
                }
            }
            if (lowerBound > upperBound) {
                println("Ви зайвий раз говорите неправду! Загадане число в діапазоні ($lowerBound-$upperBound).")
                return
            }
        } while (true)
    }
}

fun main() {
    println("Оберіть режим гри: 1 - Гравець вгадує, 2 - Комп'ютер вгадує")
    when (readlnOrNull()?.toIntOrNull()) {
        1 -> GuessNumberGame().playGame()
        2 -> GuessNumberGame(isComputerGuessing = true).playGame()
        else -> println("Невірний ввід. Оберіть 1 або 2.")
    }
}
