package com.realpacific.markerview


interface DraggableListener {
    fun onMarkerReleased(view: MarkerView)

    fun onMarkerPositionChanged(view: MarkerView)
}