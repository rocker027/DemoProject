package com.coors.demoproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.exp

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = loadLayoutCompose()


    private fun loadLayoutCompose(): View = ComposeView(requireContext()).apply {
        setContent {
            homeScreenLayout()
        }
    }


    @Preview(showBackground = true)
    @Composable
    private fun homeScreenLayout() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun menuItemViewLayout(menuName : String){
        var expaned by remember {
            mutableStateOf(false)
        }

        Surface(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row {
                Text(text = menuName)
                OutlinedButton(
                    onClick = { expaned = !expaned }
                ){
                    Text(text = if (expaned) "show less" else "show more")
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}