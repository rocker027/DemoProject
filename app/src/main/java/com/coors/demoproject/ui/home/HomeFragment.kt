package com.coors.demoproject.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.coors.demoproject.R
import com.coors.demoproject.compose.BasicsTheme
import com.coors.demoproject.data.home.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val navController by lazy {
        requireActivity().findNavController(R.id.fragment_container)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                BasicsTheme {
                    LoadHomeCompose()
                }
            }
        }
    }

    @Composable
    private fun LoadHomeCompose(
        viewModel: HomeViewModel = viewModel()
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }

        val menus: List<HomeMenu> by viewModel.getMenus().collectAsState(initial = emptyList())

        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(menus) { menu ->
                GetMenuItems(menu)
            }
        }
    }

    @Composable
    fun GetMenuItems(homeMenu: HomeMenu) {
//        val navController = rememberNavController()
//        NavHost(navController = navController, startDestination = "home"){
//            composable("cro"){
//               testNext()
//            }
//
//            composable("home"){
//
//            }
//        }

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable { handleNaviTo(homeMenu) },
        ) {
            MenuItemViewLayout(homeMenu = homeMenu)
        }
    }

    private fun handleNaviTo(homeMenu: HomeMenu) = when(homeMenu){
        is CroDemoCompose -> R.id.action_homeFragment_to_currencyListFragment
        is CroDemoKotlin -> R.id.action_homeFragment_to_currencyListFragment
        is Home -> R.id.action_homeFragment_to_currencyListFragment
        is TestCompose -> R.id.action_homeFragment_to_currencyListFragment
    }.run {
        navController.navigate(this)
    }

    @Composable
    fun testNext(){
        Surface {
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "CRO")
            }
        }
    }

    @Composable
    fun MenuItemViewLayout(homeMenu: HomeMenu) {
        var expanded by remember {
            mutableStateOf(false)
        }

        val extrasPadding by animateDpAsState(
            targetValue = if (expanded) 48.dp else 0.dp,
            animationSpec = tween(durationMillis = 2000)
        )

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = homeMenu.name,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                OutlinedButton(
                    modifier = Modifier.width(120.dp),
                    onClick = { expanded = !expanded }
                ) {
                    Text(
                        text = if (expanded) "show less" else "show more",
                        color = Color.Black
                    )
                }
            }

            if (expanded) {
                Text(
                    text = homeMenu.description, modifier = Modifier
                        .fillMaxWidth(1f)
                        //                        .height(extrasPadding)
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                )
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    @Preview(
        showBackground = true,
        widthDp = 320,
        uiMode = UI_MODE_NIGHT_YES,
        name = "DefaultPreviewDark"
    )
    @Preview
    @Composable
    fun DefaultPreview() {
        BasicsTheme {
            GetMenuItems(
                homeMenu = TestCompose(
                    name = "這是測試",
                    description = "要不要架個server或找個來一起玩啊,要不要架個server或找個來一起玩啊"
                )
            )
        }
    }
}