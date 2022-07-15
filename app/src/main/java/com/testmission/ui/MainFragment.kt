package com.testmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.testmission.App
import com.testmission.databinding.FragmentMainBinding
import com.testmission.room.DataInBase
import com.testmission.room.DataIn
import com.testmission.room.DataInDao
import com.testmission.utils.CalculateMagicSquareCost
import com.testmission.utils.Sorting
import kotlin.concurrent.thread

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    private val db: DataInBase = App.database
    val roomDao: DataInDao = db.dataInDao()

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
                    val result = binding.mainTvResult
                    result.visibility = View.VISIBLE
                    result.text = CalculateMagicSquareCost().calculateCost(inputArray).toString()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "пустой массив, введите данные",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.mainBtnSave.setOnClickListener {
            val inputString = binding.mainEtMagicBox.text.toString()
            val dataIn = DataIn(0, inputString, "square", System.currentTimeMillis())
            try {
                Thread {
                    roomDao.insert(dataIn)
                }.start()
            } catch (t: Throwable) {

            }


            Toast.makeText(requireContext(), "матрица добавлена в базу данных", Toast.LENGTH_SHORT)
                .show()
        }

        binding.mainBtnLoad.setOnClickListener {
            var strings = mutableListOf<DataIn>()
            try {
                Thread {
                    strings.addAll(roomDao.getAll())
                    requireActivity().runOnUiThread {
                        binding.mainTvResult.text = strings.toString()
                    }
                }.start()
            } catch (t: Throwable) {

            }

            binding.mainTvResult.text = strings.toString()

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