package com.egco428.ex_sqllogin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class UserAdapter(val mContext: Context, val layoutResId: Int, val showallusersList: ArrayList<User>):
    ArrayAdapter<User>(mContext, layoutResId, showallusersList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var dataSource: UsersDataSource? = null

        dataSource = UsersDataSource(mContext)
        dataSource!!.open()
        val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflator.inflate(layoutResId, null)
        val detailpage_userID = view.findViewById<TextView>(R.id.detail_userID)
        val detailpage_userName = view.findViewById<TextView>(R.id.detail_username)
        val msg = showallusersList[position]


        detailpage_userID.text = "User Id is:  ${msg.id}"
        detailpage_userName.text = "User Name is:   ${msg.username}"

        view.setOnLongClickListener {
            dataSource.deleteComment(msg.username)
            view.animate().setDuration(500).alpha(0f).withEndAction {
                showallusersList.removeAt(position)
                notifyDataSetChanged()
                view.alpha = 1.0F
            }
            true
        }
        return view
    }
}