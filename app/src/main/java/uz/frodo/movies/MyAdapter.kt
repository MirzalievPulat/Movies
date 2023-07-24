package uz.frodo.movies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyAdapter(var list: ArrayList<Film>,var onMyItemClickListener: OnMyItemClickListener):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return object :ViewHolder(view){}
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.item_name).text = list[position].name
        holder.itemView.findViewById<TextView>(R.id.item_author).text = list[position].author
        holder.itemView.findViewById<TextView>(R.id.item_date).text = list[position].date

        holder.itemView.setOnClickListener {
            onMyItemClickListener.infoItem(list[position])
        }

        holder.itemView.findViewById<Button>(R.id.item_edit).setOnClickListener {
            onMyItemClickListener.editItem(position)
        }

        holder.itemView.findViewById<Button>(R.id.item_delete).setOnClickListener {
            onMyItemClickListener.deleteItem(position)
        }
    }
    interface OnMyItemClickListener{
        fun editItem(position: Int)
        fun deleteItem( position: Int)
        fun infoItem(film: Film)
    }
}