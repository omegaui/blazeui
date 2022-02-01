/**
 * Super Flexible UI Element -- In the Making
 * Copyright (C) 2022 Omega UI

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package blazeui.component

import java.awt.*

import javax.swing.*

import java.awt.event.*

import blazeui.UIProvider.Companion.computeWidth
import blazeui.UIProvider.Companion.computeHeight
import blazeui.UIProvider.Companion.computeAscentDescent

class Comp(val text: String) : JComponent(){
	
	var backgroundColor: Color
	var foregroundColor: Color
	var hoverStateColor: Color
	var pressedStateColor: Color
	var focussedStateColor: Color
	var disabledStateBackgroundColor: Color
	var disabledStateForegroundColor: Color

	var horizontalPadding: Int = 20
	var verticalPadding: Int = 10

	var arcWidth: Int = 6
		set(value){
			field = value
			repaint()
		}
	var arcHeight: Int = 6
		set(value){
			field = value
			repaint()
		}
	
	var textX: Int = 0
	var textY: Int = 0
	var textWidth: Int = 0
	var textHeight: Int = 0

	var TEXT_ALIGNMENT_CENTER: Int = 0
	var TEXT_ALIGNMENT_RIGHT: Int = 1
	var TEXT_ALIGNMENT_LEFT: Int = 2

	var textAlignment: Int = TEXT_ALIGNMENT_CENTER
		set(value){
			field = value
			repaint()
		}
	
	var customTextX: Int = -1
		set(value){
			field = value
			repaint()
		}

	var mouseInside: Boolean = false
	var mousePress: Boolean = false
	var focussed: Boolean = false
	
	var autoComputeDimensions: Boolean = true
		set(value){
			field = value
			repaint()
		}

	init{
		backgroundColor = blazeui.UIProvider.Companion.backgroundColor
		foregroundColor = blazeui.UIProvider.Companion.foregroundColor
		hoverStateColor = blazeui.UIProvider.Companion.hoverStateColor
		pressedStateColor = blazeui.UIProvider.Companion.pressedStateColor
		focussedStateColor = blazeui.UIProvider.Companion.focussedStateColor
		disabledStateBackgroundColor = blazeui.UIProvider.Companion.disabledStateBackgroundColor
		disabledStateForegroundColor = blazeui.UIProvider.Companion.disabledStateForegroundColor

		font = blazeui.UIProvider.Companion.defaultFont
	
		addMouseListener(object: MouseAdapter(){
			override fun mouseEntered(e: MouseEvent){
				if(!isEnabled)
					return
				mouseInside = true
				repaint()
			}
			
			override fun mouseExited(e: MouseEvent){
				if(!isEnabled)
					return
				mouseInside = false
				repaint()
			}
			
			override fun mousePressed(e: MouseEvent){
				if(!isEnabled)
					return
				mousePress = true
				repaint()
			}
			
			override fun mouseReleased(e: MouseEvent){
				if(!isEnabled)
					return
				mousePress = false
				repaint()
			}
			
			override fun mouseClicked(e: MouseEvent){
				if(!isEnabled)
					return
				if(isFocusable){
					grabFocus()
					focussed = true
				}
				repaint()
			}
		})
		addFocusListener(object: FocusAdapter(){
			override fun focusGained(e: FocusEvent){
				if(!isEnabled)
					return
				focussed = true
				repaint()
			}
			
			override fun focusLost(e: FocusEvent){
				if(!isEnabled)
					return
				focussed = false
				repaint()
			}
		})
	}

	override fun paintComponent(abstractGraphics: Graphics){
		computePrefDimensions()
		
		var g: Graphics2D = abstractGraphics as Graphics2D
		
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

		paintBackground(g)
		paintText(g)
		if(isEnabled){
			paintHoverEvent(g)
			paintPressEvent(g)
			paintFocusEvent(g)
		}

		super.paintComponent(g)
	}

	fun computePrefDimensions(){
		if(autoComputeDimensions){
			size = Dimension(computeWidth(font, text) + horizontalPadding, computeHeight(font) + verticalPadding)
			preferredSize = size
		}
	}

	fun paintBackground(g: Graphics2D){
		if(isEnabled)
			g.color = backgroundColor
		else
			g.color = disabledStateBackgroundColor
		g.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
	}

	fun paintText(g: Graphics2D){
		if(customTextX == -1){
			if(textAlignment == TEXT_ALIGNMENT_CENTER){
				textWidth = computeWidth(font, text)
				textHeight = computeHeight(font)
				textX = width/2 - textWidth/2
				textY = height/2 - textHeight/2 + computeAscentDescent(font)
			}
		}
		if(isEnabled)
			g.color = foregroundColor
		else
			g.color = disabledStateForegroundColor
		g.font = font
		g.drawString(text, textX, textY)
	}

	fun paintHoverEvent(g: Graphics2D){
		if(!mouseInside)
			return
		g.color = hoverStateColor
		g.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
	}

	fun paintPressEvent(g: Graphics2D){
		if(!mousePress)
			return
		g.color = pressedStateColor
		g.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
	}
	
	fun paintFocusEvent(g: Graphics2D){
		if(!focussed)
			return
		g.color = focussedStateColor
		g.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
	}
}





