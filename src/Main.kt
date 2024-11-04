import java.io.File
import kotlin.random.Random
import kotlin.system.exitProcess

/* Códigos de color de fondo
const val BG_BLACK = "\u001B[40m"
const val BG_RED = "\u001B[41m"
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val BG_BLUE = "\u001B[44m"
const val BG_PURPLE = "\u001B[45m"
const val BG_CYAN = "\u001B[46m"
const val BG_WHITE = "\u001B[47m"
// Colores ANSI básicos
const val RESET = "\u001B[0m"
const val BLACK = "\u001B[30m"
const val RED = "\u001B[31m"
const val GREEN = "\u001B[32m"
const val YELLOW = "\u001B[33m"
const val BLUE = "\u001B[34m"
const val PURPLE = "\u001B[35m"
const val CYAN = "\u001B[36m"
const val WHITE = "\u001B[37m"
const val BOLD = "\u001B[1m"
const val UNDERLINE = "\u001B[4m"*/

fun random(cifras:Int, numeroInicio:Int, numeroFinal:Int):String{
    // Usamos un Set para asegurarnos de que no haya números repetidos
    val numerosGenerados = mutableSetOf<Int>()

    while (numerosGenerados.size < cifras) {
        // Rango de 0-10 (incluye 0 y excluye el 10)
        val ramdonNumber = Random.nextInt(numeroInicio, numeroFinal)

        // Agregamos el número al Set; si ya existe, no lo añade
        numerosGenerados.add(ramdonNumber)
    }

    // Convertimos el Set a una lista, la unimos y la convertimos a Int
    val numeroAleatorio = numerosGenerados.joinToString("")
    File("src/intentos.txt").writeText("Numero secreto:$numeroAleatorio")
    return numeroAleatorio
}

fun juego(numeroAleatorio:String, intentos:Int, cifras:Int, numeroInicio: Int, numeroFinal: Int):String {
    println()
    println("El juego consisten en adivinar un número de $cifras cifras cada cifra puede contener un numero del $numeroInicio al ${numeroFinal-1} y tendras $intentos intentos.")
    println()

    for (i in intentos downTo 1) {
        println("Te quedan $i intentos")
        print("escribe un número de $cifras cifras sin números repetidos:")
        val numeroEscrito = readln()
        val resultado =(resultadoJuego(numeroAleatorio,numeroEscrito, cifras))

        if (resultado.contains(numeroAleatorio)){
            return "$resultado con ${i-1} intentos sobrantes"
        }
        println(resultado)
        File("src/intentos.txt").appendText("Intento $i:$numeroInicio, $resultado")
        println()
    }
    return "Perdiste el numero aleatorio es $numeroAleatorio"
}

fun resultadoJuego(numeroAleatorio: String, numeroEscrito:String, cifras:Int):String{
    var contadorAciertos = 0
    var contadorContiene = 0

    //forEachIndexed guarda el indice y valor de numero escrito ejemplo: 4321 index de 4 = 0 y valor de 4 = 4
    numeroEscrito.forEachIndexed { index, valor ->
        //Si el valor es igual al valor del numero aleatorio en la pocicion del indice
        if (valor == numeroAleatorio[index]) {
            contadorAciertos++
            //De lo contrario se comprueba si el valor de numeroEscrito esta en numeroAleatorio
        } else if (numeroAleatorio.contains(valor)) {
            contadorContiene++
        }
    }
    return if (contadorAciertos == cifras){
        "Correcto el numero aleatorio es $numeroAleatorio"
    } else {
        "Correcto:$contadorAciertos, Correcto en posición incorrecta:$contadorContiene"
    }
}

fun main() {
    val cifras = 4
    val numeroInicio = 0
    val numeroFinal = 10
    val intentos = 4

    //println("${GREEN}")

    /*println("$numeroAleatorio Borrar en la version final")*/

    println("1. Jugar")
    println("2. Intento anterior")
    println("3. Salir")
    val selector = readln().toInt()
    when (selector) {
        1 ->{
            val numeroAleatorio = random(cifras, numeroInicio, numeroFinal)
            println(juego(numeroAleatorio, intentos, cifras, numeroInicio, numeroFinal))
        }
        2 -> {
            println()
            val leer = File("src/intentos.txt").readText()
            println(leer)
            println()
            main()
        }
        3 -> exitProcess(0) // Finaliza el programa
    }
}