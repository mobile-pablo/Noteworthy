package com.mobile.pablo.uicomponents.note.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

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

@Composable
fun Modifier.testTag(
    @androidx.annotation.StringRes resId: Int
) = testTag(stringResource(id = resId))

// https://stackoverflow.com/a/70185157/13770601
@Composable
inline fun <reified T> Flow<T>.observeWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    key2: Any? = null,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(
        key1 = Unit,
        key2 = key2
    ) {
        lifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(
                lifecycleOwner.lifecycle,
                minActiveState
            ).collect(action)
        }
    }
}
