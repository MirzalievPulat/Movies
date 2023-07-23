package uz.frodo.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.frodo.movies.databinding.ActivityInfoBinding

class Info : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent.getParcelableExtra<Film>("info")
        i!!
        binding.infoToolbar.title = i.name
        binding.infoName.text = i.name
        binding.infoAuthors.text = i.author
        binding.infoAbout.text = i.review
        binding.infoDate.text = i.date

        binding.closeButton.setOnClickListener {
            finish()
        }
    }
}