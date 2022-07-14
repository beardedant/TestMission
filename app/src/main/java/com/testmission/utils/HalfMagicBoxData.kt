package com.testmission.utils

data class HalfMagicBoxData(
    var matrix: MutableList<MutableList<Int>> = MutableList(3) { MutableList(3) { 0 } }
)