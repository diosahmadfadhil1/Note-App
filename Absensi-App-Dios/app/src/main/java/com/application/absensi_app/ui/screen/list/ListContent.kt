package com.application.absensi_app.ui.screen.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.application.absensi_app.R
import com.application.absensi_app.data.model.Priority
import com.application.absensi_app.data.model.Absensi
import com.application.absensi_app.ui.theme.*
import com.application.absensi_app.util.Action
import com.application.absensi_app.util.RequestState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListContent(
    onItemClicked: (Int) -> Unit,
    absens: RequestState<List<Absensi>>,
    sortState: RequestState<Priority>,
) {
    if (sortState is RequestState.Success) {
        when (sortState.data) {
            Priority.NONE -> {
                if (absens is RequestState.Success) {
                    HandleListContent(
                        onItemClicked = onItemClicked,
                        list = absens.data,
                    )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    onItemClicked: (Int) -> Unit,
    list: List<Absensi>,
) {
    if (list.isEmpty()) {
        EmptyContent()
    } else
        DisplayAbsen(
        onItemClicked = onItemClicked,
        absens = list,
    )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DisplayAbsen(
    onItemClicked: (Int) -> Unit,
    absens: List<Absensi>
) {
    LazyColumn {
        items(items = absens,
            key = { absen ->
                absen.id
            }) { absen ->

            var itemAppeared by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }
            AbseniItem(
                onItemClicked = onItemClicked,
                absensi = absen
            )
//            AnimatedVisibility(
//                visible = itemAppeared && !isDismissed,
//                enter = expandVertically(
//                    animationSpec = tween(
//                        durationMillis = 300
//                    )
//                ),
//                exit = shrinkVertically(
//                    animationSpec = tween(
//                        durationMillis = 300
//                    )
//                )
//            ) {
//                SwipeToDismiss(
//                    state = swipeDismissState,
//                    directions = setOf(DismissDirection.EndToStart),
//                    background = { RedBackground(degrees) },
//                    dismissThresholds = { FractionalThreshold(0.2f) }
//                ) {
//                    AbseniItem(
//                        onItemClicked = onItemClicked,
//                        absensi = absen
//                    )
//                }
//            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun AbseniItem(
    onItemClicked: (Int) -> Unit,
    absensi: Absensi
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.absensiItemBackgroundColor,
        elevation = ABSENSI_ITEM_ELEVATION,
        shape = RectangleShape,
        onClick = {
            onItemClicked(absensi.id)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = LARGE_PADDING, vertical = SMALL_PADDING)
                .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LARGE_PADDING)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = absensi.title,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.absensiItemTextColor,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Box(
                        Modifier.padding(top = SMALL_PADDING, end = SMALL_PADDING)
                    ) {
                        Canvas(
                            modifier = Modifier.size(
                                INDICATOR_SIZE
                            )
                        ) {
                            if (absensi.priority != Priority.NONE)
                                drawCircle(color = absensi.priority.color)
                            else drawCircle(
                                color = Color.Black,
                                style = Stroke()
                            )
                        }
                    }
                }
                Text(
                    text = absensi.desc,
                    maxLines = 2,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.absensiItemTextColor,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}