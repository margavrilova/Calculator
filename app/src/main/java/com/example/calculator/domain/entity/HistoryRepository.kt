package com.example.calculator.domain.entity

interface HistoryRepository {

    suspend fun add(historyItem: HistoryItem)

    suspend fun getAll(): List<HistoryItem>
}