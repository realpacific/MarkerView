package com.realpacific.markerview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlin.math.max
import kotlin.math.min


/**
 * @author Prashant Barahi
 */
@SuppressLint("ViewConstructor")
class MarkerView
constructor(context: Context, config: MarkerConfig) : View(context, null, 0) {

    private val property: MarkerProperty = config.build()

    /**
     * Text on the [MarkerView]. This is `null` if [MarkerView.hasPunchHole] is `true`.
     */
    var text: Char? = property.text
        private set

    /**
     * Whether the [MarkerView] has a punch hole or not.
     * When set to true, the [MarkerView.text] is `null`.
     */
    val hasPunchHole = config.hasPunchHole

    /**
     * The coordinates of the pointy part of [MarkerView]
     */
    val point: PointF
        get() = PointF(bottomX, bottomY)

    var markerColor: Int = config.markerColor
        private set

    private var viewWidth: Int = property.size

    private val viewHeight: Int
        get() = viewWidth.plus(viewWidth.times(0.7).toInt())

    private val bottomX: Float
        get() = x + width / 2

    private val bottomY: Float
        get() = y + height

    private val textPaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, config.textColor)
            textAlign = Paint.Align.CENTER
        }
    }

    private val shapePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, this@MarkerView.markerColor)
        style = Paint.Style.FILL
        isDither = true
    }

    private val pinHolePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, android.R.color.transparent)
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            style = Paint.Style.FILL
        }
    }

    private val path = Path()

    init {
        config.listener?.let { makeDraggable(it) }
        if (hasPunchHole) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }
    }

    /**
     * Updates the text displayed in the [MarkerView]. This is a noop if [hasPunchHole] is `true`
     */
    fun updateText(text: Char) {
        if (!hasPunchHole) {
            this.text = text
            invalidate()
        }
    }

    /**
     * Updates the color of the [MarkerView]
     */
    fun updateMarkerColor(@ColorRes color: Int) {
        this.markerColor = color
        this.shapePaint.color = ContextCompat.getColor(context, this@MarkerView.markerColor)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            val radius = width / 2f
            val heightOfCircle = radius * 2
            val widthOfCircle = radius * 2
            drawCircle(width / 2f, width / 2f, radius, shapePaint)

            path.moveTo(widthOfCircle * 0.2f, heightOfCircle * 0.9f)
            path.lineTo(widthOfCircle / 2f, height.toFloat())
            path.lineTo(widthOfCircle * 0.8f, heightOfCircle * 0.9f)
            drawPath(path, shapePaint)

            text?.toString()?.let {
                drawText(it, widthOfCircle / 2f, heightOfCircle * 0.7f,
                    textPaint.also { paint -> paint.textSize = heightOfCircle * 0.5f }
                )
            }
            if (hasPunchHole) {
                drawCircle(width / 2f, width / 2f, radius * 0.4f, pinHolePaint)
            }
        }
    }

    private fun makeDraggable(draggableListener: DraggableListener) {
        var dx = 0f
        var dy = 0f
        setOnTouchListener { v, event ->
            val viewParent = v.parent as View
            val parentHeight = viewParent.height
            val parentWidth = viewParent.width
            val xMax = parentWidth - v.width
            val yMax = parentHeight - v.height

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dx = v.x - event.rawX
                    dy = v.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    var newX = event.rawX + dx
                    newX = max(0F, newX)
                    newX = min(xMax.toFloat(), newX)

                    var newY = event.rawY + dy
                    newY = max(0F, newY)
                    newY = min(yMax.toFloat(), newY)

                    v.x = newX
                    v.y = newY

                    draggableListener.onMarkerPositionChanged(v as MarkerView)
                }
                MotionEvent.ACTION_UP -> {
                    performClick()
                    draggableListener.onMarkerReleased(v as MarkerView)
                }
                else -> return@setOnTouchListener false
            }
            true
        }
    }
}