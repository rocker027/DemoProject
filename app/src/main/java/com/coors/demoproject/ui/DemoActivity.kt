package com.coors.demoproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.coors.demoproject.R
import com.coors.demoproject.databinding.ActivityDemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var activityBinding : ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
    }

    private fun initViewBinding() {
        activityBinding = ActivityDemoBinding.inflate(LayoutInflater.from(this))
        setContentView(activityBinding.root)
    }
}