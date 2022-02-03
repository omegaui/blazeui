/**
 * Default UIProvider, provides default colors and fonts for blazeui 's components-- In the Making
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
 
package blazeui

import java.awt.*

import java.awt.image.*

import javax.swing.*

class UIProvider {

	companion object{
		var image: BufferedImage = BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB)

		var defaultFont: Font = Font("Ubuntu Mono", Font.BOLD, 14)

		var backgroundColor: Color = Color.decode("#ffffff")
		var foregroundColor: Color = Color.decode("#3c5181")
		var hoverStateColor: Color = Color(60, 81, 129, 60)
		var pressedStateColor: Color = Color(110, 94, 189, 60)
		var focussedStateColor: Color = Color(73, 208, 152, 60)
		var failedValidationColor: Color = Color(208, 73, 120, 60)
		var disabledStateBackgroundColor: Color = Color.decode("#bdbfbd")
		var disabledStateForegroundColor: Color = Color.decode("#636264")

		fun drawTextAtCenter(font: Font, g: Graphics2D, text: String, comp: JComponent){
			g.font = font
			g.drawString(text, comp.width/2 - computeWidth(font, text)/2, comp.height/2 - computeHeight(font)/2 + g.fontMetrics.ascent - g.fontMetrics.descent)
		}

		fun computeWidth(font: Font, text: String) : Int{
			var g: Graphics2D = image.graphics as Graphics2D
			
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

			g.font = font
			val width: Int = g.fontMetrics.stringWidth(text)
			g.dispose()
			return width
		}

		fun computeHeight(font: Font) : Int{
			var g: Graphics2D = image.graphics as Graphics2D
			
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

			g.font = font
			val height: Int = g.fontMetrics.height
			g.dispose()
			return height
		}
		
		fun computeAscentDescent(font: Font) : Int{
			var g: Graphics2D = image.graphics as Graphics2D
			
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

			g.font = font
			val ad: Int = g.fontMetrics.ascent - g.fontMetrics.descent + 1
			g.dispose()
			return ad
		}
	}

}





