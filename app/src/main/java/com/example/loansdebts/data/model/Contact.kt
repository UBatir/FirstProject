package com.example.loansdebts.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notebook")
data class Contact(
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="comment") val comment:String?,
    @ColumnInfo(name="summa") val summa:String,
    @ColumnInfo(name="date") val date:String,
    @ColumnInfo(name="debt") val debt:Int?=0,
    @PrimaryKey(autoGenerate = true) val id:Int=0
)