package uz.frodo.movies

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import uz.frodo.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:MyAdapter
    lateinit var list:ArrayList<Film>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPref.init(this)

        list = MySharedPref.list
        adapter = MyAdapter(list, object :MyAdapter.OnMyItemClickListener{
            override fun editItem(position: Int) {
                val i = Intent(this@MainActivity,Edit::class.java)
                i.putExtra("edit",list[position])
                i.putExtra("edit2",position)
                startActivityForResult(i,2)
            }

            override fun deleteItem(position: Int) {
                MySharedPref.removeList(list[position])
                list.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position,list.size)

            }

            override fun infoItem(film: Film) {
                val i = Intent(this@MainActivity,Info::class.java)
                i.putExtra("info",film)
                startActivity(i)
            }

        })
        binding.rv.adapter = adapter
        binding.plus.setOnClickListener {
            val i = Intent(this,Add::class.java)
            startActivityForResult(i,1)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1 && data != null){
            val film = data.getParcelableExtra<Film>("add")
            list.add(film!!)
            adapter.notifyItemInserted(list.size-1)
        }
        if (resultCode == RESULT_OK && requestCode == 2 && data != null){
            val film = data.getParcelableExtra<Film>("add")
            val position = data.getIntExtra("add2",-1)
            if (position != -1){
                list[position] = film!!
                adapter.notifyItemChanged(position)
            }

        }

    }


}