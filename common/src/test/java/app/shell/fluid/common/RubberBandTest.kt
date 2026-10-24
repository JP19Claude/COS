package app.shell.fluid.common

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RubberBandTest {

    private val limit = 100f
    private val stiffness = 0.5f

    @Test
    fun noOverscrollGivesNoDisplacement() {
        assertEquals(0f, rubberBand(0f, limit, stiffness), 0f)
    }

    @Test
    fun displacementStaysBelowLimit() {
        assertTrue(rubberBand(1_000_000f, limit, stiffness) < limit)
        assertTrue(rubberBand(-1_000_000f, limit, stiffness) > -limit)
    }

    @Test
    fun displacementIsSymmetric() {
        assertEquals(-rubberBand(40f, limit, stiffness), rubberBand(-40f, limit, stiffness), 1e-4f)
    }

    @Test
    fun displacementGrowsMonotonically() {
        var previous = 0f
        for (step in 1..200) {
            val current = rubberBand(step * 5f, limit, stiffness)
            assertTrue(current > previous)
            previous = current
        }
    }

    @Test
    fun slopeAtBoundaryEqualsStiffness() {
        assertEquals(stiffness, rubberBandSlope(0f, limit, stiffness), 1e-6f)
    }

    @Test
    fun slopeMatchesFiniteDifference() {
        val x = 60f
        val h = 0.1f
        val numeric = (rubberBand(x + h, limit, stiffness) - rubberBand(x - h, limit, stiffness)) / (2 * h)
        assertEquals(numeric, rubberBandSlope(x, limit, stiffness), 1e-3f)
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectsNonPositiveLimit() {
        rubberBand(10f, 0f, stiffness)
    }
}
