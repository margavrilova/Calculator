package com.example.calculator.di

import android.content.Context
import com.example.calculator.data.SettingsDaoImplementation
import com.example.calculator.domain.SettingsDao

object SettingsDaoProvider {

    private var dao: SettingsDao? = null

    fun get(context: Context): SettingsDao {
        return dao ?: SettingsDaoImplementation(
            context.getSharedPreferences(
                "settings",
                Context.MODE_PRIVATE
            )
        ).also {
            dao = it
        }
    }

}