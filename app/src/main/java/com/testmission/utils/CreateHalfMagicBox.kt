package com.testmission.utils

import kotlin.random.Random

class CreateHalfMagicBox {

    fun createHalfMagicBoxes(): List<List<Int>> {
        val halMagicBoxesList = mutableSetOf<HalfMagicBoxData>()

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

    private fun createMatrixWithStartingValue(value: Int): HalfMagicBoxData {
        var matrix = HalfMagicBoxData()
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
}