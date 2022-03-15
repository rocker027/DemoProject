package com.coors.demoproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.coors.demoproject.R
import com.coors.demoproject.databinding.ActivityDemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var activityBinding : ActivityDemoBinding

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
}