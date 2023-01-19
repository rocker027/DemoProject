package com.coors.demoproject.ui.testcompose

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.*
import com.coors.demoproject.R

@Composable
private fun PrivacyPolicyText() {
    val annotatedString = buildAnnotatedString {
        append("By signing up, you agree to our ")
        pushStringAnnotation(
            tag = "tag1",
            annotation = "https://www.xxx1.com"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Privacy Policy")
        }
        pop()
        append(" and ")
        pushStringAnnotation(
            tag = "tag2",
            annotation = "https://www.xxx2.com"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Terms of Service")
        }
        pop()
        append(".")
    }
    val tags = listOf("tag1", "tag2", "tag3")
    ClickableText(
        text = annotatedString,
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = {
            tags.forEach { tag ->
                annotatedString.getStringAnnotations(tag, it, it)
                    .firstOrNull()?.let { annotation ->
                        Log.d("TAG", "annotation: ${annotation.item}")
                    }
            }
        }
    )
}

@Composable
fun CardViewTest() {
    val isNeedExpansion = rememberSaveable {
        mutableStateOf(false)
    }
    val animatedSizeDp: Dp by animateDpAsState(targetValue = if (isNeedExpansion.value) 200.dp else 100.dp)
    Box {
        Column {
            Text(text = "Hello")
            Text(text = "Hello")
        }
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(animatedSizeDp)
                .clip(CircleShape)
                .border(5.dp, Color.Gray, CircleShape)
                .clickable {
                    isNeedExpansion.value = !isNeedExpansion.value
                }
        )
    }
}

@Composable
fun MessageCard(message: String) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        val isExpanded = remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            targetValue = if (isExpanded.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )

        Column(
            modifier = Modifier
                .clickable { isExpanded.value = !isExpanded.value }
        ) {
            Text(text = "Android", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium, tonalElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = "Hey there! I'm using Compose for building my UIs. It's pretty cool!",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<String>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarViews(modifier: Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = "Search")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    imageUrl: String,
    title: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(100.dp, 130.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun AlignYourBodyElements() {
    val list = listOf<String>(
        "aaaaaaaaaafdadffdasaaa",
        "bbbbbbbbbb",
        "ccccccccccc",
        "ddddddddddd",
        "eeeeeeeeeee",
        "ffffffffff"
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(list) { item ->
            AlignYourBodyElement("https://anitar.dev/get/r", item)
        }
    }
}

data class AnchorModel(
    val name: String = "灯妹妹菲儿",
    val photo: String = "https://anitar.dev/get/r",
    val id: Int = 0,
    val liveStatus: Int = 0,
    val isBooking: Boolean = false,
    val language: Int = 0,
    val tag: String = "官方新手教学",
    val startData: String = "08-10 10:00",
    val depiction: String = "私密直播间，及时反馈解决",
) {
    fun isLiving() = liveStatus == 1
}


@Composable
fun SameMatchAnchorContainerView(
    anchor: AnchorModel,
) {
    Box(modifier = Modifier.size(96.dp, 106.dp)) {
        SameMatchAnchorView(anchor = anchor, modifier = Modifier.align(Alignment.Center))
        if (anchor.isLiving()) {
            LivingStateView(modifier = Modifier.align(Alignment.TopEnd))
        }
    }
}

@Composable
fun OtherMoreAnchorContainerView(anchor: AnchorModel){
    Box(modifier = Modifier.size(165.dp, 157.dp)) {
        OtherMoreAnchorView(modifier = Modifier.align(Alignment.Center), anchor = anchor)
        if (anchor.isLiving()) {
            LivingStateView(modifier = Modifier.align(Alignment.TopEnd))
        } else {
            ReadyStateView(
                isBooking = anchor.isBooking,
                startData = anchor.startData,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun LivingStateView(modifier: Modifier) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.wave))
    Row(
        modifier = modifier
            .size(50.dp, 16.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF835B), Color(0xFFFC2719)
                    )
                ), shape = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp)
            )
            .padding(start = 4.dp)

    ) {
        LottieAnimation(
            composition = composition,
            restartOnPlay = true,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(10.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = "直播中",
            fontSize = 10.sp,
            modifier = Modifier.padding(end = 4.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun ReadyStateView(isBooking: Boolean, startData: String, modifier: Modifier) {
    Row(
        modifier = modifier.size(113.dp, 16.dp)
    ) {
        Text(
            text = startData,
            fontSize = 10.sp,
            color = Color(0xFFBBBBBB),
            modifier = Modifier
                .background(
                    color = Color(0xB2000000),
                    shape = RoundedCornerShape(bottomStart = 8.dp)
                )
                .size(69.dp, 16.dp)
                .padding(start = 6.dp, end = 2.dp)
                .align(Alignment.CenterVertically)

        )
        Row(
            modifier = Modifier
                .background(
                    color = Color(0xFF2B44B1),
                    shape = RoundedCornerShape(topEnd = 8.dp)
                )
                .size(44.dp, 16.dp)
        ) {
            Spacer(modifier = Modifier.width(3.dp))
            if (!isBooking) {
                Image(
                    painter = painterResource(id = R.drawable.icon_ball), contentDescription = null,
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Text(
                text = if (isBooking) "已预约" else "预约",
                fontSize = 10.sp,
                modifier = Modifier.padding(end = 4.dp),
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}

@Composable
fun AnchorTagView(tag: String , modifier: Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.icon_anchor_tag_bg),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp, 18.dp)
                .align(Alignment.Center)
        )
        Text(
            text = tag,
            fontSize = 10.sp,
            color = Color(0xFF4F4BFF),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun SameMatchAnchorView(modifier: Modifier, anchor: AnchorModel) {
    Column(
        modifier = modifier
            .size(96.dp, 106.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anchor.photo)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(96.dp, 63.dp)
                .clip(RoundedCornerShape(8.dp))

        )

        Spacer(modifier = Modifier.height(2.dp))
        Row {
            if (anchor.language != -1) {
                Text(
                    text = if (anchor.language == 0) "普" else "粵語",
                    fontSize = 12.sp,
                    color = Color(0xFF00A525),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                )
            }
            Text(
                text = anchor.name,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )
        }
        Row {
            Spacer(modifier = Modifier.width(4.dp))
            AnchorTagView(tag = anchor.tag, modifier = Modifier.padding(end = 4.dp))
        }
    }
}

@Composable
fun OtherMoreAnchorView(modifier: Modifier, anchor: AnchorModel) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anchor_stroke))
    Column(
        modifier = modifier
            .size(165.dp, 157.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anchor.photo)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(165.dp, 108.dp)
                .clip(RoundedCornerShape(8.dp))

        )

        Spacer(modifier = Modifier.height(2.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.size(27.dp)) {
                LottieAnimation(
                    composition = composition,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(27.dp)
                        .align(Alignment.Center)
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(anchor.photo)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                )
            }
            Text(
                text = anchor.name,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .align(Alignment.CenterVertically)
            )

            AnchorTagView(tag = anchor.tag, modifier = Modifier.align(Alignment.CenterVertically))
        }
        Text(
            text = anchor.depiction,
            fontSize = 12.sp,
            color = Color(0xFF000000),
            modifier = Modifier.padding(start = 4.dp, end = 4.dp)
        )
    }
}


@Preview
@Composable
fun ShowNotBookingReadyStateView() {
    ReadyStateView(isBooking = false, startData = "08-10 10:00", modifier = Modifier)
}

@Preview
@Composable
fun ShowBookingReadyStateView() {
    ReadyStateView(isBooking = true, startData = "08-10 10:00", modifier = Modifier)
}

@Preview
@Composable
fun ShowLiving() {
    LivingStateView(modifier = Modifier)
}

@Preview
@Composable
fun ShowTag() {
    AnchorTagView("官方新手教学", modifier = Modifier)
}