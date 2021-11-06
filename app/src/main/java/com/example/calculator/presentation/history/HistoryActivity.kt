package com.example.calculator.presentation.history

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.R
import com.example.calculator.databinding.HistoryAcitivityBinding
import com.example.calculator.presentation.common.BaseActivity

class HistoryActivity : BaseActivity() {

    private val viewBinding by viewBinding(HistoryAcitivityBinding::bind)
    private val viewModel by viewModels<HistoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_acitivity)

        val historyAdapter = HistoryAdapter(viewModel::onItemClicked)
        with(viewBinding.list) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = historyAdapter
        }
        viewBinding.historyBack.setOnClickListener {
            finish()
        }
        viewModel.historyItemsState.observe(this) { state ->
            historyAdapter.setData(state)
        }
        viewModel.showToastAction.observe(this) { state ->
            Toast.makeText(this, "Нажатие", Toast.LENGTH_SHORT).show()
        }
    }
}