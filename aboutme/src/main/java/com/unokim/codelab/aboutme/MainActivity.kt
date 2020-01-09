package com.unokim.codelab.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        done_button.setOnClickListener { addNickname() }
        nickname_text.setOnClickListener { updateNickname() }
    }

    private fun addNickname() {
        nickname_text.text = nickname_edit.text

        nickname_edit.visibility = View.GONE
        done_button.visibility = View.GONE
        nickname_text.visibility = View.VISIBLE

        // Hide the keyboard.
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(done_button.windowToken, 0)
    }

    private fun updateNickname() {
        nickname_edit.visibility = View.VISIBLE
        done_button.visibility = View.VISIBLE
        nickname_text.visibility = View.GONE

        // Set the focus to the edit text.
        nickname_edit.requestFocus()
        // Show the keyboard.
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(nickname_edit, 0)
    }
}
