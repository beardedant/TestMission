package com.testmission.utils

import kotlin.math.absoluteValue

class CalculateMagicBoxCost {
    fun calculateCostFromRandom(inputArray: List<Int>): Int {
        val halfMagicBoxFirst = CreateHalfMagicBox().createHalfMagicBoxesRandom()
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

    fun calculateCostFromEnumeration(inputArray: List<Int>): Int {
        val halfMagicBoxFirst = CreateHalfMagicBox().createHalfMagicSquare()

        var cost = 0
        for (i in 0..halfMagicBoxFirst.lastIndex) {
            var sum = 0
            for (j in 0..halfMagicBoxFirst[i].lastIndex) {
                 sum += (inputArray[j]-halfMagicBoxFirst[i][j]).absoluteValue
            }
            println(sum)
            if (i == 1 || sum < cost) cost = sum
        }

        return cost
    }
}