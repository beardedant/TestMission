package com.testmission.ui.dbdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testmission.App
import com.testmission.R
import com.testmission.databinding.FragmentDbDataListBinding
import com.testmission.room.DataIn
import com.testmission.room.DataInBase
import com.testmission.room.DataInDao
import com.testmission.ui.MainFragmentArgs

class DbDataListFragment : Fragment(), DbDataClickListener {
    private var _binding: FragmentDbDataListBinding? = null
    private val binding
        get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDbDataListBinding.inflate(layoutInflater)

        if (arguments != null && !arguments!!.isEmpty) {
            val safeArgs = DbDataListFragmentArgs.fromBundle(arguments!!).dataInList

            val dbItemsHolder = binding.fragmentDbDataListRecycler
            val layoutManager = LinearLayoutManager(requireActivity())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            dbItemsHolder.layoutManager = layoutManager

            val adapter = DbDataRecyclerAdapter(safeArgs.toList(), this)
            dbItemsHolder.adapter = adapter
        }
        return binding.root
    }

    override fun onItemDbDataClick(dataIn: DataIn) {
        val action = DbDataListFragmentDirections.actionDbDataListFragmentToMainFragment(dataIn)
        findNavController().navigate(action)
    }
}