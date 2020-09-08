package com.realpacific.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.realpacific.markerview.DraggableListener
import com.realpacific.markerview.MarkerConfig
import com.realpacific.markerview.MarkerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), DraggableListener {
    private val markerValues = listOf('M', 'a', 'r', 'k', 'e', 'r', 'V', 'e', 'w')
    private val colors = listOf(
        android.R.color.holo_red_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_green_dark,
        android.R.color.darker_gray,
        android.R.color.background_dark,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    private val random = Random(26)
    private val colorIndex: Int
        get() {
            return random.nextInt(10000) % colors.size
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<RelativeLayout>(R.id.container)
        val config = MarkerConfig()
            .withColor(R.color.colorPrimaryDark)
            .withSize(100)
            .withDraggableListener(this)

        markerValues.forEachIndexed { _, s ->
            val clone = config.clone()
                .withColor(colors[colorIndex])
                .withText(s)
            container.addView(MarkerView(this, clone))
        }

        container.addView(MarkerView(this, config.clone().withPunchHole().withSize(200)))
    }


    override fun onMarkerReleased(view: MarkerView) {
    }

    @SuppressLint("SetTextI18n")
    override fun onMarkerPositionChanged(view: MarkerView) {
        val textView = findViewById<TextView>(R.id.text_view)
        textView.text = "${view.text}: ${view.point}"
    }
}