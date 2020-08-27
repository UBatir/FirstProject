package com.example.loansdebts.ui.dialog.changebalance

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.loansdebts.ui.main.MainActivity
import com.example.loansdebts.R
import com.example.loansdebts.data.NotebookDatabase
import com.example.loansdebts.data.dao.ContactDao
import com.example.loansdebts.data.model.Contact
import com.example.loansdebts.ui.dialog.CalculatorDialog
import com.example.loansdebts.ui.dialog.DataDialog
import com.example.loansdebts.ui.dialog.SetData
import kotlinx.android.synthetic.main.dialog_add_contact.*
import kotlinx.android.synthetic.main.dialog_change_balance.*
import kotlinx.android.synthetic.main.dialog_change_balance.etSumma
import kotlinx.android.synthetic.main.dialog_change_balance.tvSane
import java.util.*


class DialogChangeBalance(private val activity: MainActivity, private val id:Int):Dialog(activity),SetData{

    lateinit var dao: ContactDao
    lateinit var currentContact:Contact

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_change_balance)
        dao=NotebookDatabase.getInstance(activity).dao()
        currentContact=dao.getContactById(id)
        tvName.text=currentContact.name
        tvSumma.text=currentContact.summa.toString()
        val c= Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        tvSane.text="$day.${month+1}.$year"
        tvSane.setOnClickListener{
            val dialog= DataDialog(context,this)
            dialog.show()
        }
        if(currentContact.debt==1){
            tvMinusText.setTextColor(Color.rgb(76,175,80))
            tvPlusText.setTextColor(Color.rgb(76,175,80))
            tvMinusText.text = "+${currentContact.summa}"
            tvPlusText.text = "+${currentContact.summa}"
        }else if(currentContact.debt==-1){
            tvPlusText.setTextColor(Color.rgb(97,97,97))
            tvMinusText.setTextColor(Color.rgb(97,97,97))
            tvMinusText.text = currentContact.summa.toString()
            tvPlusText.text = currentContact.summa.toString()
        }
        else{
            tvMinusText.setTextColor(Color.rgb(229,57,53))
            tvPlusText.setTextColor(Color.rgb(229,57,53))
            tvMinusText.text = currentContact.summa.toString()
            tvPlusText.text = currentContact.summa.toString()
        }
        etSumma.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val a=if (etSumma.text.toString().trim() == "") 0 else etSumma.text.toString().toLong()
                    if(currentContact.debt==1){
                        tvPlusText.setTextColor(Color.rgb(76,175,80))
                        tvPlusText.text = "+${currentContact.summa+ a}"
                        if(currentContact.summa- a>0){
                            tvMinusText.setTextColor(Color.rgb(76,175,80))
                            tvMinusText.text = "+${currentContact.summa- a}"
                        }else if(currentContact.summa- a<0){
                            tvMinusText.setTextColor(Color.rgb(229,57,53))
                            tvMinusText.text = "${currentContact.summa- a}"
                        }else{
                            tvMinusText.setTextColor(Color.rgb(97,97,97))
                            tvMinusText.text = "${currentContact.summa- a}"
                        }
                    } else{
                        tvMinusText.setTextColor(Color.rgb(229,57,53))
                        tvMinusText.text = "${currentContact.summa- a}"
                        if(currentContact.summa+ a>0){
                            tvPlusText.setTextColor(Color.rgb(76,175,80))
                            tvPlusText.text = "+${currentContact.summa+ a}"
                        }else if(currentContact.summa+ a<0){
                            tvPlusText.setTextColor(Color.rgb(229,57,53))
                            tvPlusText.text = "${currentContact.summa+ a}"
                        }else{
                            tvPlusText.setTextColor(Color.rgb(97,97,97))
                            tvPlusText.text = "${currentContact.summa+ a}"
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
            if (etSumma.text.toString().trim() == ""||etSumma.text.toString().toInt()==0) {
                Toast.makeText(context,"Summa kirgizilmegen yamasa nolge ten. Nolge ten emes san kirgizin!",
                    Toast.LENGTH_SHORT).show()
            } else{
                val a=etSumma.text.toString().toInt()
                if(currentContact.debt==1){
                    currentContact.debt=1
                    tvPlusText.text = "${currentContact.summa+ a}"
                } else{
                    if(currentContact.summa+ a>0){
                    currentContact.debt=1
                    tvPlusText.text = "${currentContact.summa+ a}"
                    }else if(currentContact.summa+ a<0){
                    currentContact.debt=0
                    tvPlusText.text = "${currentContact.summa+ a}"
                    } else if(currentContact.summa.toInt()+ a==0){
                    currentContact.debt=-1
                    tvPlusText.text = "${currentContact.summa+ a}"
                    }
                }

                currentContact.summa=tvPlusText.text.toString().toLong()
                activity.rewriteContact(currentContact)
                dismiss()
            }
        }
        btnMinus.setOnClickListener{
            if (etSumma.text.toString().trim() == ""||etSumma.text.toString().toInt()==0) {
                Toast.makeText(context,"Summa kirgizilmegen yamasa nolge ten. Nolge ten emes san kirgizin!",Toast.LENGTH_SHORT).show()
            }
            else{
                val a=etSumma.text.toString().toInt()
                if(currentContact.debt==0) {
                    if(currentContact.summa+ a>0){
                        currentContact.debt=0
                        tvMinusText.text = "${currentContact.summa- a}"
                    }else if(currentContact.summa+ a<0){
                        currentContact.debt=0
                        tvMinusText.text = "${currentContact.summa- a}"
                    }else{
                        tvMinusText.setTextColor(Color.rgb(97,97,97))
                        tvMinusText.text = "${currentContact.summa+ a}"
                    }
                }else{
                    if(currentContact.summa- a>0){
                        currentContact.debt=1
                        tvMinusText.text = "${currentContact.summa - a}"
                    }else if(currentContact.summa- a<0){
                        currentContact.debt=0
                        tvMinusText.text = "${currentContact.summa - a}"
                    }else{
                        currentContact.debt=-1
                        tvMinusText.text = "${currentContact.summa - a}"
                    }
                }
                currentContact.summa=tvMinusText.text.toString().toLong()
                activity.rewriteContact(currentContact)
                dismiss()
            }
        }

        calculator1.setOnClickListener {
            val dialog= CalculatorDialog(context,this)
            dialog.show()
        }

    }

    override fun setData(data: String) {
        tvSane.text=data
    }

    override fun setSum(sum: Long) {
        etSumma.setText("$sum")
    }

}