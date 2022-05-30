package com.coors.demoproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
                Text(text = "test on ")
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun Screen() {
        MaterialTheme() {
            Column {
                Row {
                    Text(text = "text2222222")
                    Text(
                        text= "test on text" , color = Color.White
                    )
                }

                Row {
                    Text(text = "text2222222")
                    Text(
                        text= "test on text" , color = Color.White
                    )
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