/*
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

import blazeui.paint.PaintBoard
import java.awt.Graphics2D
import java.awt.image.BufferedImage

import java.awt.event.MouseEvent
import java.awt.event.MouseAdapter

class Button(buttonText: String) : AbstractBlazeComponent(buttonText){

	var onClick: Runnable = Runnable {}	

	constructor(contentImage: BufferedImage) : this("") {
		initialize(contentImage)
	}

	constructor(content: String, contentImage: BufferedImage) : this(content) {
		initialize(content, contentImage)
	}

	constructor(contentImage: BufferedImage, imageSize: Int) : this("") {
		initialize(contentImage, imageSize)
	}

	constructor(content: String, contentImage: BufferedImage, imageSize: Int) : this(content) {
		initialize(content, contentImage, imageSize)
	}

	init{
		arcWidth = 6
		arcHeight = 6

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
			if(focussed && lastValidationResult){
				it.color = focussedStateColor
				it.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
			}
		}

		failedValidationPaintBoard = PaintBoard {
			it.color = failedValidationColor
			it.fillRoundRect(0, 0, width - 1, height - 1, arcWidth, arcHeight)
		}

		addMouseListener(object: MouseAdapter(){
			override fun mouseClicked(e: MouseEvent){
				if(e.clickCount == 1 && e.button == 1)
					onClick.run()
			}
		})
	}

	override fun performPrePaintOperations() {
		if(mouseClick)
			lastValidationResult = getValidationResult()
	}

	override fun performPostPaintOperations() {
		if(mouseInside && !lastValidationResult)
			lastValidationResult = true
	}

	override fun paintExtra(g: Graphics2D) {
		paintValidation(g)
	}
}





