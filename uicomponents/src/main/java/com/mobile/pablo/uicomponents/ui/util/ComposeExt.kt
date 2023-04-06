package com.mobile.pablo.uicomponents.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp

/**
 * Found solution on
 *  https://stackoverflow.com/questions/68592618/how-to-add-border-on-bottom-only-in-jetpack-compose
 */
@Suppress("UnnecessaryComposedModifier")
fun Modifier.topRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier = composed(
    factory = {
        this.then(
            Modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawLine(
                        brush,
                        Offset(
                            width.value,
                            0f
                        ),
                        Offset(
                            size.width - width.value,
                            0f
                        )
                    )
                }
            }
        )
    },
    inspectorInfo = debugInspectorInfo {
        name = "border"
        properties["width"] = width
        if (brush is SolidColor) {
            properties["color"] = brush.value
            value = brush.value
        } else {
            properties["brush"] = brush
        }
        properties["shape"] = RectangleShape
    }
)

@Suppress("UnnecessaryComposedModifier")
fun Modifier.bottomRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier = composed(
    factory = {
        this.then(
            Modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawLine(
                        brush,
                        Offset.Zero.copy(y = size.height),
                        Offset(
                            size.width,
                            size.height
                        )
                    )
                }
            }
        )
    },
    inspectorInfo = debugInspectorInfo {
        name = "border"
        properties["width"] = width
        if (brush is SolidColor) {
            properties["color"] = brush.value
            value = brush.value
        } else {
            properties["brush"] = brush
        }
        properties["shape"] = RectangleShape
    }
)

@Suppress("UnnecessaryComposedModifier")
fun Modifier.leftRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier = composed(
    factory = {
        this.then(
            Modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawLine(
                        brush,
                        Offset.Zero.copy(y = 0f),
                        Offset.Zero.copy(y = size.height)
                    )
                }
            }
        )
    },
    inspectorInfo = debugInspectorInfo {
        name = "border"
        properties["width"] = width
        if (brush is SolidColor) {
            properties["color"] = brush.value
            value = brush.value
        } else {
            properties["brush"] = brush
        }
        properties["shape"] = RectangleShape
    }
)

@Suppress("UnnecessaryComposedModifier")
fun Modifier.rightRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier = composed(
    factory = {
        this.then(
            Modifier.drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawLine(
                        brush,
                        Offset(
                            size.width,
                            0f
                        ),
                        Offset(
                            size.width,
                            size.height
                        )
                    )
                }
            }
        )
    },
    inspectorInfo = debugInspectorInfo {
        name = "border"
        properties["width"] = width
        if (brush is SolidColor) {
            properties["color"] = brush.value
            value = brush.value
        } else {
            properties["brush"] = brush
        }
        properties["shape"] = RectangleShape
    }
)

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }