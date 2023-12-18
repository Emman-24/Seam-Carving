package org.example

import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class CreateImage(width: Int, height: Int, nameImage: String) {

    init {
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = image.createGraphics()
        rectX(width, height, graphics)
        val imageFile = File(nameImage)
        saveImage(image, imageFile)
    }

    private fun rectX(width: Int, height: Int, graphics: Graphics2D) {
        graphics.color = Color.RED
        graphics.drawLine(0, 0, width - 1, height - 1)
        graphics.drawLine(0, height - 1, width - 1, 0)
    }

    private fun saveImage(image: BufferedImage, imageFile: File) {
        ImageIO.write(image, "png", imageFile)
    }
}