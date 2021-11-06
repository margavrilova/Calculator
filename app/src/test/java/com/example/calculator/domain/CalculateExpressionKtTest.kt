package com.example.calculator.domain
//package com.example.calculator.domain

import org.junit.Assert
import org.junit.Test

class CalculateExpressionKtTest {

    @Test
    fun testAddition() {
        val expression = "1+4"
        val result = "5"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testSubtraction() {
        val expression = "6-4"
        val result = "2"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testMultiplication() {
        val expression = "2*73"
        val result = "146"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testDivision() {
        val expression = "146/2"
        val result = "73"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testExpression() {
        val expression = "1+4*6"
        val result = "25"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testExtraSymbols() {
        val expression = "146+"
        val result = "146"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testSeveralInputs() {
        testCalculation("", "")
        testCalculation("+", "")
        testCalculation("1", "1")
        testCalculation("14", "14")
        testCalculation("14-6", "8")
        testCalculation("14-6+", "8")
    }

    private fun testCalculation(expression: String, result: String) {
        Assert.assertEquals(result, calculateExpression(expression))
    }
}