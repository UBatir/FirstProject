package com.example.loansdebts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loansdebts.MainActivity
import com.example.loansdebts.R
import kotlinx.android.synthetic.main.item_contact.view.*

class ListAdapter(private val activity: MainActivity):RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun populateModel(model:AddContact,activity: MainActivity){
            itemView.tvName.text=model.name
            itemView.tvSumma.text=model.summa
            itemView.tvKommentariy.text=model.comment
            itemView.btnOptions.setOnClickListener{
                activity.onOptionsButtonClick(itemView.btnOptions)
            }
        }
    }

    var models: List<AddContact> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount()=models.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.populateModel(models[position],activity)
    }
}