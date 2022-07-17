package com.testmission.ui.dbdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val dbItemsHolder = binding.fragmentDbDataListRecycler
        val layoutManager = LinearLayoutManager(requireActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        dbItemsHolder.layoutManager = layoutManager

        try {
            Thread {
                val adapter = DbDataRecyclerAdapter(roomDao.getAll())
                dbItemsHolder.adapter = adapter

                requireActivity().runOnUiThread {
                    adapter.notifyDataSetChanged()
                }

            }.start()
        } catch (t: Throwable) {

        }

        return binding.root
    }
}