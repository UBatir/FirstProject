package com.example.loansdebts.ui.dialog.changebalance

import com.example.loansdebts.data.dao.ContactDao

class ChangeBalancePresenter(private val dao:ContactDao,private val view:DialogChangeBalance) {

    fun getContactById(id:Int){
        view.dao.getContactById(id)
    }
}