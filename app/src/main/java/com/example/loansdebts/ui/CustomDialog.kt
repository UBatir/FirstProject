package com.example.loansdebts.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.loansdebts.MainActivity
import com.example.loansdebts.R
import com.example.loansdebts.data.model.Contact
import kotlinx.android.synthetic.main.dialog_add_contact.*
import java.util.*

class CustomDialog(context: Context, private val activity: MainActivity):Dialog(context)    {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_contact)
        val c=Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        tvSane.text="$day.${month+1}.$year"
        btnPayda.setOnClickListener {
            if(etName.text.toString() == ""){
                Toast.makeText(context,"Ati kirgizilmegen, qosatin esset joq!",Toast.LENGTH_SHORT).show()
                } else if(etSumma.text.toString()==""){
                    Toast.makeText(context,"Summa kirgizilmegen yamasa nolge ten. Nolge ten emes san kirgizin!",Toast.LENGTH_SHORT).show()
                } else{
            activity.addContact(Contact(etName.text.toString(),etKommentariy.text.toString(),"+${etSumma.text.toString()}","$day.${month+1}.$year",1))
            dismiss()
            }
        }
        btnQariz.setOnClickListener{
            if(etName.text.toString() == ""){
                Toast.makeText(context,"Ati kirgizilmegen, qosatin esset joq!",Toast.LENGTH_SHORT).show()
            } else if(etSumma.text.toString()==""){
                Toast.makeText(context,"Summa kirgizilmegen yamasa nolge ten. Nolge ten emes san kirgizin!",Toast.LENGTH_SHORT).show()
            } else{
                activity.addContact(Contact(etName.text.toString(),etKommentariy.text.toString(),"-${etSumma.text.toString()}","$day.${month+1}.$year",0))
                dismiss()
            }
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