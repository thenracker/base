package cz.weissar.base.ui.spacex

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import cz.weissar.base.R
import cz.weissar.base.common.enums.Loading
import cz.weissar.base.common.enums.State
import cz.weissar.base.common.enums.Success
import cz.weissar.base.data.model.response.RocketLaunch
import cz.weissar.base.databinding.FragmentComposeBinding
import cz.weissar.base.ui.base.ComposeFragment
import cz.weissar.base.ui.theme.Gray300
import cz.weissar.base.ui.theme.Shapes
import cz.weissar.base.ui.theme.Typography
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpaceFlightsFragment : ComposeFragment() {

    override val viewModel by viewModel<SpaceXVM>()

    @Composable
    override fun setContent() {
        SpaceFlightsScreen(viewModel)
    }

    override fun FragmentComposeBinding.initViews() {
        viewModel.fetchItems()
    }

    companion object {
        fun newInstance() = SpaceFlightsFragment()
    }
}

@Composable
fun SpaceFlightsScreen(
    viewModel: SpaceXVM,
) {
    val viewState = viewModel.viewState.collectAsState()

    Scaffold(
        content = {
            ListContainer(
                status = viewState.value.status,
                list = viewState.value.list,
                emptyMessage = { EmptyPlaceholder("Empty") }
            ) {
                LaunchesList(
                    status = viewState.value.status,
                    launches = viewState.value.list,
                    onClick = { },
                    onRefresh = { viewModel.fetchItems() }
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
            )
        },
    )
}

@Composable
fun <T> ListContainer(
    status: State,
    list: List<T>,
    emptyMessage: @Composable () -> Unit = { EmptyPlaceholder("List is empty") },
    content: @Composable () -> Unit,
) {
    when {
        status is Success && list.isEmpty() -> emptyMessage()
        else -> content()
    }
}

@Composable
fun EmptyPlaceholder(
    message: String = "List is empty",
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        shape = Shapes.large,
        contentColor = Gray300,
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LaunchesList(
    status: State,
    launches: List<RocketLaunch>,
    onClick: (RocketLaunch) -> Unit,
    onRefresh: () -> Unit,
) {
    val swipeRefreshState = rememberSwipeRefreshState(status is Loading)

    SwipeRefresh(
        modifier = Modifier.fillMaxWidth(),
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 56.dp, top = 8.dp, start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(launches.size) { index ->
                LaunchItem(
                    launch = launches[index],
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing,
                            )
                        ),
                    onItemClick = onClick,
                )
            }
        }
    }
}

private const val TitleId = "TitleId"
private const val RocketNameId = "RocketNameId"


@Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchItem(
    launch: RocketLaunch,
    onItemClick: (RocketLaunch) -> Unit,
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    bgColor: Color = Color.White,
) {
    Card(
        elevation = elevation,
        onClick = { onItemClick(launch) },
        modifier = modifier,
        backgroundColor = bgColor,
    ) {
        Column {
            Text(
                text = launch.missionName,
                modifier = Modifier.layoutId(TitleId),
                style = Typography.h5,
            )
            Text(
                text = launch.rocket.name,
                modifier = Modifier.layoutId(TitleId),
                style = Typography.h3,
            )
        }
    }
}