package com.realpacific.markerview

import org.junit.Assert.*
import org.junit.Test

class MarkerConfigTest {
    @Test
    fun whenClone_MustCopyAllProperty() {
        val config = MarkerConfig()
            .withSize(100)
            .withText('X', android.R.color.background_dark)
            .withColor(android.R.color.holo_red_light)

        val clonedConfig = config.clone()
        with(clonedConfig) {
            assertEquals(hasPunchHole, config.hasPunchHole)
            assertEquals(textColor, config.textColor)
            assertEquals(markerColor, config.markerColor)
            assertEquals(size, config.size)
            assertEquals(text, config.text)
            assertEquals(listener, config.listener)
        }
    }

    @Test
    fun whenPunchHoleEnabled_textShouldBeNull() {
        val config = MarkerConfig()
            .withSize(100)
            .withText('X')
        val punchHoleConfig = config.clone().withPunchHole()
        assertNull(punchHoleConfig.text)
        assertTrue(punchHoleConfig.hasPunchHole)
    }
}