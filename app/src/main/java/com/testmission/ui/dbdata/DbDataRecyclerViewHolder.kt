package com.testmission.ui.dbdata

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.testmission.R
import com.testmission.room.DataIn

class DbDataRecyclerViewHolder(itemView: View, private val listener: DbDataClickListener) :
    RecyclerView.ViewHolder(itemView) {

    private val dataType = itemView.findViewById<TextView>(R.id.item_db_data_et_type)
    private val squareArray = itemView.findViewById<TextView>(R.id.item_db_data_et_square)
    private val containerArray =
        itemView.findViewById<TextView>(R.id.item_db_data_et_container_array)
    private val sortableArray =
        itemView.findViewById<TextView>(R.id.item_db_data_et_sortable_array)
    private val addNoteTime =
        itemView.findViewById<TextView>(R.id.item_db_data_et_timestamp)

    fun bindAll(dbValues: DataIn) {
        dataType.text = dbValues.type
        squareArray.text = dbValues.square
        containerArray.text = dbValues.square
        sortableArray.text = dbValues.square
        addNoteTime.text = dbValues.timeStamp.toString()

        itemView.findViewById<CardView>(R.id.item_db_data_card).setOnClickListener {
            listener.onItemDbDataClick(dbValues)
        }
    }
}