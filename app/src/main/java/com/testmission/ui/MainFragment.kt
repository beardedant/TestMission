package com.testmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.testmission.databinding.FragmentMainBinding
import com.testmission.utils.CalculateMagicBoxCost
import com.testmission.utils.Sorting

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

        binding.mainEtMagicBox.visibility = View.GONE
        binding.mainTvResult.visibility = View.GONE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = binding.mainRadioGroup
        radioGroup.setOnCheckedChangeListener { _, i ->
            visibility(i == binding.mainRdbtnArraySorting.id)
        }


        binding.mainBtnCalculation.setOnClickListener {
            val sortedString = binding.mainEtFirstArray.text.toString()
            val containerString = binding.mainEtSecondArray.text.toString()
            if (binding.mainRdbtnArraySorting.isChecked) {
                if (sortedString.isNotEmpty() && containerString.isNotEmpty()) {
                    val result = binding.mainTvResult
                    result.visibility = View.VISIBLE
                    result.text = Sorting().getSorted(sortedString, containerString)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "пустой массив, введите данные",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                if (binding.mainEtMagicBox.text.toString().isNotEmpty()) {
                    val inputArray =
                        binding.mainEtMagicBox.text!!.toString().split(" ").map { it.toInt() }

//                    var intStr = mutableListOf<Int>()
//                    for (i in 0..inputArray.lastIndex) {
//                        intStr.add(inputArray[i].toInt())
//                    }
                    val result = binding.mainTvResult
                    result.visibility = View.VISIBLE
                    result.text = CalculateMagicBoxCost().calculateCost(inputArray).toString()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "пустой массив, введите данные",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun visibility(visible: Boolean) {
        if (visible) {
            binding.mainEtMagicBox.visibility = View.GONE
            binding.mainEtFirstArray.visibility = View.VISIBLE
            binding.mainEtSecondArray.visibility = View.VISIBLE
            binding.mainTvResult.visibility = View.GONE
        } else {
            binding.mainEtMagicBox.visibility = View.VISIBLE
            binding.mainEtFirstArray.visibility = View.GONE
            binding.mainEtSecondArray.visibility = View.GONE
            binding.mainTvResult.visibility = View.GONE
        }
    }
}