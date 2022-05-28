package com.rulhouse.evgawatcher

object StringMethods {
    fun removeStringCharacter(str: String, charList: List<Char>): String {
        if (str.isEmpty())
            return ""
        var nowStr = str
        charList.forEach { char ->
            if (nowStr.contains(char)) {
                val removedIndex = nowStr.indexOf(char)
                // If the char doesn't exist
                if (removedIndex != -1) {
                    nowStr = nowStr.substring(0, removedIndex) +
                            nowStr.substring(removedIndex + 1, nowStr.length)
                }
            }
        }
        return nowStr
    }
}