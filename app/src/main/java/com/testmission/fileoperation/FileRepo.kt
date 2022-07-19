package com.testmission.fileoperation

import android.content.Context
import com.testmission.room.DataIn
import com.testmission.ui.MAGIC_SQUARE_TYPE
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class FileRepo {
    fun createFile(context: Context): File {
        val path = context.applicationInfo.dataDir
        val fileName = ("$path/datain.txt")
        val file = File(fileName)
        file.createNewFile()
        return file
    }

    fun saveToFile(context: Context, dataInt: DataIn) {
        val file = createFile(context)
        try {
            val fw = FileWriter(file, true)
            val pw = PrintWriter(fw)

            val string = if (dataInt.type == MAGIC_SQUARE_TYPE) {
                "${dataInt.type}, ${dataInt.square}, none, none, ${dataInt.timeStamp}"
            } else {
                "${dataInt.type}, none, ${dataInt.sortingArray}, ${dataInt.containerArray}, ${dataInt.timeStamp}"
            }
            pw.println(string)
            pw.flush()
            pw.close()
            fw.close()

        } catch (e: Exception) {
        }
    }

    fun readFile(context: Context): List<String> {
        val file = createFile(context)
        return file.readLines()
    }
}