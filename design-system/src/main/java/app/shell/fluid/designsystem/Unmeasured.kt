package app.shell.fluid.designsystem

/**
 * Marks a token whose value is not measured from reference material yet.
 *
 * Every value carrying this annotation is a placeholder. In DESIGN_SPEC.md and
 * MOTION_TOKENS.md it is still `TODO: Referenz nötig` (or `[geschätzt]` once it is
 * deliberately estimated). Remove the annotation only together with an entry that
 * names the source.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
annotation class Unmeasured(val note: String = "")
