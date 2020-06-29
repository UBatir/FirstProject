package com.example.loansdebts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loansdebts.R
import kotlinx.android.synthetic.main.item_contact.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun populateModel(model:Contact){
            itemView.tvName.text=model.name
            itemView.tvSumma.text=model.summa
            itemView.tvKommentariy.text=model.comment
        }
    }

    var models: MutableList<Contact> = mutableListOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    fun addUser(name:String,comment:String,summa:String){
        models.add(Contact(name, summa, comment))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount()=models.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}