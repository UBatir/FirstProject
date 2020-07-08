package com.example.loansdebts.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.loansdebts.MainActivity
import com.example.loansdebts.R
import com.example.loansdebts.data.NotebookDatabase
import com.example.loansdebts.data.dao.ContactDao
import com.example.loansdebts.data.model.Contact
import kotlinx.android.synthetic.main.dialog_rename.*

class DialogRename(private val id: Int,private val activity:MainActivity): Dialog(activity) {

    lateinit var dao:ContactDao
    lateinit var currentContact: Contact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_rename)
        dao=NotebookDatabase.getInstance(activity).dao()
        currentContact=dao.getContactById(id)
        etRename.setText(currentContact.name)
        btnAtinOzgertiw.setOnClickListener {
            currentContact.name=etRename.text.toString()
            activity.renameContact(currentContact)
            dismiss()
        }
        btnBiykarlaw.setOnClickListener {
            dismiss()
        }
    }
}