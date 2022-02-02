/**
 * Just a Test Class for testing Blaze UI!
 */

package test

import java.awt.*

import javax.swing.JFrame
import javax.swing.BorderFactory

import blazeui.component.Button

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
		layout = FlowLayout()
		add(comp1)
		add(comp2)
		add(comp3)
		isVisible = true
	}

	val thread = Thread({
		try{
			Thread.sleep(2000)
		}
		catch(e: Exception){
			e.printStackTrace()
		}
		comp1.text = "Hi!"
	})
	thread.start()
}





