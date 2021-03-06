package com.example.calculator.presentation.settings

import android.app.AlertDialog
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.R
import com.example.calculator.databinding.SettingsActivityBinding
import com.example.calculator.di.SettingsDaoProvider
import com.example.calculator.domain.entity.ResultPanelType
import com.example.calculator.presentation.common.BaseActivity

class SettingsActivity : BaseActivity() {

    private val viewBinding by viewBinding(SettingsActivityBinding::bind)
    private val viewModel by viewModels<SettingsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SettingsViewModel(SettingsDaoProvider.get(this@SettingsActivity)) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        viewBinding.settingsBack.setOnClickListener {
            finish()
        }
        viewBinding.resultPanelContainer.setOnClickListener {
            viewModel.onResultPanelTypeClicked()
        }

        viewBinding.seekBarPrecision.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                viewBinding.precisionText.text =
                    "Знаков после запятой: " + seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                viewModel.onPrecisionChanged(seekBar.progress)
            }
        })

        viewBinding.seekBarVibration.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                viewBinding.vibrationText.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                viewModel.onVibrationChanged(seekBar.progress)
            }
        })

        viewModel.resultPanelState.observe(this) { state ->
            viewBinding.resultPanelDescription.text =
                resources.getStringArray(R.array.result_panel_types)[state.ordinal]
        }
        viewModel.openResultPanelAction.observe(this) { type ->
            showDialog(type)
        }

        viewModel.precisionResult.observe(this) {
            viewBinding.seekBarPrecision.progress = it
        }

        viewModel.vibrationTime.observe(this) {
            viewBinding.seekBarVibration.progress = it
        }
    }


    private fun showDialog(type: ResultPanelType) {
        var result: Int? = null
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(getString(R.string.settings_result_panel_title))
            setPositiveButton("Ok") { dialog, id ->
                result?.let { viewModel.onResultPanelTypeChanged(ResultPanelType.values()[it]) }
            }
            setNegativeButton("Отмена") { dialog, id ->
            }
            setSingleChoiceItems(R.array.result_panel_types, type.ordinal) { dialog, id ->
                result = id
            }
                .show()
        }
    }
}