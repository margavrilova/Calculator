package com.example.calculator.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fathzer.soft.javaluator.DoubleEvaluator
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testPlus() {
        val viewModel = MainViewModel()

        viewModel.onNumberClick(1, 0)
        viewModel.onOperatorClicker(Operator.PLUS, 1)
        viewModel.onNumberClick(4, 2)

        Assert.assertEquals("1+4", viewModel.expressionState.value?.expression)
        Assert.assertEquals("5", viewModel.resultState.value)
    }

    @Test
    fun testDivide() {
        val viewModel = MainViewModel()

        viewModel.onNumberClick(1, 0)
        viewModel.onNumberClick(2, 1)
        viewModel.onOperatorClicker(Operator.DIVIDE, 2)
        viewModel.onNumberClick(6, 3)

        Assert.assertEquals("12/6", viewModel.expressionState.value?.expression)
        Assert.assertEquals("2", viewModel.resultState.value)
    }

    @Test
    fun testPower() {
        val viewModel = MainViewModel()

        viewModel.onNumberClick(2, 0)
        viewModel.onOperatorClicker(Operator.POWER, 1)
        viewModel.onNumberClick(3, 2)

        Assert.assertEquals("2^3", viewModel.expressionState.value?.expression)
        Assert.assertEquals("8", viewModel.resultState.value)
    }

    @Test
    fun testSqrt() {
        val viewModel = MainViewModel()

        viewModel.onNumberClick(4, 0)
        viewModel.onSqrtClicker(1)

        Assert.assertEquals("4^(1/2)", viewModel.expressionState.value?.expression)
        Assert.assertEquals("2", viewModel.resultState.value)
    }

    @Test
    fun check() {
        val evaluator = DoubleEvaluator()
        val expression = "16^(1/2)"
        val result = evaluator.evaluate(expression)
        println("$expression = $result")
    }
}