/**
 * The Button Component of Blaze UI
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

import blazeui.PaintBoard

import blazeui.UIProvider.Companion as UIProvider

class Button(buttonText: String) : JComponent(){

	var text: String = buttonText
		set(value){
			field = value
			computePrefDimensions()
			repaint()
		}

	var backgroundPaintBoard: PaintBoard = PaintBoard {}
		set(value){
			field = value
			repaint()
		}
	
	var textPaintBoard: PaintBoard = PaintBoard {}
		set(value){
			field = value
			repaint()
		}
	
	var hoverEventPaintBoard: PaintBoard = PaintBoard {}
		set(value){
			field = value
			repaint()
		}
	
	var pressEventPaintBoard: PaintBoard = PaintBoard {}
		set(value){
			field = value
			repaint()
		}
	
	var focusEventPaintBoard: PaintBoard = PaintBoard {}
		set(value){
			field = value
			repaint()
		}
	
	var backgroundColor: Color = UIProvider.backgroundColor
		set(value){
			field = value
			repaint()
		}
	
	var foregroundColor: Color = UIProvider.foregroundColor
		set(value){
			field = value
			repaint()
		}
	
	var hoverStateColor: Color = UIProvider.hoverStateColor
		set(value){
			field = value
			repaint()
		}
	
	var pressedStateColor: Color = UIProvider.pressedStateColor
		set(value){
			field = value
			repaint()
		}
	
	var focussedStateColor: Color = UIProvider.focussedStateColor
		set(value){
			field = value
			repaint()
		}
	
	var disabledStateBackgroundColor: Color = UIProvider.disabledStateBackgroundColor
		set(value){
			field = value
			repaint()
		}
	
	var disabledStateForegroundColor: Color = UIProvider.disabledStateForegroundColor
		set(value){
			field = value
			repaint()
		}
	
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
	
	var textLeftAlignmentMargin: Int = 5
		set(value){
			field = value
			repaint()
		}
	
	var textRightAlignmentMargin: Int = 5
		set(value){
			field = value
			repaint()
		}

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
			computePrefDimensions()
			repaint()
		}

	init{
		font = UIProvider.defaultFont
	
		backgroundPaintBoard = PaintBoard {
			it.color = if (isEnabled) backgroundColor else disabledStateBackgroundColor
			it.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
		}

		textPaintBoard = PaintBoard {
			textWidth = UIProvider.computeWidth(font, text)
			textHeight = UIProvider.computeHeight(font)
			
			if(customTextX == -1){
				textY = height/2 - textHeight/2 + UIProvider.computeAscentDescent(font)
				if(textAlignment == TEXT_ALIGNMENT_CENTER){
					textX = width/2 - textWidth/2
				}
				else if(textAlignment == TEXT_ALIGNMENT_LEFT){
					textX = textLeftAlignmentMargin
				}
				else if(textAlignment == TEXT_ALIGNMENT_RIGHT){
					textX = width - textWidth - textRightAlignmentMargin
				}
			}
			
			it.color = if (isEnabled) foregroundColor else disabledStateForegroundColor
			it.font = font
			it.drawString(text, textX, textY)
		}

		hoverEventPaintBoard = PaintBoard {
			if(mouseInside){
				it.color = hoverStateColor
				it.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
			}
		}

		pressEventPaintBoard = PaintBoard {
			if(mousePress){
				it.color = pressedStateColor
				it.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
			}
		}

		focusEventPaintBoard = PaintBoard {
			if(focussed){
				it.color = focussedStateColor
				it.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
			}
		}
		
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
		val g: Graphics2D = abstractGraphics as Graphics2D
		
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

	fun paintBackground(g: Graphics2D){
		backgroundPaintBoard.paint(g)
	}

	fun paintText(g: Graphics2D){
		textPaintBoard.paint(g)
	}

	fun paintHoverEvent(g: Graphics2D){
		hoverEventPaintBoard.paint(g)
	}

	fun paintPressEvent(g: Graphics2D){
		pressEventPaintBoard.paint(g)
	}
	
	fun paintFocusEvent(g: Graphics2D){
		focusEventPaintBoard.paint(g)
	}
	
	fun computePrefDimensions(){
		if(autoComputeDimensions){
			size = Dimension(UIProvider.computeWidth(font, text) + horizontalPadding, UIProvider.computeHeight(font) + verticalPadding)
			preferredSize = size
		}
	}
	
	override fun setFont(font: Font){
		super.setFont(font)
		computePrefDimensions()
		repaint()
	}
}





