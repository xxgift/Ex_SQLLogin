package com.egco428.ex_sqllogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.signin.*
import kotlinx.android.synthetic.main.signup.*

class SigninActivity : AppCompatActivity() {
    private var dataSource: UsersDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        dataSource = UsersDataSource(this)
        dataSource!!.open()

        signin_signinBtn.setOnClickListener() {
            val username = signin_username.text.toString()
            val password = signin_password.text.toString()
            val user = dataSource!!.login(username, password)
            if (user == null) {
                Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
            } else {
                var intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
