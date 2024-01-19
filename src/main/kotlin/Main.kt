
package org.example

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {

    // input : > java Main -in sky.png -out sky_negative.png

    val arguments = args.toList()
    val input = arguments[arguments.indexOf("-in") + 1]
    val output = arguments[arguments.indexOf("-out") + 1]

    val imageInput = ImageIO.read(File(input))

    val energy = Array(imageInput.width) { Array(imageInput.height) { 0.0 } }

    for (x in 0 until imageInput.width) {
        for (y in 0 until imageInput.height) {
            energy[x][y] = calculateEnergy(x, y, imageInput)
        }
    }

    val maxEnergy = energy.maxOfOrNull { it.maxOrNull()!! }

    for (w in 0 until imageInput.width) {
        for (h in 0 until imageInput.height) {
            val energy = energy[w][h]
            val intensity = (255.0 * energy / maxEnergy!!).toInt()
            val alpha = Color(imageInput.getRGB(w, h)).alpha
            imageInput.setRGB(w, h, Color(intensity, intensity, intensity, alpha).rgb)
        }
    }

    val outputFileImage = File(output)
    ImageIO.write(imageInput, "png", outputFileImage)
}

fun calculateEnergy(x: Int, y: Int, imageInput: BufferedImage): Double {
    val posix = when (x) {
        0 -> 1
        imageInput.width - 1 -> imageInput.width - 2
        else -> x
    }

    val posiy = when (y) {
        0 -> 1
        imageInput.height - 1 -> imageInput.height - 2
        else -> y
    }

    /**
     * E(0,2)=Δ2x(1,2)+Δ2y(0,2)‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾√
     */

    val colorX1 = Color(imageInput.getRGB(posix - 1, y))
    val colorX2 = Color(imageInput.getRGB(posix + 1, y))
    val colorY1 = Color(imageInput.getRGB(x, posiy - 1))
    val colorY2 = Color(imageInput.getRGB(x, posiy + 1))

    val deltaX = (colorX1.red - colorX2.red).toDouble().pow(2.0) +
            (colorX1.green - colorX2.green).toDouble().pow(2.0) +
            (colorX1.blue - colorX2.blue).toDouble().pow(2.0)

    val deltaY = (colorY1.red - colorY2.red).toDouble().pow(2.0) +
            (colorY1.green - colorY2.green).toDouble().pow(2.0) +
            (colorY1.blue - colorY2.blue).toDouble().pow(2.0)

    return sqrt(deltaX + deltaY)
}

