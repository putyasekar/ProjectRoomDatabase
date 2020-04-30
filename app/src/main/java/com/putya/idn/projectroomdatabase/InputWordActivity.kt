package com.putya.idn.projectroomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.putya.idn.projectroomdatabase.databinding.ActivityInputWordBinding

class InputWordActivity : AppCompatActivity() {
    private lateinit var inputWordText: EditText

    companion object {
        const val EXTRA_REPLAY = "com.putya.idn.wordlistsql.REPLAY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityInputWordBinding>(
            this, R.layout.activity_input_word
        )
        inputWordText = binding.etName
        inputWordText = binding.etAge
        inputWordText = binding.etEmail

        val button = binding.btnSave
        button.setOnClickListener {
            val moveData = Intent()
            if (TextUtils.isEmpty(inputWordText.text)) {
                setResult(Activity.RESULT_CANCELED, moveData)
            } else {
                val word = inputWordText.text.toString()
                moveData.putExtra(EXTRA_REPLAY, word)
                setResult(Activity.RESULT_OK, moveData)
            }
            finish()
        }
    }
}
