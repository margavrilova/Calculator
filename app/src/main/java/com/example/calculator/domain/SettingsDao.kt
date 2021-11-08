package com.example.calculator.domain

import com.example.calculator.domain.entity.ResultPanelType

interface SettingsDao {

    /** Тип отображения панели результата */
    suspend fun setResultPanelType(resultPanelType: ResultPanelType)
    suspend fun getResultPanelType(): ResultPanelType

    /** Точность вычислений */
    suspend fun setPrecision(precisionNumber: Int)
    suspend fun getPrecision(): Int

    suspend fun setVibration(vibration: Int)
    suspend fun getVibration(): Int?


    /** Сила вибрации */
    //suspend fun setVibrationType(vibrationType: VibrationType) TODO
    //suspend fun getVibrationType(): VibrationType TODO
}