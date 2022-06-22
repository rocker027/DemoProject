package com.coors.demoproject.ui.testcompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.coors.demoproject.compose.BasicsTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coors.demoproject.ui.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class TestComposeViewModel : ViewModel() {

}