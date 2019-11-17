package com.egco428.ex_sqllogin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class MySQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(MySQLiteHelper::class.java!!.name,
            "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data")
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        onCreate(db)
    }

    companion object {

        val TABLE_USERS = "users"
        val COLUMN_ID = "_id"
        val COLUMN_USERNAME = "username"
        val COLUMN_PASSWORD = "password"

        private val DATABASE_NAME = "users.db"
        private val DATABASE_VERSION = 1

        // Database creation sql statement
        private val DATABASE_CREATE = ("create table "
                + TABLE_USERS + "(" + COLUMN_ID
                + " VARCHAR(5) primary key , " + COLUMN_USERNAME
                + " VARCHAR(5) not null , " + COLUMN_PASSWORD
                + " VARCHAR(5) not null);")
    }

}