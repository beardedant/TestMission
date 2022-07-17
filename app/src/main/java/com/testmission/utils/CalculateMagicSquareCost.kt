package com.testmission.utils

import kotlin.math.absoluteValue


class CalculateMagicSquareCost {

    fun calculateCostFromEnumeration(inputArray: List<Int>): Int {
        val halfMagicBoxFirst = CreateHalfMagicSquare().createHalfMagicSquareEnumeration()

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