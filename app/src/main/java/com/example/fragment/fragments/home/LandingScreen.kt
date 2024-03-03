package com.example.fragment.fragments.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fragment.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun HomeTabBar(
    modifier: Modifier = Modifier,
    onMenuClicked: () -> Unit,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        // Separate Row as the children shouldn't have the padding
        Row(Modifier.padding(top = 8.dp)) {
            Image(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(onClick = onMenuClicked),
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(id = R.string.form_username)
            )
            Spacer(Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = null
            )
        }
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

//@Composable
//fun LandingScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
//    // Adds composition consistency. Use the value when LaunchedEffect is first called
//    val currentOnTimeout by rememberUpdatedState(onTimeout)
//
//    LaunchedEffect(Unit) {
//        delay(SplashWaitTime)
//        currentOnTimeout()
//    }
//    Image(
//        painterResource(id = R.drawable.ic_home),
//        contentDescription = null,
//        modifier
//            .fillMaxSize()
//            .wrapContentSize()
//    )
//
//    @OptIn(
//        ExperimentalMaterialApi::class,
//        ExperimentalFoundationApi::class
//    )
//
//    @Composable
//     fun HomeTabBar(
//        openDrawer: () -> Unit,
//        modifier: Modifier = Modifier
//    ) {
//        CraneTabBar(
//            modifier = modifier
//                .wrapContentWidth()
//                .sizeIn(maxWidth = 500.dp),
//            onMenuClicked = openDrawer
//        ) {
////            tabBarModifier ->
////            CraneTabs(
////                modifier = tabBarModifier,
////                titles = CraneScreen.values().map { it.name },
////                tabSelected = tabSelected,
////                onTabSelected = { newTab -> onTabSelected(CraneScreen.values()[newTab.ordinal]) }
////            )
//        }
//    }
//
//
//}

