package com.example.loansdebts.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.SimpleAdapter
import android.widget.Toast
import com.example.loansdebts.MainActivity
import com.example.loansdebts.R
import com.example.loansdebts.data.model.Contact
import kotlinx.android.synthetic.main.dialog_add_contact.*
import kotlinx.android.synthetic.main.dialog_add_contact.etKommentariy
import kotlinx.android.synthetic.main.dialog_add_contact.etSumma
import kotlinx.android.synthetic.main.dialog_add_contact.tvSane
import kotlinx.android.synthetic.main.dialog_change_balance.*
import java.util.*

class CustomDialog(context: Context, private val activity: MainActivity):Dialog(context)    {

    private lateinit var mPeopleList:ArrayList<Map<String, String>>
    private lateinit var mAdapter: SimpleAdapter
    private lateinit var mTxtPhoneNo: AutoCompleteTextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_contact)
        mPeopleList = ArrayList()
        populatePeopleList()
        mTxtPhoneNo=findViewById(R.id.actvName)
        mAdapter= SimpleAdapter(context,mPeopleList,R.layout.auto_complete_tv, arrayOf("Name"),
            intArrayOf(R.id.tvNameContact))
        mTxtPhoneNo.setAdapter(mAdapter)
        actvName.setSelection(actvName.text!!.length)


        mTxtPhoneNo.onItemClickListener =
            AdapterView.OnItemClickListener { av, _, index, _ ->
                val map = av.getItemAtPosition(index) as Map<*, *>
                val name = map["Name"]
                mTxtPhoneNo.setText("$name")
                mTxtPhoneNo.setSelection(mTxtPhoneNo.text!!.length)
            }

        val c=Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        tvSane.text="$day.${month+1}.$year"
        btnPayda.setOnClickListener {
            if(actvName.text.toString() == ""){
                Toast.makeText(context,"Ati kirgizilmegen, qosatin esset joq!",Toast.LENGTH_SHORT).show()
                } else if(etSumma.text.toString()==""||etSumma.text.toString().toInt()==0){
                    Toast.makeText(context,"Summa kirgizilmegen yamasa nolge ten. Nolge ten emes san kirgizin!",Toast.LENGTH_SHORT).show()
                } else{
            activity.addContact(Contact(actvName.text.toString(),etKommentariy.text.toString(),etSumma.text.toString(),"$day.${month+1}.$year",1))
            dismiss()
            }
        }
        btnQariz.setOnClickListener{
            if(actvName.text.toString() == ""){
                Toast.makeText(context,"Ati kirgizilmegen, qosatin esset joq!",Toast.LENGTH_SHORT).show()
            } else if(etSumma.text.toString()==""||etSumma.text.toString().toInt()==0){
                Toast.makeText(context,"Summa kirgizilmegen yamasa nolge ten. Nolge ten emes san kirgizin!",Toast.LENGTH_SHORT).show()
            } else{
                activity.addContact(Contact(actvName.text.toString(),etKommentariy.text.toString(),"-${etSumma.text.toString()}","$day.${month+1}.$year",0))
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


    private fun populatePeopleList() {
        mPeopleList.clear()
        val people = activity.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (people != null) {
            while (people.moveToNext())
            {
                val contactName = people.getString(people
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                if (contactName!=null)
                {
                    val namePhoneType = HashMap<String, String>()
                    namePhoneType["Name"] = contactName
                    mPeopleList.add(namePhoneType)
                }
            }
        }
        people?.close()
        activity.startManagingCursor(people)
    }
}