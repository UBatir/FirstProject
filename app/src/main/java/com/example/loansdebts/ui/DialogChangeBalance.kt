package com.example.loansdebts.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.loansdebts.MainActivity
import com.example.loansdebts.R
import com.example.loansdebts.data.NotebookDatabase
import com.example.loansdebts.data.dao.ContactDao
import com.example.loansdebts.data.model.Contact
import kotlinx.android.synthetic.main.dialog_change_balance.*
import kotlinx.android.synthetic.main.item_contact.view.*
import java.util.*


class DialogChangeBalance(private val activity: MainActivity,private val id:Int):Dialog(activity) {

    lateinit var dao: ContactDao
    lateinit var currentContact:Contact

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_change_balance)
        dao=NotebookDatabase.getInstance(activity).dao()
        currentContact=dao.getContactById(id)
        tvName.text=currentContact.name
        tvSumma.text=currentContact.summa
        val c= Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        tvSane.text="$day.${month+1}.$year"
        tvSane.setOnClickListener{
            val sane= DatePickerDialog(activity,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                tvSane.text = "$dayOfMonth.${month+1}.$year"
            }, year,month,day)
            sane.show()
        }
        if(currentContact.debt==1){
            tvMinusText.setTextColor(Color.rgb(76,175,80))
            tvPlusText.setTextColor(Color.rgb(76,175,80))
            tvMinusText.text = "+${currentContact.summa}"
            tvPlusText.text = "+${currentContact.summa}"
        }else if(currentContact.debt==-1){
            tvPlusText.setTextColor(Color.rgb(97,97,97))
            tvMinusText.setTextColor(Color.rgb(97,97,97))
            tvMinusText.text = currentContact.summa
            tvPlusText.text = currentContact.summa
        }
        else{
            tvMinusText.setTextColor(Color.rgb(229,57,53))
            tvPlusText.setTextColor(Color.rgb(229,57,53))
            tvMinusText.text = currentContact.summa
            tvPlusText.text = currentContact.summa
        }
        etSumma.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val a = if (etSumma.text.toString().trim() == "") 0 else etSumma.text.toString().toInt()
                if(currentContact.debt==1){
                    tvPlusText.setTextColor(Color.rgb(76,175,80))
                    tvPlusText.text = "+${currentContact.summa.toInt()+ a}"
                    if(currentContact.summa.toInt()- a>0){
                        tvMinusText.setTextColor(Color.rgb(76,175,80))
                        tvMinusText.text = "+${currentContact.summa.toInt()- a}"
                    }else if(currentContact.summa.toInt()- a<0){
                        tvMinusText.setTextColor(Color.rgb(229,57,53))
                        tvMinusText.text = "${currentContact.summa.toInt()- a}"
                    }else{
                        tvMinusText.setTextColor(Color.rgb(97,97,97))
                        tvMinusText.text = "${currentContact.summa.toInt()- a}"
                    }
                } else{
                    tvMinusText.setTextColor(Color.rgb(229,57,53))
                    tvMinusText.text = "${currentContact.summa.toInt()- a}"
                    if(currentContact.summa.toInt()+ a>0){
                        tvPlusText.setTextColor(Color.rgb(76,175,80))
                        tvPlusText.text = "+${currentContact.summa.toInt()+ a}"
                    }else if(currentContact.summa.toInt()+ a<0){
                        tvPlusText.setTextColor(Color.rgb(229,57,53))
                        tvPlusText.text = "${currentContact.summa.toInt()+ a}"
                    }else{
                        tvPlusText.setTextColor(Color.rgb(97,97,97))
                        tvPlusText.text = "${currentContact.summa.toInt()+ a}"
                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })
        btnPlus.setOnClickListener{
            val a = if (etSumma.text.toString().trim() == "") 0 else etSumma.text.toString().toInt()
            if(currentContact.debt==1){
            currentContact.debt=1
            tvPlusText.text = "${currentContact.summa.toInt()+ a}"
            } else{
                if(currentContact.summa.toInt()+ a>0){
                currentContact.debt=1
                tvPlusText.text = "${currentContact.summa.toInt()+ a}"
                }else if(currentContact.summa.toInt()+ a<0){
                currentContact.debt=0
                tvPlusText.text = "${currentContact.summa.toInt()+ a}"
                }else if(currentContact.summa.toInt()+ a==0){
                currentContact.debt=-1
                tvPlusText.text = "${currentContact.summa.toInt()+ a}"
                }
            }
            currentContact.summa=tvPlusText.text.toString()
            activity.rewriteContact(currentContact)
            dismiss()
        }
        btnMinus.setOnClickListener{
            val a = if (etSumma.text.toString().trim() == "") 0 else etSumma.text.toString().toInt()
            if(currentContact.debt==0) {
                if(currentContact.summa.toInt()+ a>0){
                    currentContact.debt=0
                    tvMinusText.text = "${currentContact.summa.toInt()- a}"
                }else if(currentContact.summa.toInt()+ a<0){
                    currentContact.debt=0
                    tvMinusText.text = "${currentContact.summa.toInt()- a}"
                }else{
                    tvMinusText.setTextColor(Color.rgb(97,97,97))
                    tvMinusText.text = "${currentContact.summa.toInt()+ a}"
                }
            }else{
                if(currentContact.summa.toInt()- a>0){
                    currentContact.debt=1
                    tvMinusText.text = "${currentContact.summa.toInt() - a}"
                }else if(currentContact.summa.toInt()- a<0){
                    currentContact.debt=0
                    tvMinusText.text = "${currentContact.summa.toInt() - a}"
                }else{
                    currentContact.debt=-1
                    tvMinusText.text = "${currentContact.summa.toInt() - a}"
                }
            }
            currentContact.summa=tvMinusText.text.toString()
            activity.rewriteContact(currentContact)
            dismiss()
        }
    }
}