package org.example

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {

    // input : > java Main -in sky.png -out sky_negative.png

    val arguments = args.toList()
    val input = arguments[arguments.indexOf("-in") + 1]
    val output = arguments[arguments.indexOf("-out") + 1]

    val imageInput = ImageIO.read(File(input))

    for (x in 0 until imageInput.width) {
        for (y in 0 until imageInput.height) {
            val color = Color(imageInput.getRGB(x, y))
            val g = color.green
            val r = color.red
            val b = color.blue

            val newColor = Color(255 - r, 255 - g, 255 - b)
            imageInput.setRGB(x, y, newColor.rgb)
        }
    }

    val outputFileImage = File(output)
    ImageIO.write(imageInput, "png", outputFileImage)
}