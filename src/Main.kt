import kotlin.random.Random

fun ramdom(cifras:Int):Int{
    val numeroAleatorio = (0 until cifras)
        .map { Random.nextInt(0, 10) }  // Genera números aleatorios entre 0 y 9
        .joinToString("")                 // Une los números en un string
        .toInt()
    return numeroAleatorio
}

fun main() {
    val cifras = 4
    val numeroAleatorio = ramdom(cifras)


}