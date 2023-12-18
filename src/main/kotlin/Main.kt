package org.example

fun main() {

    println("Enter rectangle width:")
    val width = readln().toInt()
    println("Enter rectangle height:")
    val height = readln().toInt()
    println("Enter output image name:")
    val nameImage = readln()

    CreateImage(width, height, nameImage)
}