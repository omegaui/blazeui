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
	val comp1 = Button("Button with Text at Center")
	
	val comp2 = Button("Button with Text at Left")
	comp2.textAlignment = comp2.TEXT_ALIGNMENT_LEFT
	
	val comp3 = Button("Disabled Button")
	comp3.isEnabled = false

	val comp4 = Button("Button with Custom textX")
	comp4.customTextX = 15

	val comp5 = Button(getImage("icons8-console-50.png"))

	val comp6 = Button("Unscaled Image", getImage("icons8-code-50.png"))

	val comp7 = Button("Scaled Image", getImage("icons8-energy-64.png"), 25)

	val comp8 = Button("Scaled Right Aligned Image", getImage("icons8-dart-48.png"), 25)
	comp8.imageAlignment = comp8.IMAGE_ALIGNMENT_RIGHT

	val comp9 = Button(getImage("icons8-chatbot-50.png"), 25)

	JFrame("Test Blaze UI").apply{

		size = Dimension(300, 300)
		setLocationRelativeTo(null)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		contentPane = JPanel(FlowLayout()).apply{
			background = Color.WHITE
		}

		add(comp1)
		add(comp2)
		add(comp3)
		add(comp4)
		add(comp5)
		add(comp6)
		add(comp7)
		add(comp8)
		add(comp9.also { it.isEnabled = false })
		isVisible = true
	}
}





