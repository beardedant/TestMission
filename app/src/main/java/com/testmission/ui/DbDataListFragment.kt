package com.testmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.testmission.App
import com.testmission.databinding.FragmentDbDataListBinding
import com.testmission.room.DataIn
import com.testmission.room.DataInBase
import com.testmission.room.DataInDao

class DbDataListFragment : Fragment() {
    private var _binding: FragmentDbDataListBinding? = null
    private val binding
        get() = _binding!!

    private val db: DataInBase = App.database
    private val roomDao: DataInDao = db.dataInDao()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDbDataListBinding.inflate(layoutInflater)


        val strings = mutableListOf<DataIn>()
        try {
            Thread {
                strings.addAll(roomDao.getAll())
                requireActivity().runOnUiThread {
//                    binding.mainTvResult.text = strings.toString()
                }
            }.start()
        } catch (t: Throwable) {

        }

        return binding.root
    }
}