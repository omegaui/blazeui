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

import blazeui.PaintBoard
import java.awt.Font
import java.awt.image.BufferedImage

class Button(buttonText: String) : AbstractBlazeComponent(buttonText){

	constructor(contentImage: BufferedImage) : this("") {
		initialize(contentImage)
	}

	constructor(content: String, contentImage: BufferedImage) : this(content) {
		initialize(content, contentImage)
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
			if(focussed){
				it.color = focussedStateColor
				it.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
			}
		}

	}

	override fun setFont(font: Font){
		super.setFont(font)
		computePrefDimensions()
		repaint()
	}
}





