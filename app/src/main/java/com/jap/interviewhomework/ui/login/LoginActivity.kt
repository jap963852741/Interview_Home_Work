package com.jap.interviewhomework.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jap.interviewhomework.R
import com.jap.interviewhomework.databinding.ActivityLoginBinding
import com.jap.interviewhomework.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var viewbinding: ActivityLoginBinding

    companion object{
        const val LOGIN_DATA = "LoginData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        val username = viewbinding.username
        val password = viewbinding.password
        var isOpenEye = false

        viewbinding.ivEye.setOnClickListener(
            object : View.OnClickListener{
                override fun onClick(v: View?) {
                    if(!isOpenEye) {
                        viewbinding.ivEye.setSelected(true);
                        isOpenEye = true;
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                        viewbinding.ivEye.setSelected(false);
                        isOpenEye = false;
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }

            }
        )

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            // disable login button unless both username / password is valid
            viewbinding.login.isEnabled = loginState.isDataValid
            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            viewbinding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser()
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra(LOGIN_DATA, loginResult.success)
                    setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) //next activity never back
                }
                startActivity(intent)
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                        username.text.toString(),
                        password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                                username.text.toString(),
                                password.text.toString()
                        )
                }
                false
            }

            viewbinding.login.setOnClickListener {
                viewbinding.loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun updateUiWithUser() {
        val login_success = getString(R.string.login_success)
        Toast.makeText(
                applicationContext,
                "$login_success ",
                Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}