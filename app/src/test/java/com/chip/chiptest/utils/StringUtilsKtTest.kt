package com.chip.chiptest.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test


class StringUtilsKtTest{

    @Test
    fun `capitalizeFirstLetter should capitalize the first letter of a non-empty string`() {
        // Arrange
        val input = "example"

        // Act
        val result = input.capitalizeFirstLetter()

        // Assert
        assertEquals("Example", result)
    }

    @Test
    fun `capitalizeFirstLetter should handle an empty string`() {
        // Arrange
        val input = ""

        // Act
        val result = input.capitalizeFirstLetter()

        // Assert
        assertEquals("", result)
    }

    @Test
    fun `capitalizeFirstLetter should handle a single-character string`() {
        // Arrange
        val input = "x"

        // Act
        val result = input.capitalizeFirstLetter()

        // Assert
        assertEquals("X", result)
    }
}