/*
 * The Abstract Component of Blaze UI
 * This is the parent of all UI Elements except the Text Input Components
  
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
import blazeui.UIProvider
import blazeui.listener.ValidationTask
import java.awt.*
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import javax.swing.JComponent

abstract class AbstractBlazeComponent() : JComponent(){
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

    var imagePaintBoard: PaintBoard = PaintBoard {}
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

    var failedValidationPaintBoard: PaintBoard = PaintBoard {}
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

    var failedValidationColor: Color = UIProvider.failedValidationColor
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

    val PAINT_NO_CONTENT = -1
    val PAINT_TEXT_ONLY = 0
    val PAINT_IMAGE_ONLY = 1
    val PAINT_BOTH_TEXT_AND_IMAGE = 2

    var currentPaintMode: Int = PAINT_NO_CONTENT

    var horizontalPadding: Int = 20
        set(value){
            field = value
            computePrefDimensions()
            repaint()
        }
    var verticalPadding: Int = 10
        set(value){
            field = value
            computePrefDimensions()
            repaint()
        }

    var arcWidth: Int = 0
        set(value){
            field = value
            repaint()
        }

    var arcHeight: Int = 0
        set(value){
            field = value
            repaint()
        }

    var autoComputeDimensions: Boolean = true
        set(value){
            field = value
            repaint()
        }

    var useImageDimensionsFromObject: Boolean = true
        set(value){
            field = value
            repaint()
        }

    var text: String = ""
        set(value){
            field = value
            computePrefDimensions()
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

    val TEXT_ALIGNMENT_CENTER: Int = 0
    val TEXT_ALIGNMENT_RIGHT: Int = 1
    val TEXT_ALIGNMENT_LEFT: Int = 2

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

    var image: BufferedImage = UIProvider.image
        set(value){
            field = value
            computePrefDimensions()
            repaint()
        }

    var imageX: Int = 0
    var imageY: Int = 0
    var imageWidth: Int = 0
        get() = if (useImageDimensionsFromObject) image.width else field
    var imageHeight: Int = 0
        get() = if (useImageDimensionsFromObject) image.height else field

    var scaledImageInstance: Image = image

    var imageLeftAlignmentMargin: Int = 5
        set(value){
            field = value
            repaint()
        }

    var imageRightAlignmentMargin: Int = 5
        set(value){
            field = value
            repaint()
        }

    val IMAGE_ALIGNMENT_CENTER: Int = 0
    val IMAGE_ALIGNMENT_RIGHT: Int = 1
    val IMAGE_ALIGNMENT_LEFT: Int = 2

    var imageAlignment: Int = IMAGE_ALIGNMENT_CENTER
        set(value){
            field = value
            repaint()
        }

    var customImageX: Int = -1
        set(value){
            field = value
            repaint()
        }

    var mouseInside: Boolean = false
    var mousePress: Boolean = false
    var mouseClick: Boolean = false
    var focussed: Boolean = false

    var validationTask: ValidationTask = ValidationTask { true }

    var lastValidationResult: Boolean = true

    constructor(content: String) : this() {
        initialize(content)
    }

    constructor(contentImage: BufferedImage) : this() {
        initialize(contentImage)
    }

    constructor(contentImage: BufferedImage, imageSize: Int) : this() {
        initialize(contentImage, imageSize)
    }

    constructor(content: String, contentImage: BufferedImage) : this() {
        initialize(content, contentImage)
    }

    constructor(content: String, contentImage: BufferedImage, imageSize: Int) : this() {
        initialize(content, contentImage, imageSize)
    }

    fun initialize(content: String) {
        text = content
    }

    fun initialize(contentImage: BufferedImage) {
        image = contentImage
        scaledImageInstance = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)
    }

    fun initialize(content: String, contentImage: BufferedImage) {
        text = content
        image = contentImage
        scaledImageInstance = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)
        imageAlignment = IMAGE_ALIGNMENT_LEFT
    }

    fun initialize(contentImage: BufferedImage, imageSize: Int) {
        image = contentImage
        useImageDimensionsFromObject = false
        imageWidth = imageSize
        imageHeight = imageSize
        scaledImageInstance = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)
        computePrefDimensions()
    }

    fun initialize(content: String, contentImage: BufferedImage, imageSize: Int) {
        text = content
        image = contentImage
        useImageDimensionsFromObject = false
        imageWidth = imageSize
        imageHeight = imageSize
        scaledImageInstance = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)
        imageAlignment = IMAGE_ALIGNMENT_LEFT
        computePrefDimensions()
    }

    init {
        font = UIProvider.defaultFont

        backgroundPaintBoard = PaintBoard {
            it.color = if (isEnabled) backgroundColor else disabledStateBackgroundColor
            it.fillRoundRect(0, 0, width, height, arcWidth, arcHeight)
        }

        textPaintBoard = PaintBoard {
            textWidth = UIProvider.computeWidth(font, text)
            textHeight = UIProvider.computeHeight(font)

            textY = height/2 - textHeight/2 + UIProvider.computeAscentDescent(font)
            if(customTextX == -1){
                if(textAlignment == TEXT_ALIGNMENT_CENTER){
                    if(currentPaintMode == PAINT_BOTH_TEXT_AND_IMAGE){
                        if(imageAlignment == IMAGE_ALIGNMENT_LEFT)
                            textX = imageLeftAlignmentMargin + imageWidth + textHeight/2
                        else if(imageAlignment == IMAGE_ALIGNMENT_RIGHT)
                            textX = width - (textWidth + imageRightAlignmentMargin + imageWidth + textHeight/2)
                    }
                    else
                        textX = width/2 - textWidth/2
                }
                else if(textAlignment == TEXT_ALIGNMENT_LEFT){
                    textX = textLeftAlignmentMargin
                }
                else if(textAlignment == TEXT_ALIGNMENT_RIGHT){
                    textX = width - textWidth - textRightAlignmentMargin
                }
            }
            else
                textX = customTextX

            it.color = if (isEnabled) foregroundColor else disabledStateForegroundColor
            it.font = font
            it.drawString(text, textX, textY)
        }

        imagePaintBoard = PaintBoard {
            imageY = height/2 - imageHeight/2
            if(customImageX == -1){
                if(imageAlignment == IMAGE_ALIGNMENT_CENTER){
                    imageX = width/2 - imageWidth/2
                }
                else if(imageAlignment == IMAGE_ALIGNMENT_LEFT){
                    imageX = imageLeftAlignmentMargin
                }
                else if(imageAlignment == IMAGE_ALIGNMENT_RIGHT){
                    imageX = width - imageWidth - imageRightAlignmentMargin
                }
            }
            else
                imageX = customImageX

            it.drawImage(scaledImageInstance, imageX, imageY, imageWidth, imageHeight, this)
        }

        this.addMouseListener(object: MouseAdapter(){
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
                mouseClick = false
                repaint()
            }

            override fun mouseClicked(e: MouseEvent){
                if(!isEnabled)
                    return
                if(isFocusable){
                    grabFocus()
                    focussed = true
                    mouseClick = true
                }
                repaint()
            }
        })
        this.addFocusListener(object: FocusAdapter(){
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
                mouseClick = false
                repaint()
            }
        })
    }

    override fun setFont(font: Font){
        super.setFont(font)
        computePrefDimensions()
    }

    fun prepareGraphics(abstractGraphics: Graphics) : Graphics2D{
        val g: Graphics2D = abstractGraphics as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
        return g
    }

    override fun paintComponent(abstractGraphics: Graphics){
        performPrePaintOperations()

        val g: Graphics2D = prepareGraphics(abstractGraphics)

        paintBackground(g)

        currentPaintMode = getPaintMode()
        if(currentPaintMode != PAINT_NO_CONTENT) {
            if(currentPaintMode == PAINT_TEXT_ONLY)
                paintText(g)
            else if(currentPaintMode == PAINT_IMAGE_ONLY)
                paintImage(g)
            else {
                paintText(g)
                paintImage(g)
            }
        }

        if(isEnabled){
            paintHoverEvent(g)
            paintPressEvent(g)
            paintFocusEvent(g)
        }

        paintExtra(g)

        super.paintComponent(g)

        performPostPaintOperations()
    }

    abstract fun performPrePaintOperations()
    abstract fun performPostPaintOperations()
    abstract fun paintExtra(g: Graphics2D)

    fun paintBackground(g: Graphics2D){
        backgroundPaintBoard.paint(g)
    }

    fun paintText(g: Graphics2D){
        textPaintBoard.paint(g)
    }

    fun paintImage(g: Graphics2D){
        imagePaintBoard.paint(g)
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

    fun paintValidation(g: Graphics2D){
        if(!lastValidationResult)
            failedValidationPaintBoard.paint(g)
    }

    fun getValidationResult() : Boolean {
        return validationTask.performValidation(this)
    }

    open fun computePrefDimensions(){
        if(autoComputeDimensions){
            currentPaintMode = getPaintMode()
            if(currentPaintMode == PAINT_TEXT_ONLY) {
                size = Dimension(
                    UIProvider.computeWidth(font, text) + horizontalPadding,
                    UIProvider.computeHeight(font) + verticalPadding
                )
                preferredSize = size
            }
            else if(currentPaintMode == PAINT_IMAGE_ONLY){
                size = Dimension(
                    imageWidth + horizontalPadding,
                    imageHeight + verticalPadding
                )
                preferredSize = size
            }
            else if(currentPaintMode == PAINT_BOTH_TEXT_AND_IMAGE){
                size = Dimension(
                    UIProvider.computeWidth(font, text) + imageWidth + horizontalPadding,
                    UIProvider.computeHeight(font) + imageHeight + verticalPadding
                )
                preferredSize = size
            }
        }
    }

    fun getPaintMode() : Int {
        if(text.isBlank() && image == UIProvider.image)
            return PAINT_NO_CONTENT
        else if(!text.isBlank() && image == UIProvider.image)
            return PAINT_TEXT_ONLY
        else if(text.isBlank() && image != UIProvider.image)
            return PAINT_IMAGE_ONLY
        return PAINT_BOTH_TEXT_AND_IMAGE
    }

}