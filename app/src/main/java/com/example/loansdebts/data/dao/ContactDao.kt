package com.example.loansdebts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.loansdebts.data.model.Contact
import com.example.loansdebts.ui.AddContact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact:Contact)

    @Query("SELECT * FROM notebook")
    fun getAllContact():List<AddContact>
}