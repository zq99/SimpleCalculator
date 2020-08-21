package com.zq99.simplecalculator

import com.githubzq99.simplecalculator.CalculatorDisplay
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class CalculatorDisplayUnitTest {
    @Test
    fun addDigitTest() {
        val display = CalculatorDisplay()
        for(i in 1..5){
            display.addDigit(i)
        }
        assertEquals("12345",display.getDisplayText())
        assertEquals(12345.0,display.getDisplayAsValue(),0.00001)
    }

    @Test
    fun getDisplayNumberTest() {
        val display = CalculatorDisplay()
        display.addNumberToDisplay(888.0)
        assertEquals(888.0,display.getDisplayAsValue(),0.00001)
    }

    @Test
    fun addToDisplayTest() {
        val display = CalculatorDisplay()
        display.addNumberToDisplay(2020.toDouble())
        assertEquals(2020.0,display.getDisplayAsValue(),0.00001)
        assertEquals("2020",display.getDisplayText())
    }

    @Test
    fun resetTest() {
        val display = CalculatorDisplay()
        display.addNumberToDisplay(2020.toDouble())
        assertEquals("2020",display.getDisplayText())
        display.reset()
        assertEquals("0",display.getDisplayText())
    }

    @Test
    fun removeLastTest() {
        val display = CalculatorDisplay()
        display.addNumberToDisplay(20209.toDouble())
        assertEquals("20209",display.getDisplayText())
        display.removeLastDigit()
        assertEquals("2020",display.getDisplayText())
    }

    @Test
    fun addDecimalTest() {
        val display = CalculatorDisplay()
        display.addNumberToDisplay(2020.toDouble())
        assertEquals("2020",display.getDisplayText())
        display.addDecimal()
        display.addDigit(5)
        assertEquals("2020.5",display.getDisplayText())
        display.addDecimal()
        assertEquals("2020.5",display.getDisplayText())
        display.addNumberToDisplay(12.toDouble())
        display.addDecimal()
        assertEquals(12.0,display.getDisplayAsValue(),0.0001)
    }

    @Test
    fun setErrorTest() {
        val display = CalculatorDisplay()
        display.setError()
        assertTrue(display.isErrorDisplayed())
        display.reset()
        assertFalse(display.isErrorDisplayed())
    }

    @Test
    fun addSign() {
        val display = CalculatorDisplay()
        display.addDigit(8)
        assertEquals("8",display.getDisplayText())
        display.addSign()
        assertEquals("-8",display.getDisplayText())
        display.addSign()
        assertEquals("8",display.getDisplayText())
    }
}