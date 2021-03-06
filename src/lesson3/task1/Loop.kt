@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.math.BigInteger
import kotlin.math.sqrt
import kotlin.math.truncate

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var i = 1.0
    while (n / Math.pow(10.0, i) >= 1) {
        i++
    }
    return i.toInt()
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(number: Int): Int {
    var n = 1
    var n_1 = 1
    for (i in 3..number) {
        val v = n
        n += n_1
        n_1 = v
    }
    return n
}

fun fib(n: Int, type: CalcType): Int = when (type) {
    CalcType.CYCLE -> fib(n)
    CalcType.RECURCE -> when {
        n < 3 -> 1
        else -> fib(n - 1) + fib(n - 2)
    }

}

enum class CalcType {
    CYCLE,
    RECURCE
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var lcm = Math.max(m, n)
    val step = Math.max(m, n)
    while (!(lcm % m == 0 && lcm % n == 0)) {
        lcm += step
    }
    return lcm
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var divider = 2
    while (n % divider != 0) {
        divider++
    }
    return divider
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var divider = n / 2
    while (n % divider != 0) {
        divider--
    }
    return divider

}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..Math.min(m, n)) {
        if (m % i == 0 && n % i == 0) {
            return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n) {
        if (Math.sqrt(i.toDouble()) - Math.round(Math.sqrt(i.toDouble())) == 0.0) {
            return true
        }
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var step = 0
    var next = x
    while (next != 1) {
        next = if (next % 2 == 0) next / 2 else 3 * next + 1
        step++
    }
    return step
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var n = 1
    var x1 = x
    if (x > 2 * Math.PI)
        x1 = x % (2 * Math.PI)

    var result = 0.0
    while (Math.pow(x1, n.toDouble()) / factorial(n) > eps) {
        result += (Math.pow(-1.0, (n - 1) / 2.0) * Math.pow(x1, n.toDouble()) / factorial(n))
        n += 2
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var n = 2
    var x1 = x
    if (x > 2 * Math.PI)
        x1 = x % (2 * Math.PI)

    var result = 1.0
    while (Math.pow(x1, n.toDouble()) / factorial(n) > eps) {
        result += (Math.pow(-1.0, n / 2.0) * Math.pow(x1, n.toDouble()) / factorial(n))
        n += 2
    }
    return result
}

/**
 * Подсчет количетва цифр в числе
 */

fun numberCount(n: Int): Int {
    var numberCount = 0
    while (n / Math.pow(10.0, numberCount.toDouble()) >= 1) {
        numberCount++
    }
    return numberCount
}

fun numberCount(n: Long): Int {
    var numberCount = 0
    while (n / Math.pow(10.0, numberCount.toDouble()) >= 1) {
        numberCount++
    }
    return numberCount
}

fun numberCount(n: BigInteger): Int {
    var numberCount = 0
    while (n.divide((BigInteger.TEN.pow((numberCount)))).compareTo(BigInteger.ONE) >= 0) {
        numberCount++
    }
    return numberCount
}

/**
 * Поиск цифры в числе по порядковому номеру справа налево
 */
fun getNumber(n: Int, number: Int): Int = (n / Math.pow(10.0, (number - 1).toDouble()) % 10).toInt()

fun getNumber(n: Long, number: Int): Int = (n / Math.pow(10.0, (number - 1).toDouble()) % 10).toInt()

fun getNumber(n: BigInteger, number: Int): Int =
    (n.divide((BigInteger.TEN.pow((number - 1)))).mod(BigInteger.TEN)).toInt()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var result = 0
    val numberCount = numberCount(n)
    for (i in 0..numberCount - 1) {
        result += (getNumber(n, numberCount - i) * Math.pow(10.0, (i).toDouble())).toInt()
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val numberCount = numberCount(n)
    var result = true
    for (i in 1..numberCount / 2) {
        if (getNumber(n, numberCount - i + 1) != getNumber(n, i))
            result = false
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val numberCount = numberCount(n)
    val firstNumber = getNumber(n, numberCount)
    for (i in 1..numberCount - 1) {
        if (getNumber(n, i) != firstNumber) {
            return true
        }
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var currentNumber: BigInteger = BigInteger.ONE
    var next = 2.0
    while (numberCount(currentNumber) < n) {
        val pow = Math.pow(next, 2.0)
        currentNumber =
            currentNumber.multiply(BigInteger.TEN.pow(numberCount(pow.toInt()))).add(pow.toBigDecimal().toBigInteger())
        next++
    }
    return getNumber(currentNumber, numberCount(currentNumber) - n + 1)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var currentNumber: BigInteger = BigInteger.ONE
    var next = 2
    while (numberCount(currentNumber) < n) {
        val pow = fib(next)
        currentNumber =
            currentNumber.multiply(BigInteger.TEN.pow(numberCount(pow))).add(pow.toBigDecimal().toBigInteger())
        next++
    }
    return getNumber(currentNumber, numberCount(currentNumber) - n + 1)
}
