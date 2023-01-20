package com.coors.demoproject.ui.testcompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coors.commoncore.model.AnchorModel
import com.coors.demoproject.compose.BasicsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TestComposeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BasicsTheme {
                    LoadTestComposeScreen()
                }
            }
        }
    }

    @Preview
    @Composable
    private fun LoadTestComposeScreen(
        viewModel: TestComposeViewModel = viewModel()
    ) {
        val anchors: List<AnchorModel> by viewModel.getAnchors().collectAsState(initial = emptyList())

        MockSameMatchAnchorsView(anchors)
    }

}

@Composable
fun MockSameMatchAnchorsView(items: List<AnchorModel>) {
//    val items = getMockAnchors()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color(0xFF000000))
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "本场主播",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp ),
        ) {
            items(items) {
                SameMatchAnchorContainerView(it)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "更多主播",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp ),
            modifier = Modifier,
            columns = GridCells.Fixed(2)
        ) {
            items(items) {
                OtherMoreAnchorContainerView(it)
            }
        }
    }
}

//fun getMockAnchors() = listOf<AnchorModel>(
//    AnchorModel(
//        name = "爱妃宝贝",
//        photo = "https://picsum.photos/id/237/200/300",
//        id = 0,
//        liveStatus = 0,
//        isBooking = false,
//        language = -1,
//        tag = "赛事分析",
//        startData = "08-10 10:00"
//    ),
//    AnchorModel(
//        name = "曼联糖糖",
//        photo = "https://picsum.photos/id/236/200/300",
//        id = 1,
//        liveStatus = 0,
//        isBooking = true,
//        language = 0,
//        tag = "赛事分析",
//        startData = "08-12 10:00"
//    ),
//    AnchorModel(
//        name = "小梨宝",
//        photo = "https://picsum.photos/id/235/200/300",
//        id = 2,
//        liveStatus = 1,
//        isBooking = false,
//        language = 1,
//        tag = "赛事分析",
//        startData = "08-11 10:00"
//    )
//)