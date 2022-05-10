package dev.yasan.kaizen.presentation.ui.screen.home.states.modules

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yasan.kaizen.model.SportEvent
import dev.yasan.kaizen.presentation.ui.preview.provider.SportEventPreviewProvider
import dev.yasan.kaizen.presentation.ui.theme.KaizenIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

@Composable
fun EventItem(
    modifier: Modifier = Modifier,
    event: SportEvent
) {

    Column(
        modifier = modifier
            .padding(vertical = grid(1.5f))
            .border(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.onSurface,
                width = 1.dp
            )
            .clip(MaterialTheme.shapes.medium)
            .padding(grid(2)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    event.favorite.value = !event.favorite.value
                }
                .padding(grid()),
            imageVector = if (event.favorite.value) KaizenIcons.Star else KaizenIcons.StarBorder,
            contentDescription = null,
            tint = if (event.favorite.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
        )

        event.nameSplit.forEach {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = rubikFamily,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}

@Preview(name = "Sport Event Item", showBackground = true)
@Composable
private fun EventItemPreview(
    @PreviewParameter(SportEventPreviewProvider::class) event: SportEvent
) {
    EventItem(
        modifier = Modifier.padding(horizontal = grid(2)),
        event = event
    )
}

@Preview(name = "Sport Event Item (Favorite)", showBackground = true)
@Composable
private fun EventItemPreviewFavorite(
    @PreviewParameter(SportEventPreviewProvider::class) event: SportEvent
) {
    val favoriteEvent = event.copy(
        favorite = remember { mutableStateOf(true) }
    )
    EventItem(
        modifier = Modifier.padding(horizontal = grid(2)),
        event = favoriteEvent
    )
}