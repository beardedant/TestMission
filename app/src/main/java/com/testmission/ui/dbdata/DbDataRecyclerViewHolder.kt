package com.testmission.ui.dbdata

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.testmission.R
import com.testmission.room.DataIn
import com.testmission.ui.MAGIC_SQUARE_TYPE
import com.testmission.utils.TimeStampToNormalTime

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
        if (dataType.text == MAGIC_SQUARE_TYPE) {
            containerArray.visibility = View.GONE
            sortableArray.visibility = View.GONE
        } else {
            squareArray.visibility = View.GONE
        }
        squareArray.text = dbValues.square
        containerArray.text = dbValues.containerArray
        sortableArray.text = dbValues.sortingArray

        addNoteTime.text =
            TimeStampToNormalTime().toNormalDateFromString(dbValues.timeStamp)
//        addNoteTime.text = dbValues.timeStamp.toString()

        itemView.findViewById<CardView>(R.id.item_db_data_card).setOnClickListener {
            listener.onItemDbDataClick(dbValues)
        }
    }
}