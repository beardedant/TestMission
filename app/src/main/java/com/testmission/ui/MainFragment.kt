package com.testmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.testmission.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)

        binding.mainInputMagicBox.visibility = View.GONE
        binding.mainResult.visibility = View.GONE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = binding.mainRadioGroup
        radioGroup.setOnCheckedChangeListener { _, i ->
            visibility(i == binding.mainArraySortingRdb.id)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun visibility(visible: Boolean) {
        if (visible) {
            binding.mainInputMagicBox.visibility = View.GONE
            binding.mainInputA1.visibility = View.VISIBLE
            binding.mainInputA2.visibility = View.VISIBLE
            binding.mainResult.visibility = View.GONE
        } else {
            binding.mainInputMagicBox.visibility = View.VISIBLE
            binding.mainInputA1.visibility = View.GONE
            binding.mainInputA2.visibility = View.GONE
            binding.mainResult.visibility = View.GONE
        }
    }
}