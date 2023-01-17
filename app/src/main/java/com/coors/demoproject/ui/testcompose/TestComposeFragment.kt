package com.coors.demoproject.ui.testcompose

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
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
//                    PrivacyTextV2()
                }
            }
        }
    }

    @Preview
    @Composable
    private fun LoadTestComposeScreen(
        viewModel: TestComposeViewModel = viewModel()
    ) {
        PrivacyPolicyText()
    }
}

@Preview
@Composable
private fun PrivacyPolicyText() {
    val annotatedString = buildAnnotatedString {
        append("By signing up, you agree to our ")
        withStyle(style = SpanStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline)) {
            append("Privacy Policy")
        }
        append(" and ")
        withStyle(style = SpanStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline)) {
            append("Terms of Service")
        }
        append(".")
    }
    ClickableText(
        text = annotatedString,
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { annotation ->
                    Log.d("TestComposeFragment", "annotation: $annotation")
                }
        }
    )
}

@Composable
private fun PrivacyTextV2() {
    val annotatedText = buildAnnotatedString {
        append("登录即表明同意")
        pushStringAnnotation(
            tag = "tag1",
            annotation = "隐私条款1：https://www.xxx1.com"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("中国移动认证服务条款")
        }
        pop()
        append("以及")
        pushStringAnnotation(
            tag = "tag2",
            annotation = "隐私条款2：https://www.xxx2.com"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("用户协议")
        }
        pop()
        append("和")
        pushStringAnnotation(
            tag = "tag1",
            annotation = "隐私条款3：https://www.xxx3.com"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("隐私政策")
        }
        pop()
    }

    val tags = listOf("tag1", "tag2", "tag3")
    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            tags.forEach { tag ->
                annotatedText.getStringAnnotations(
                    tag = tag,
                    start = offset,
                    end = offset
                )
                    .firstOrNull()?.let { annotation ->
                        Log.d("xige", annotation.item)
                    }
            }
        }
    )
}