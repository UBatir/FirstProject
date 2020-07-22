package com.example.loansdebts.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import com.example.loansdebts.R
import com.example.loansdebts.data.NotebookDatabase
import com.example.loansdebts.data.dao.ContactDao
import com.example.loansdebts.ui.main.MainActivity
import kotlinx.android.synthetic.main.dialog_sort.*

class DialogSort(context: Context,private val activity: MainActivity):Dialog(context) {

    private lateinit var dao:ContactDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_sort)
        dao=NotebookDatabase.getInstance(activity).dao()

        tvSortName.setOnClickListener {
            dismiss()
        }
        tvSortSum.setOnClickListener {
            tvSortSum.setTypeface(tvSortName.typeface, Typeface.BOLD)
            dao.sortBySum()
        }
        tvSortDate.setOnClickListener {
            tvSortDate.setTypeface(tvSortName.typeface, Typeface.BOLD)
            dao.sortByDate()
        }
    }
}