package com.example.loansdebts.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import com.example.loansdebts.R
import com.example.loansdebts.data.NotebookDatabase
import com.example.loansdebts.data.dao.ContactDao
import com.example.loansdebts.data.model.Contact
import com.example.loansdebts.ui.main.MainActivity
import kotlinx.android.synthetic.main.dialog_sort.*

class DialogSort(context: Context,private val activity: MainActivity):Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_sort)

        tvSortName.setOnClickListener {
            activity.sortByName()
            dismiss()
        }
        tvSortSum.setOnClickListener {
            activity.sortBySum()
            dismiss()
        }
        tvSortDate.setOnClickListener {
            activity.sortByDate()
            dismiss()
        }
    }
}