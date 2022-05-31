package com.coors.demoproject.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.coors.demoproject.R
import com.coors.demoproject.data.currency.CurrencyInfo
import com.coors.demoproject.databinding.FragmentCurrencyListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private lateinit var fragmentViewBinding: FragmentCurrencyListBinding

    private val viewModel by viewModels<DemoActivityViewModel>(
        ownerProducer = {
            requireActivity()
        }
    )

    private val currencyListAdapter by lazy {
        CurrencyListAdapter { item: CurrencyInfo ->
            Toast.makeText(requireContext(), "click Currency ${item.id} !!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                onStartApp()
            }
        }
    }

    @Composable
    private fun onStartApp() {
        var shouldShowOnboarding by rememberSaveable {
            mutableStateOf(true)
        }
        if (shouldShowOnboarding) {
            onBoardScreen { shouldShowOnboarding = false }
        } else {
            MyApp()
        }
    }


    @Composable
    fun StickyNote(content: String, offX: Int, offY: Int) {
        Surface(
            Modifier
                .offset(offX.dp, offY.dp)
                .size(108.dp, 108.dp),
            color = Color.Green,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = content, style = MaterialTheme.typography.h5)
            }
        }
    }

    //    @Preview(showBackground = true)
    @Composable
    fun StickyNotePreview() {
        Box(modifier = Modifier.fillMaxSize()) {
            StickyNote("Hello", offX = 10, offY = 10)
            StickyNote("BBBBB", offX = 50, offY = 60)
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun MyApp(
        list: List<String> = List(10) { "$it" }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 4.dp)
            ) {
                item { Text(text = "header") }
                items(list) { name ->
                    Greeting(name = name)
                }
                item { Text(text = "footer") }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Greeting(name: String) {
        var expanded by remember {
            mutableStateOf(false)
        }

        val extrasPadding by animateDpAsState(
            targetValue = if (expanded) 48.dp else 0.dp,
            animationSpec = tween(durationMillis = 2000)
        )

        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extrasPadding)
                ) {
                    Text(text = "Hello!")
                    Text(text = name)
                }

                OutlinedButton(onClick = {
                    expanded = !expanded
                }) {
                    Text(text = if(expanded) "Show less" else "Show more")
                }
            }
        }
    }

    @Preview(showBackground = true , widthDp = 320, heightDp = 320 , uiMode = UI_MODE_NIGHT_YES)
    @Preview(showBackground = true , widthDp = 320, heightDp = 320)
    @Composable
    fun onBoardScreen(onClickCallback: () -> Unit) {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Welcome to the Basics Codelab!")
                Button(
                    modifier = Modifier.padding(vertical = 24.dp),
                    onClick = onClickCallback
                ){
                    Text("Continue")
                }
            }
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::fragmentViewBinding.isInitialized) return
        fragmentViewBinding.initViews()
        subscribeFlow()
    }

    private fun subscribeFlow() {
        collectFetchCurrencyList()
        collectErrorMessage()
    }

    private fun collectFetchCurrencyList() {
        lifecycleScope.launch {
            viewModel.currencyListStateFlow
                .flowWithLifecycle(lifecycle)
                .collect {
                    Timber.d("RRR show $it")
                    currencyListAdapter.submitList(it)
                }
        }
    }

    private fun collectErrorMessage() {
        lifecycleScope.launch {
            viewModel.errorShareFlow
                .flowWithLifecycle(lifecycle)
                .collect { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun FragmentCurrencyListBinding.initViews() {
        with(rvCurrencyList) {
            addItemDecoration(getRecyclerViewDivider(R.drawable.currency_divider))
            adapter = currencyListAdapter
        }
    }

    private fun getRecyclerViewDivider(@DrawableRes drawableId: Int): ItemDecoration {
        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), drawableId)?.let {
            itemDecoration.setDrawable(it)
        }
        return itemDecoration
    }
}