package com.tatweer.smartdrivingtest.utils

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope.PlaceHolderSize
import androidx.compose.animation.SharedTransitionScope.PlaceHolderSize.Companion.contentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.tatweer.smartdrivingtest.presentation.main.LocalAnimatedContentScope
import com.tatweer.smartdrivingtest.presentation.main.LocalSharedTransitionScope
import kotlinx.coroutines.channels.ReceiveChannel

@Composable
fun <T> ReceiveChannel<T>.ConsumeEach(onReceived: (T) -> Unit) {
    LaunchedEffect(this) {
        for (stateEvent in this@ConsumeEach) {
            onReceived(stateEvent)
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalComposeUiApi::class)
fun Modifier.optionalSharedElement(
    key: Any,
    placeHolderSize: PlaceHolderSize = contentSize,
    renderInOverlayDuringTransition: Boolean = true,
    zIndexInOverlay: Float = 0f,
): Modifier {
    return composed(
        "Optional Shared Element",
        key,
        placeHolderSize,
        renderInOverlayDuringTransition,
        zIndexInOverlay
    ) {
        LocalSharedTransitionScope.current?.let { sharedTransitionScope ->
            LocalAnimatedContentScope.current?.let { animatedContentScope ->
                with(sharedTransitionScope) {
                    sharedElement(
                        rememberSharedContentState(key),
                        animatedContentScope,
                        placeHolderSize = placeHolderSize,
                        renderInOverlayDuringTransition = renderInOverlayDuringTransition,
                        zIndexInOverlay = zIndexInOverlay
                    )
                }
            }
        } ?: Modifier
    }
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalComposeUiApi::class)
fun Modifier.optionalSharedBounds(
    key: Any,
    placeHolderSize: PlaceHolderSize = contentSize,
    renderInOverlayDuringTransition: Boolean = true,
    zIndexInOverlay: Float = 0f,
): Modifier {
    return composed(
        "Optional Shared Element",
        key,
        placeHolderSize,
        renderInOverlayDuringTransition,
        zIndexInOverlay
    ) {
        LocalSharedTransitionScope.current?.let { sharedTransitionScope ->
            LocalAnimatedContentScope.current?.let { animatedContentScope ->
                with(sharedTransitionScope) {
                    sharedBounds(
                        rememberSharedContentState(key),
                        animatedContentScope,
                        placeHolderSize = placeHolderSize,
                        renderInOverlayDuringTransition = renderInOverlayDuringTransition,
                        zIndexInOverlay = zIndexInOverlay
                    )
                }
            }
        } ?: Modifier
    }
}
