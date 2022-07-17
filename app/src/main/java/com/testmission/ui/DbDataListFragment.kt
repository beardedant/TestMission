package com.testmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.testmission.databinding.FragmentDbDataListBinding

class DbDataListFragment : Fragment() {
    private var _binding: FragmentDbDataListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDbDataListBinding.inflate(layoutInflater)
        return binding.root
    }
}