package com.example.calculator.domain

import com.example.calculator.domain.entity.ResultPanelType

interface SettingsDao {

    /**
     * Устанавливает тип отображения панели результата
     */
    suspend fun setResultPanelType(resultPanelType: ResultPanelType)

    /**
     * Получает тип отображения панели результата
     */
    suspend fun getResultPanelType(): ResultPanelType
}