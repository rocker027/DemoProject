package com.coors.demoproject.ui.testcompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
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
                }
            }
        }
    }

    @Preview
    @Composable
    private fun LoadTestComposeScreen(
        viewModel: TestComposeViewModel = viewModel()
    ) {


    }
}