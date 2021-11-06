package com.example.calculator.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.calculateExpression

class MainViewModel : ViewModel() {

    private var expression: String = ""

    private val _expressionState = MutableLiveData(ExpressionState(expression, 0))
    val expressionState: LiveData<ExpressionState> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState: LiveData<String> = _resultState

    fun onNumberClick(number: Int, selection: Int) {
        expression = putInSelection(expression, number.toString(), selection)
        _expressionState.value = ExpressionState(expression, selection + 1)
        _resultState.value = calculateExpression(expression)
    }

    fun onOperatorClicker(operator: Operator, selection: Int) {
        expression = putInSelection(expression, operator.symbol, selection)
        _expressionState.value = ExpressionState(expression, selection + 1)
        _resultState.value = calculateExpression(expression)
    }

    fun onSqrtClicker(selection: Int) {
        expression = putInSelection(expression, "^(1/2)", selection)
        _expressionState.value = ExpressionState(expression, selection + 5)
        _resultState.value = calculateExpression(expression)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "onCleared")
    }

    private fun putInSelection(expression: String, put: String, selection: Int): String {
        return expression.substring(0, selection) + put + expression.substring(
            selection,
            expression.length
        )
    }

}

enum class Operator(val symbol: String) {
    MINUS("-"), PLUS("+"), MULTIPLY("*"), DIVIDE("/"),
    POWER("^"), BRACELEFT("("), BRACERIGHT(")")
}

class ExpressionState(val expression: String, val selection: Int) {

}