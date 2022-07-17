package com.testmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.testmission.App
import com.testmission.R
import com.testmission.databinding.FragmentMainBinding
import com.testmission.room.DataIn

import com.testmission.room.DataInBase
import com.testmission.room.DataInDao
import com.testmission.utils.CalculateMagicSquareCost

import com.testmission.utils.Sorting

const val ARRAY_TYPE: String = "arrays"
const val MAGIC_SQUARE_TYPE: String = "square"

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    private val db: DataInBase = App.database
    private val roomDao: DataInDao = db.dataInDao()

    private lateinit var dataIn: DataIn

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
                if (isValidStringsArrays()) {
                    val result = binding.mainTvResult
                    result.visibility = View.VISIBLE
                    result.text = Sorting().getSorted(sortedString, containerString)
                } else {
                    errorMessage()
                }
            } else {
                if (isValidStringMagicSquare()) {
                    val inputArray =
                        binding.mainEtMagicBox.text!!.toString().split(" ").map { it.toInt() }
                    val result = binding.mainTvResult
                    result.visibility = View.VISIBLE

                    result.text =
                        CalculateMagicSquareCost().calculateCostFromEnumeration(inputArray)
                            .toString()

                } else {
                    errorMessage()
                }
            }
        }

        saveDataToDataBase()

        binding.mainBtnLoad.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_dbDataListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isValidStringMagicSquare(): Boolean {
        val regex = Regex("""^(\d\s){8}+\d$""")
        return regex.containsMatchIn(binding.mainEtMagicBox.text.toString())
    }

    private fun isValidStringsArrays(): Boolean =
        (!binding.mainEtFirstArray.text.isNullOrEmpty() && !binding.mainEtSecondArray.text.isNullOrEmpty())

    private fun errorMessage() {
        Toast.makeText(
            requireContext(),
            getString(R.string.error_message_string),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun successMessage() {
        Toast.makeText(
            requireContext(),
            getString(R.string.success_message_string),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun saveDataToDataBase() {
        binding.mainBtnSave.setOnClickListener {
            if (binding.mainRdbtnArraySorting.isChecked) {
                if (isValidStringsArrays()) {
                    dataIn = DataIn(
                        0,
                        ARRAY_TYPE,
                        null,
                        binding.mainEtFirstArray.text.toString(),
                        binding.mainEtSecondArray.text.toString(),
                        System.currentTimeMillis()
                    )
                    insertDataToDb(dataIn)
                    successMessage()
                } else
                    errorMessage()
            }

            if (binding.mainRdbtnMagicBox.isChecked) {
                if (isValidStringMagicSquare()) {
                    dataIn = DataIn(
                        0,
                        MAGIC_SQUARE_TYPE,
                        binding.mainEtMagicBox.text.toString(),
                        null,
                        null,
                        System.currentTimeMillis()
                    )
                    insertDataToDb(dataIn)
                    successMessage()
                } else
                    errorMessage()
            }
        }
    }

    private fun insertDataToDb(dataIn: DataIn) {
        try {
            Thread {
                roomDao.insert(dataIn)
            }.start()
        } catch (t: Throwable) {
        }
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