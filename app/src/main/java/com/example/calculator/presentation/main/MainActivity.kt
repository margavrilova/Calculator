package com.example.calculator.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.R
import com.example.calculator.databinding.MainActivityBinding
import com.example.calculator.presentation.common.BaseActivity
import com.example.calculator.presentation.settings.SettingsActivity
import com.example.calculator.presentation.settings.SettingsActivity.Companion.SETTINGS_RESULT_REQUEST_CODE

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
        viewBinding.mainEquals.setOnClickListener {
            viewBinding.mainResult.text = "="
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

        viewModel.expressionState.observe(this) { state ->
            viewBinding.mainInput.setText(state.expression)
            viewBinding.mainInput.setSelection(state.selection)
        }

        viewModel.resultState.observe(this) { state ->
            viewBinding.mainResult.text = state
        }
    }

    private fun openSettings() {
        Toast.makeText(this, "Открытие настроек", Toast.LENGTH_LONG).show()
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra(SettingsActivity.SETTINGS_RESULT_KEY, 10)
        startActivityForResult(intent, SETTINGS_RESULT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.getStringExtra(SettingsActivity.SETTINGS_RESULT_KEY)?.let {
            Toast.makeText(this, "result $it", Toast.LENGTH_SHORT).show()
        }
    }
}