package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.calculator.SettingsActivity.Companion.SETTINGS_RESULT_REQUEST_CODE

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val button: Button = findViewById<Button>(R.id.main_activity_settings)

        button.setOnClickListener {
            openSettings()
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