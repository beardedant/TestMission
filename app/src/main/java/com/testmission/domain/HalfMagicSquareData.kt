package com.testmission.domain

data class HalfMagicSquareData(
    var matrix: MutableList<MutableList<Int>> = MutableList(3) { MutableList(3) { 0 } }
)