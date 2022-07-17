package com.testmission.ui.dbdata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testmission.R
import com.testmission.room.DataIn

class DbDataRecyclerAdapter(private val items: List<DataIn>) :
    RecyclerView.Adapter<DbDataRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DbDataRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_db_data, parent, false)
        return DbDataRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DbDataRecyclerViewHolder, position: Int) {
    holder.bindAll(items[position])
    }

    override fun getItemCount(): Int = items.size
}