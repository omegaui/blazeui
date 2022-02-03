/**
 * Just a Test Class for testing Blaze UI!
 */

package test

import blazeui.UIProvider
import blazeui.component.Button
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JPanel

fun getImage(name: String) : BufferedImage {
	try {
	    return ImageIO.read(object {}.javaClass.getResourceAsStream("/${name}"))
	}
	catch (e: Exception){
		e.printStackTrace()
	}
	return UIProvider.image
}

fun main(){

	val comp1 = Button(getImage("icons8-dart-48.png"), 24)
	comp1.horizontalPadding = 6
	comp1.verticalPadding = 6

	JFrame("Test Blaze UI").apply {

		size = Dimension(300, 300)
		setLocationRelativeTo(null)
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		contentPane = JPanel(FlowLayout()).apply{
			background = Color.WHITE
		}

		add(comp1)
		isVisible = true

	}
}





