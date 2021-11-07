package com.example.calculator.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.calculateExpression
import com.example.calculator.domain.entity.HistoryItem
import com.example.calculator.domain.entity.HistoryRepository
import com.example.calculator.domain.entity.ResultPanelType
import kotlinx.coroutines.launch

class MainViewModel(
    private val settingsDao: SettingsDao,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private var expression: String = ""

    private val _expressionState = MutableLiveData(ExpressionState(expression, 0))
    val expressionState: LiveData<ExpressionState> = _expressionState

    private val _resultState = MutableLiveData<String>()
    val resultState: LiveData<String> = _resultState

    private val _resultPanelState = MutableLiveData<ResultPanelType>(ResultPanelType.RIGHT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
        }
    }

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

    fun onClearClicker() {
        expression = ""
        _expressionState.value = ExpressionState(expression, 0)
        _resultState.value = ""
    }

    fun onBackClicker(selection: Int) {
        if (selection == 0) {
            return
        }
        expression = StringBuilder(expression).deleteAt(selection - 1).toString()
        _expressionState.value = ExpressionState(expression, selection - 1)
    }

    fun onEqualsClicker(selection: Int) {
        val result = calculateExpression(expression)
        viewModelScope.launch {
            historyRepository.add(HistoryItem(expression, result))
        }
        _resultState.value = result
        _expressionState.value = ExpressionState(result, result.length)
        expression = result
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

    fun onStart() {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
        }
    }

    fun onHistoryResult(item: HistoryItem?) {
        if (item != null) {
            expression = item.expression
            _expressionState.value = ExpressionState(expression, expression.length)
            _resultState.value = item.result
        }
    }
}