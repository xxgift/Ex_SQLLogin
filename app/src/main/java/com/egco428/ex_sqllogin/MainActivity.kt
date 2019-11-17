package com.egco428.ex_sqllogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_signupBtn.setOnClickListener(){
            var intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        main_signinBtn.setOnClickListener(){
            var intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
        main_showallusersBtn.setOnClickListener(){
            var intent = Intent(this,ShowallusersActivity::class.java)
            startActivity(intent)
        }
    }
}
