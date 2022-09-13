package com.coors.demoproject.ui.cro

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import logcat.asLog
import logcat.logcat

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private lateinit var fragmentViewBinding: FragmentCurrencyListBinding

    private val viewModel by viewModels<CurrencyListViewModel>(
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
        fragmentViewBinding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return fragmentViewBinding.root
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
                .catch { e ->

                    logcat { "RRR Error ${e.asLog()}" }
                }
                .collect {
                    throw Exception("RRR Error")
                    logcat { "RRR show $it" }
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

        btnLoading.setOnClickListener {
            viewModel.onClickLoading()
        }

        btnSorting.setOnClickListener {
            viewModel.onClickSorting()
        }
    }

    private fun getRecyclerViewDivider(@DrawableRes drawableId: Int): ItemDecoration {
        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), drawableId)?.let {
            itemDecoration.setDrawable(it)
        }
        return itemDecoration
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val menuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu , inflater)
    }
}