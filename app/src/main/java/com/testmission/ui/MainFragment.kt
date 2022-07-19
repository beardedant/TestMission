package com.testmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.testmission.App
import com.testmission.R
import com.testmission.databinding.FragmentMainBinding
import com.testmission.fileoperation.FileRepo
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)

        binding.mainEtMagicBox.visibility = View.GONE
        binding.mainTvResult.visibility = View.GONE

        if (arguments != null && !arguments!!.isEmpty) {
            val safeArgs = MainFragmentArgs.fromBundle(arguments!!).dataIn

            binding.mainEtMagicBox.setText(safeArgs.square)
            binding.mainEtFirstArray.setText(safeArgs.sortingArray)
            binding.mainEtSecondArray.setText(safeArgs.containerArray)

            getResultSortedArrays()
            getResultMagicSquare()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = binding.mainRadioGroup
        radioGroup.setOnCheckedChangeListener { _, i ->
            visibility(i == binding.mainRdbtnArraySorting.id)
        }

        binding.mainBtnCalculation.setOnClickListener {
            if (binding.mainRdbtnArraySorting.isChecked) {
                getResultSortedArrays()
            } else {
                getResultMagicSquare()
            }
        }

        saveDataToDataBase()

        loadFromDb()

        saveDataToFile()

        loadFromFile()
    }

    private fun loadFromFile() {
        binding.mainBtnImport.setOnClickListener {
            val dataList = FileRepo().readFile(requireContext())
            val dataInList = mutableListOf<DataIn>()
            for (i in 0..dataList.lastIndex) {
                val string = dataList[i].split(", ")
                dataInList.add(
                    DataIn(
                        0,
                        string[0],
                        string[1],
                        string[2],
                        string[3],
                        string[4].toLong()
                    )
                )
            }
            val action =
                MainFragmentDirections.actionMainFragmentToDbDataListFragment(dataInList.toTypedArray())
            findNavController().navigate(action)
        }
    }

    private fun loadFromDb() {
        binding.mainBtnLoad.setOnClickListener {
            try {
                Thread {
                    val dataInList = roomDao.getAll()
                    requireActivity().runOnUiThread {
                        val action =
                            MainFragmentDirections.actionMainFragmentToDbDataListFragment(dataInList.toTypedArray())
                        findNavController().navigate(action)
                    }
                }.start()
            } catch (t: Throwable) {
                error(t.message.toString())
            }
        }
    }

    private fun saveDataToFile() {
        binding.mainBtnExport.setOnClickListener {
            val dataIn = if (binding.mainRdbtnArraySorting.isChecked) {
                DataIn(
                    0,
                    ARRAY_TYPE,
                    "null",
                    binding.mainEtFirstArray.text.toString(),
                    binding.mainEtSecondArray.text.toString(),
                    System.currentTimeMillis()
                )
            } else {
                DataIn(
                    0,
                    MAGIC_SQUARE_TYPE,
                    binding.mainEtMagicBox.text.toString(),
                    "null",
                    "null",
                    System.currentTimeMillis()
                )
            }
            FileRepo().saveToFile(requireContext(), dataIn)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getResultSortedArrays() {
        if (isValidStringsArrays()) {
            val sortedString = binding.mainEtFirstArray.text.toString()
            val containerString = binding.mainEtSecondArray.text.toString()
            val result = binding.mainTvResult
            result.visibility = View.VISIBLE
            result.text = Sorting().getSorted(sortedString, containerString)
            binding.mainRdbtnArraySorting.isChecked = true
        } else {
            errorMessage()
        }
    }

    private fun getResultMagicSquare() {
        if (isValidStringMagicSquare()) {
            val inputArray =
                binding.mainEtMagicBox.text!!.toString().split(" ").map { it.toInt() }
            val result = binding.mainTvResult
            result.visibility = View.VISIBLE

            result.text =
                CalculateMagicSquareCost().calculateCostFromEnumeration(inputArray)
                    .toString()
            binding.mainRdbtnMagicBox.isChecked = true
            binding.mainEtFirstArray.visibility = View.GONE
            binding.mainEtSecondArray.visibility = View.GONE
            binding.mainEtMagicBox.visibility = View.VISIBLE
        } else {
            errorMessage()
        }
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
            errorMessage()
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