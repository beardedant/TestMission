package com.testmission.ui.dbdata

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.testmission.R
import com.testmission.room.DataIn

class DbDataRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val dataType = itemView.findViewById<TextInputEditText>(R.id.item_db_data_et_type)
    private val squareArray = itemView.findViewById<TextInputEditText>(R.id.item_db_data_et_square)
    private val containerArray =
        itemView.findViewById<TextInputEditText>(R.id.item_db_data_et_container_array)
    private val sortableArray =
        itemView.findViewById<TextInputEditText>(R.id.item_db_data_et_sortable_array)
    private val addNoteTime =
        itemView.findViewById<TextInputEditText>(R.id.item_db_data_et_timestamp)

    fun bindAll(dbValues: DataIn) {
        dataType.setText(dbValues.type)
        squareArray.setText(dbValues.value)
        containerArray.setText(dbValues.value)
        sortableArray.setText(dbValues.value)
        addNoteTime.setText(dbValues.timeStamp.toString())
    }
}