package com.example.calculator.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.R
import com.example.calculator.databinding.MainActivityBinding
import com.example.calculator.presentation.common.BaseActivity
import com.example.calculator.presentation.settings.ResultPanelType
import com.example.calculator.presentation.settings.SettingsActivity

//import com.example.calculator.presentation.settings.SettingsActivity.Companion.SETTINGS_RESULT_REQUEST_CODE

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val viewBinding by viewBinding(MainActivityBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewBinding.mainInput.apply {
            showSoftInputOnFocus = false
        }

        viewBinding.mainActivitySettings.setOnClickListener {
            openSettings()
        }

        listOf(
            viewBinding.mainZero,
            viewBinding.mainOne,
            viewBinding.mainTwo,
            viewBinding.mainThree,
            viewBinding.mainFour,
            viewBinding.mainFive,
            viewBinding.mainSix,
            viewBinding.mainSeven,
            viewBinding.mainEight,
            viewBinding.mainNine
        ).forEachIndexed { index, textView ->
            textView.setOnClickListener {
                viewModel.onNumberClick(
                    index,
                    viewBinding.mainInput.selectionStart
                )
            }
        }

        mapOf(
            Operator.PLUS to viewBinding.mainPlus,
            Operator.MINUS to viewBinding.mainMinus,
            Operator.MULTIPLY to viewBinding.mainMultiply,
            Operator.DIVIDE to viewBinding.mainDivide,
            Operator.POWER to viewBinding.mainPower,
            Operator.BRACELEFT to viewBinding.mainBraceLeft,
            Operator.BRACERIGHT to viewBinding.mainBraceRight,
            Operator.POINT to viewBinding.mainPoint
        ).forEach { (operator, textView) ->
            textView?.setOnClickListener {
                viewModel.onOperatorClicker(operator, viewBinding.mainInput.selectionStart)
            }
        }

        viewBinding.mainSqrt?.setOnClickListener {
            viewModel.onSqrtClicker(viewBinding.mainInput.selectionStart)
        }

        viewBinding.mainClear.setOnClickListener {
            viewModel.onClearClicker()
        }

        viewBinding.mainBack.setOnClickListener {
            viewModel.onBackClicker(viewBinding.mainInput.selectionStart)
        }

        viewBinding.mainEquals.setOnClickListener {
            viewModel.onEqualsClicker(viewBinding.mainInput.selectionStart)
        }

        viewModel.expressionState.observe(this) { state ->
            viewBinding.mainInput.setText(state.expression)
            viewBinding.mainInput.setSelection(state.selection)
        }

        viewModel.resultState.observe(this) { state ->
            viewBinding.mainResult.text = state
        }

        viewModel.resultPanelState.observe(this) {
            with(viewBinding.mainResult) {
                gravity = when (it) {
                    ResultPanelType.LEFT -> Gravity.START.or(Gravity.CENTER_VERTICAL)
                    ResultPanelType.RIGHT -> Gravity.END or (Gravity.CENTER_VERTICAL)
                    ResultPanelType.HIDE -> gravity
                }
                isVisible = it != ResultPanelType.HIDE
            }

        }
    }

    private fun openSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

}