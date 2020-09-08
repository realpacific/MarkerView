package com.realpacific.markerview

import androidx.annotation.ColorRes

/**
 * @author Prashant Barahi
 */
internal data class MarkerProperty(
    val text: Char?,
    val hasPunchHole: Boolean,
    @ColorRes val markerColor: Int,
    @ColorRes val textColor: Int,
    val size: Int,
    val draggableListener: DraggableListener?
)