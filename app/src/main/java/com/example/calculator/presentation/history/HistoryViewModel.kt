package com.example.calculator.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.entity.HistoryItem
import com.example.calculator.presentation.settings.SingleLiveEvent

class HistoryViewModel : ViewModel() {

    private val historyItems: List<HistoryItem> = listOf(
        HistoryItem("14*6+62", "146"),
        HistoryItem("200+30+3*3", "239"),
        HistoryItem("10^3+4", "1004"),
        HistoryItem("239-93", "146"),
        HistoryItem("20*100+21", "2021"),
        HistoryItem("14*6+62", "146"),
        HistoryItem("200+30+3*3", "239"),
        HistoryItem("10^3+4", "1004"),
        HistoryItem("239-93", "146"),
        HistoryItem("20*100+21", "2021")
    )

    private val _historyItemsState = MutableLiveData<List<HistoryItem>>()
    val historyItemsState: LiveData<List<HistoryItem>> = _historyItemsState

    private val _showToastAction = SingleLiveEvent<Unit>()
    val showToastAction: LiveData<Unit> = _showToastAction


    init {
        _historyItemsState.value = historyItems
    }

    fun onItemClicked(historyItem: HistoryItem) {

    }
}

