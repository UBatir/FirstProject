package com.example.loansdebts.data.dao

import androidx.room.*
import com.example.loansdebts.data.model.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact:Contact)

    @Query("SELECT * FROM notebook")
    fun getAllContact():List<Contact>

    @Delete
    fun deleteContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Query("SELECT * FROM notebook where id=:id")
    fun getContactById(id:Int):Contact
//
//    @Query("SELECT SUM(summa) FROM notebook where debt=1")
//    fun getCountSum():List<Contact>
}