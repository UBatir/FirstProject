package com.example.loansdebts.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.loansdebts.MainActivity
import com.example.loansdebts.R
import kotlinx.android.synthetic.main.dialog_qosiw.*
import kotlinx.android.synthetic.main.fragment.*
import kotlinx.android.synthetic.main.item_contact.*
import java.text.FieldPosition
import java.util.*

class CustomDialog(context: Context,val activity: MainActivity):Dialog(context)    {

    private val adapter=ListAdapter()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_qosiw)
        btnPayda.setOnClickListener {
            adapter.addUser(etSumma.text.toString(),etKommentariy.text.toString(),etSumma.text.toString())
            dismiss()
        }
        btnBiykarlaw.setOnClickListener {
            dismiss()
        }
        tvSane.setOnClickListener{
            val c=Calendar.getInstance()
            val year=c.get(Calendar.YEAR)
            val month=c.get(Calendar.MONTH)
            val day=c.get(Calendar.DAY_OF_MONTH)
            val sane=DatePickerDialog(activity,DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                tvSane.text = "$dayOfMonth.${month+1}.$year"
            }, year,month,day)

            sane.show()
        }
    }
}