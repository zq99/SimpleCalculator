package com.zq99.simplecalculator

import com.githubzq99.simplecalculator.Calculation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculationUnitTest {
    @Test
    fun calculation_isCorrect() {
        val value1 = 10.0
        val value2 = 5.0
        val calculation = Calculation(value1,value2)
        assertEquals(15.0, calculation.getAddition(),0.00001)
        assertEquals(50.0, calculation.getProduct(),0.00001)
        assertEquals(5.0, calculation.getSubtraction(),0.00001)
        assertEquals(2.0, calculation.getDivision(),0.00001)
    }

    @Test
    fun divideByZero(){
        val value1 = 10.0
        val value2 = 5.0
        val calculation = Calculation(value1,value2)
        assertTrue(!calculation.isDivideByZero())
        val divideByZeroCalc = Calculation(value1,0.0)
        assertTrue(divideByZeroCalc.isDivideByZero())
    }
}