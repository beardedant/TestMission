package com.testmission.utils

import com.testmission.domain.HalfMagicSquareData
import kotlin.random.Random

class CreateHalfMagicSquare {

    fun createHalfMagicBoxesRandom(): List<List<Int>> {
        val halMagicBoxesList = mutableSetOf<HalfMagicSquareData>()
        for (k in 1..9) {
            var matrixThirdDegrees = createMatrixWithStartingValue(k)
            var x0 = 0
            var x1 = 1
            var x2 = 2
            var y0 = 0
            var y1 = 1
            var y2 = 2
            var d1 = 1
            var d2 = 2
            var count = 0
            while (!(x0 == x1 && x1 == x2 && y0 == y1 && y1 == y2)) {
                for (i in 0..2) {
                    for (j in 0..2) {

                        val r1 = Random.nextInt(0, 2)
                        val r2 = Random.nextInt(0, 2)
                        val tmp = matrixThirdDegrees.matrix[r1][r2]

                        if (!(i == j && i == 0)) {
                            if (!(r1 == 0 && r2 == 0)) {
                                matrixThirdDegrees.matrix[r1][r2] = matrixThirdDegrees.matrix[i][j]
                                matrixThirdDegrees.matrix[i][j] = tmp
                            }
                        }
                    }
                }
                x0 = with(matrixThirdDegrees) { matrix[0][0] + matrix[0][1] + matrix[0][2] }
                x1 = with(matrixThirdDegrees) { matrix[1][0] + matrix[1][1] + matrix[1][2] }
                x2 = with(matrixThirdDegrees) { matrix[2][0] + matrix[2][1] + matrix[2][2] }
                y0 = with(matrixThirdDegrees) { matrix[0][0] + matrix[1][0] + matrix[2][0] }
                y1 = with(matrixThirdDegrees) { matrix[0][1] + matrix[1][1] + matrix[2][1] }
                y2 = with(matrixThirdDegrees) { matrix[0][2] + matrix[1][2] + matrix[2][2] }
                d1 = with(matrixThirdDegrees) { matrix[0][0] + matrix[1][1] + matrix[2][2] }
                d2 = with(matrixThirdDegrees) { matrix[0][2] + matrix[1][1] + matrix[2][0] }
                count++
            }
            if (!(d1 == 15 || d2 == 15))
                halMagicBoxesList.add(matrixThirdDegrees)
        }

        return halMagicBoxesList.random().matrix
    }

    private fun createMatrixWithStartingValue(value: Int): HalfMagicSquareData {
        var matrix = HalfMagicSquareData()
        val values = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9).toMutableList()
        values.remove(value)
        matrix.matrix[0][0] = value
        var count = 0
        for (i in 0..2)
            for (j in 0..2) {
                if (!(i == j && i == 0)) {
                    matrix.matrix[i][j] = values[count]
                    count++
                }
            }
        return matrix
    }

    fun createHalfMagicSquareEnumeration(): MutableList<List<Int>> {
        // x11 x12 x13 x14
        // x21 x22 x23 x24
        // x31 x32 x33 x34
        // x41 x42 x43 x44
        val halMagicBoxesList = mutableListOf<List<Int>>()
        val mC = 15
        var squares = 0;
        val digits = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        for (x11 in 1..9) {
            var set12 = digits.toCollection(mutableListOf())
            set12.remove(x11)

            for (it12 in set12) {
                val x12 = it12
                val set13 = set12.toCollection(mutableListOf())
                set13.remove(x12)

                for (it13 in set13) {
                    val x13 = it13
                    val set21 = set13.toCollection(mutableListOf())
                    set21.remove(x13)

                    for (it21 in set21) {
                        val x21 = it21
                        val set22 = set21.toCollection(mutableListOf())
                        set22.remove(x21)

                        for (it22 in set22) {
                            val x22 = it22;
                            val set23 = set22.toCollection(mutableListOf())
                            set23.remove(x22)

                            for (it23 in set23) {
                                val x23 = it23
                                val set31 = set23.toCollection(mutableListOf())
                                set31.remove(x23)

                                for (it31 in set31) {
                                    val x31 = it31;
                                    val set32 = set31.toCollection(mutableListOf())
                                    set32.remove(x31)

                                    for (it32 in set32) {
                                        val x32 = it32
                                        val set33 = set32.toCollection(mutableListOf())
                                        set33.remove(x32)
                                        val x33 = set33.first()

                                        val sh1 = x11 + x12 + x13
                                        val sh2 = x21 + x22 + x23
                                        val sh3 = x31 + x32 + x33

                                        val sv1 = x11 + x21 + x31
                                        val sv2 = x12 + x22 + x32
                                        val sv3 = x13 + x23 + x33

                                        val sd1 = x11 + x22 + x33
                                        val sd2 = x31 + x22 + x13

                                        squares++

                                        if ((sh1 == sh2 && sh2 == sh3 && sv1 == sv2 && sv2 == sv3))
                                            if (sd1 != mC && sd2 != mC) {
                                                val halfBox = listOf(
                                                    x11, x12, x13, x21, x22, x23, x31, x32, x33
                                                )
                                                halMagicBoxesList.add(halfBox)
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return halMagicBoxesList
    }
}