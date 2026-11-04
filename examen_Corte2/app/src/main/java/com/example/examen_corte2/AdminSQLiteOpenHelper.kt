package com.example.examen_corte2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table respuestas(" +
                    "id INTEGER primary key AUTOINCREMENT, " +
                    "primera int, " +
                    "segunda int, " +
                    "tercera int, " +
                    "cuarta int, " +
                    "quinta int)"
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

    }
}