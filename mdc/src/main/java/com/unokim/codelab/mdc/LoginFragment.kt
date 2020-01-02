package com.unokim.codelab.mdc

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unokim.codelab.logger.Logger
import kotlinx.android.synthetic.main.shr_login_fragment.*
import kotlinx.android.synthetic.main.shr_login_fragment.view.*

/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Logger.d(TAG, "onCreateView")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        // Set an error if the password is less than 8 characters.
        view.next_button.setOnClickListener {
            if (!isPasswordValid(password_edit_text.text)) {
                password_text_input.error = getString(R.string.shr_error_password)
            } else {
                // Clear the error.
                password_text_input.error = null
                // Navigate to the next Fragment.
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }
        // Clear the error once more than 8 characters are typed.
        view.password_edit_text.setOnKeyListener { v, keyCode, event ->
            if (isPasswordValid(password_edit_text.text)) {
                // Clear the error.
                password_text_input.error = null
            }
            false
        }

        return view
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    private fun isPasswordValid(text: Editable?): Boolean = text != null && text.length >= 8


    companion object {
        private const val TAG = "LoginFragment"
    }
}
