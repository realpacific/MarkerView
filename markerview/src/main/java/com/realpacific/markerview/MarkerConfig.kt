package com.realpacific.markerview

import androidx.annotation.ColorRes

/**
 * @author Prashant Barahi
 *
 * Marker configuration that defines the looks and behavior of a [MarkerView]
 */
class MarkerConfig {
    var text: Char? = null
        private set

    var hasPunchHole = true
        private set

    var markerColor = android.R.color.holo_red_dark
        private set

    var listener: DraggableListener? = null
        private set

    var size: Int = 70

    var textColor: Int = android.R.color.white

    /**
     * Creates a copy of [MarkerConfig]
     */
    fun clone(): MarkerConfig {
        val config = MarkerConfig()
        config.text = text
        config.hasPunchHole = hasPunchHole
        config.markerColor = markerColor
        config.listener = listener
        config.size = size
        config.textColor = textColor
        return config
    }

    /**
     * The full width of the [MarkerView] i.e. the diameter of the circular part.
     *
     * Default value is 70.
     */
    fun withSize(size: Int): MarkerConfig {
        this.size = size
        return this
    }

    /**
     * Creates a punch hole in the [MarkerView].
     *
     * When set to `true`, [MarkerView.text] will be null.
     *
     * Defaults to `true`
     */
    fun withPunchHole(): MarkerConfig {
        this.hasPunchHole = true
        this.text = null
        return this
    }

    /**
     * Sets the text on the [MarkerView].
     * @param value The text value
     * @param textColor The color of the text ands [android.R.color.white] by default
     */
    fun withText(value: Char, @ColorRes textColor: Int = this.textColor): MarkerConfig {
        this.text = value
        this.textColor = textColor
        this.hasPunchHole = false
        return this
    }

    /**
     * Makes [MarkerView] draggable
     */
    fun withDraggableListener(listener: DraggableListener): MarkerConfig {
        this.listener = listener
        return this
    }

    /**
     * Sets the background color of a [MarkerView]
     */
    fun withColor(@ColorRes color: Int): MarkerConfig {
        this.markerColor = color
        return this
    }

    internal fun build(): MarkerProperty {
        return MarkerProperty(
            text = text,
            hasPunchHole = hasPunchHole,
            markerColor = markerColor,
            size = size,
            textColor = textColor,
            draggableListener = listener
        )
    }
}