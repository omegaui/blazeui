/**
 * Just a Test Class for testing Blaze UI!
 */

package test

import blazeui.component.Button
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel

fun main(){
	val comp1 = Button("Hello!")
	
	val comp2 = Button("There.")
	comp2.textAlignment = comp2.TEXT_ALIGNMENT_LEFT
	
	val comp3 = Button("Disabled Button")
	comp3.isEnabled = false
	
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
		isVisible = true
	}
}





