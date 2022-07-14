package com.testmission.utils

class Sorting() {

    fun getSorted(sortableString: String, containerString: String): String {
        val sortableArray = sortableString.split(" ")
        val containerArray = containerString.split(" ")
        val result = sortedSetOf<String>()

        for (i in containerArray) {
            for (j in sortableArray) {
                if (i.contains(j)) result.add(j)
            }
        }
        return result.sortedDescending().toString()
    }
}