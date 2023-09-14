package com.chip.chiptest.utils

/**
 * Extension function to capitalize the first letter of a string.
 *
 * @return A new string with the first letter capitalized.
 */
fun String.capitalizeFirstLetter(): String {
    if (isEmpty()) {
        return this
    }
    val firstChar = this[0].uppercase()
    return firstChar + this.substring(1)
}
