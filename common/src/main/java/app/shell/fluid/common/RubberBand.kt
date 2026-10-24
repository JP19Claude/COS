package app.shell.fluid.common

import kotlin.math.abs
import kotlin.math.sign

/**
 * Visible displacement for finger travel beyond a boundary (rubber banding).
 *
 * The result grows with [overscroll] but approaches [limit] asymptotically. Near the
 * boundary it moves [stiffness] px per px of finger travel. The curve shape is a
 * placeholder until the resistance curve is measured (MOTION_TOKENS.md, gesture parameters).
 *
 * @param overscroll signed finger travel past the boundary, in px
 * @param limit largest visible displacement, in px, must be > 0
 * @param stiffness slope at the boundary, must be > 0
 */
fun rubberBand(overscroll: Float, limit: Float, stiffness: Float): Float {
    require(limit > 0f) { "limit must be > 0" }
    require(stiffness > 0f) { "stiffness must be > 0" }
    val x = abs(overscroll)
    return sign(overscroll) * limit * (1f - 1f / (x * stiffness / limit + 1f))
}

/**
 * Derivative of [rubberBand] with respect to finger travel. Converts a finger velocity
 * into the velocity of the rubber-banded displacement, so a release can hand the
 * velocity over to the settle animation.
 */
fun rubberBandSlope(overscroll: Float, limit: Float, stiffness: Float): Float {
    require(limit > 0f) { "limit must be > 0" }
    require(stiffness > 0f) { "stiffness must be > 0" }
    val d = abs(overscroll) * stiffness / limit + 1f
    return stiffness / (d * d)
}
