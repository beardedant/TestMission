package com.testmission.utils

import kotlin.math.absoluteValue

class CalculateMagicBoxCost {
    fun calculateCost(inputArray: List<Int>): Int {
        val halfMagicBoxFirst = CreateHalfMagicBox().createHalfMagicBoxes()
        val hint = mutableListOf<Int>()

        for (i in 0..halfMagicBoxFirst.lastIndex)
            for (j in 0..halfMagicBoxFirst[i].lastIndex)
                hint.add(halfMagicBoxFirst[i][j])

        println(hint)
        var cost = 0
        for (i in 0..hint.lastIndex) {
            cost += (inputArray[i] - hint[i]).absoluteValue
        }
        return cost
    }
}