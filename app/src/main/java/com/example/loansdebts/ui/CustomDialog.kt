package com.example.loansdebts.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import com.example.loansdebts.MainActivity
import com.example.loansdebts.R
import com.example.loansdebts.data.model.Contact
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_qosiw.*
import kotlinx.android.synthetic.main.fragment.*
import kotlinx.android.synthetic.main.fragment.recyclerView
import kotlinx.android.synthetic.main.item_contact.*
import java.text.FieldPosition
import java.util.*

class CustomDialog(context: Context, private val activity: MainActivity):Dialog(context)    {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_qosiw)
        val c=Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        tvSane.text="$day.${month+1}.$year"
        btnPayda.setOnClickListener {
            activity.addContact(Contact(etName.text.toString(),etKommentariy.text.toString(),"+${etSumma.text.toString()}","$day.${month+1}.$year"))
            dismiss()
        }
        btnQariz.setOnClickListener{
            activity.addContact(Contact(etName.text.toString(),etKommentariy.text.toString(),"-${etSumma.text.toString()}","$day.${month+1}.$year"))
            dismiss()
        }
        btnBiykarlaw.setOnClickListener {
            dismiss()
        }
        tvSane.setOnClickListener{
            val sane=DatePickerDialog(activity,DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                tvSane.text = "$dayOfMonth.${month+1}.$year"
            }, year,month,day)

            sane.show()
        }
    }
}