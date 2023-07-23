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

class MyAdapter(var context:Context,var list: ArrayList<Film>):RecyclerView.Adapter<ViewHolder>() {
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
            val i = Intent(context,Info::class.java)
            i.putExtra("info",list[position])
            context.startActivity(i)
        }

        holder.itemView.findViewById<Button>(R.id.item_edit).setOnClickListener {
            val i = Intent(context,Edit::class.java)
            i.putExtra("edit",list[position])
            println("${list[position]}")
            i.putExtra("edit2",position)
            context.startActivity(i)
        }

        holder.itemView.findViewById<Button>(R.id.item_delete).setOnClickListener {
            MySharedPref.init(context)
            MySharedPref.removeList(list[position])
            list = MySharedPref.list
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,list.size)

        }
    }
    fun addItem(){
        MySharedPref.init(context)
        list = MySharedPref.list
        notifyItemInserted(list.size-1)
    }

    fun dataSet(){
        MySharedPref.init(context)
        list = MySharedPref.list
        notifyDataSetChanged()
    }
}