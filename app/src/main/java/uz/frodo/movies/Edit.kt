package uz.frodo.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.frodo.movies.databinding.ActivityEditBinding
import uz.frodo.movies.MyAdapter

class Edit : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent.getParcelableExtra<Film>("edit")
        println("$i")
        val i2 = intent.getIntExtra("edit2", -1)
        i?.let {
            binding.nameEdit.setText(i.name)
            binding.authorEdit.setText(i.author)
            binding.aboutEdit.setText(i.review)
            binding.dateEdit.setText(i.date)
        }


        binding.editButton.setOnClickListener {
            val name = binding.nameEdit.text.toString()
            val authors = binding.authorEdit.text.toString()
            val about = binding.aboutEdit.text.toString()
            val date = binding.dateEdit.text.toString()
            println("mana: $name,$authors...")
            if (name.isNotBlank() && authors.isNotBlank() && about.isNotBlank() && date.isNotBlank()) {
                val film = Film(name, authors, about, date)
                MySharedPref.editList(i2, film)
                finish()
            } else
                Toast.makeText(this, "Fill the blank", Toast.LENGTH_SHORT).show()
        }
    }
}