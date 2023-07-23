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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPref.init(this)

        adapter = MyAdapter(this,MySharedPref.list)
        binding.rv.adapter = adapter
        binding.plus.setOnClickListener {
            val i = Intent(this,Add::class.java)
            startActivityForResult(i,1)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1){
            adapter.addItem()
        }

    }

    override fun onRestart() {
        super.onRestart()
        adapter.dataSet()
    }


}