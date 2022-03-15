package com.coors.demoproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.coors.demoproject.R
import com.coors.demoproject.databinding.ActivityDemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivityDemoBinding

    private val viewModel by viewModels<DemoActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        activityBinding.initViews()
    }

    private fun ActivityDemoBinding.initViews() {
        btnLoading.setOnClickListener {
            viewModel.onClickLoading()
        }

        btnSorting.setOnClickListener {
            viewModel.onClickSorting()
        }
    }

    private fun initViewBinding() {
        activityBinding = ActivityDemoBinding.inflate(LayoutInflater.from(this))
        setContentView(activityBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
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
        return super.onCreateOptionsMenu(menu)
    }
}