package com.coors.demoproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coors.demoproject.R
import com.coors.demoproject.databinding.FragmentCurrencyListBinding

class CurrencyListFragment : Fragment() {

    private lateinit var fragmentViewBinding : FragmentCurrencyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewBinding = FragmentCurrencyListBinding.inflate(inflater,container,false)
        return fragmentViewBinding.root
    }

}