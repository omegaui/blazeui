package blazeui.paint

import java.awt.Color

class PixelColor(r: Int, g: Int, b: Int) : Color(r, g, b) {

    fun withOpacity(opacity: Float) : Color {
        return Color(red, green, blue, (opacity * 255).toInt())
    }

    fun withRed(r: Int) : Color {
        return Color(r, green, blue)
    }

    fun withGreen(g: Int) : Color {
        return Color(red, g, blue)
    }

    fun withBlue(b: Int) : Color {
        return Color(red, green, b)
    }

}
