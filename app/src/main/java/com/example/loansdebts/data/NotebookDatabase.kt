package com.example.loansdebts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loansdebts.data.dao.ContactDao
import com.example.loansdebts.data.model.Contact

@Database(entities = [Contact::class],version = 1)
abstract class NotebookDatabase:RoomDatabase() {
    companion object {
        private lateinit var INSTANCE:NotebookDatabase

        fun getInstance(context: Context): NotebookDatabase {
            if(!::INSTANCE.isInitialized){
                    INSTANCE= Room.databaseBuilder(
                    context,
                    NotebookDatabase::class.java,
                "notebook-database.db")
                .allowMainThreadQueries()
                .build()
            }
                return INSTANCE
        }
    }
    abstract fun dao():ContactDao
}