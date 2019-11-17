package com.egco428.ex_sqllogin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase

class UsersDataSource(context: Context) {
    // Database fields
    private var database: SQLiteDatabase? = null
    private val dbHelper: MySQLiteHelper
    private val allColumns = arrayOf<String>(MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_USERNAME,MySQLiteHelper.COLUMN_PASSWORD)

    // make sure to close the cursor
    val allComments: ArrayList<User>
        get() {
            val users = ArrayList<User>()

            val cursor = database!!.query(MySQLiteHelper.TABLE_USERS,
                allColumns, null, null, null, null, null)

            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val user = cursorToUser(cursor)
                users.add(user)
                cursor.moveToNext()
            }
            cursor.close()
            return users
        }

    init {
        dbHelper = MySQLiteHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = dbHelper.getWritableDatabase()
    }

    fun close() {
        dbHelper.close()
    }

    fun createUser(id:String,username: String,password:String): User? {

        val checkUser = database!!.query(MySQLiteHelper.TABLE_USERS,
            allColumns, MySQLiteHelper.COLUMN_USERNAME + " = \'$username\'" , null, null, null, null)
        checkUser.moveToFirst()
        if(checkUser.count!=0){
            return null
        }else{
            val user = ContentValues()
            user.put(MySQLiteHelper.COLUMN_ID, id)
            user.put(MySQLiteHelper.COLUMN_USERNAME,username)
            user.put(MySQLiteHelper.COLUMN_PASSWORD,password)

            val insertId = database!!.insert(MySQLiteHelper.TABLE_USERS, null,
                user)

            val cursor = database!!.query(MySQLiteHelper.TABLE_USERS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = \'$id\'" , null, null, null, null)
            cursor.moveToFirst()

            val newUser = cursorToUser(cursor)

            cursor.close()
            return newUser
        }
    }

    fun deleteComment(username: String) {

        database!!.delete(MySQLiteHelper.TABLE_USERS, MySQLiteHelper.COLUMN_USERNAME
                + " = \'$username\'", null)
    }

    private fun cursorToUser(cursor: Cursor): User {
        val user = User()
        user.id = cursor.getString(0)
        user.username = cursor.getString(1)
        user.password = cursor.getString(2)
        return user
    }

    fun login(username: String, password: String):User?{
        val checkUser = database!!.query(MySQLiteHelper.TABLE_USERS,
            allColumns, MySQLiteHelper.COLUMN_USERNAME + " = \'$username\'" , null, null, null, null)
        checkUser.moveToFirst()
        if(checkUser.count == 0){
            return  null
        }else {
            val usered = cursorToUser(checkUser)
            if (usered.password != password){
                return  null
            }else{
                return usered
            }
        }
        checkUser.close()

    }
}