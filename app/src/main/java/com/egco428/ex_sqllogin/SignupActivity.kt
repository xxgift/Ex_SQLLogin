package com.egco428.ex_sqllogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.signup.*

class SignupActivity : AppCompatActivity() {
    private var dataSource:UsersDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        dataSource = UsersDataSource(this)
        dataSource!!.open()
        var values = dataSource!!.allComments

        signup_signupBtn.setOnClickListener(){
            values = dataSource!!.allComments
            val username = signup_username.text.toString()
            val password = signup_password.text.toString()
            var id = 0
            if(password.length >= 8){
                if((values.size)!= 0){
                    id = (values[values.size-1].id).toInt() + 1
                }
                println("id $id")
                val newUser = dataSource!!.createUser(id.toString(),username,password)
                if(newUser == null){
                    Toast.makeText(this,"Username is invalid",Toast.LENGTH_SHORT).show()
                }else{
                    signup_password.text = null
                    signup_username.text = null
                    Toast.makeText(this,"Add new user success!!!!",Toast.LENGTH_SHORT).show()
                }
                println("======================"+newUser?.username+"====================")
            }else{
                Toast.makeText(this,"Password is invalid",Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        if (id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
