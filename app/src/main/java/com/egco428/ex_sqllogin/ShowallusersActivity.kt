package com.egco428.ex_sqllogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.showallusers.*

class ShowallusersActivity : AppCompatActivity() {
    private var dataSource: UsersDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.showallusers)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        dataSource = UsersDataSource(this)
        dataSource!!.open()

        val adapter = UserAdapter(applicationContext,R.layout.userlist_detail,dataSource!!.allComments)
        showallusersList.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        if (id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
