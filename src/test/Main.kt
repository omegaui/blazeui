/**
 * Just a Test Class for testing Blaze UI!
 */

package test

import java.awt.*

import javax.swing.*

import blazeui.component.Comp

fun main(){
	val comp1 = Comp("Hello!")
	val comp2 = Comp("There.")
	val comp3 = Comp("Disabled Comp")
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
}





