package uz.frodo.movies
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.frodo.movies.databinding.ActivityAddBinding
import uz.frodo.movies.MainActivity
import uz.frodo.movies.MySharedPref.list

class Add : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPref.init(this)


        binding.saveButton.setOnClickListener {
            val name = binding.nameEdit.text.toString()
            val authors = binding.authorEdit.text.toString()
            val about = binding.aboutEdit.text.toString()
            val date = binding.dateEdit.text.toString()
            if (name.isNotBlank() && authors.isNotBlank() && about.isNotBlank() && date.isNotBlank()){
                val film = Film(name,authors,about,date)
                MySharedPref.addList(film)

                setResult(RESULT_OK)
                finish()
            }else
                Toast.makeText(this, "Fill the blank", Toast.LENGTH_SHORT).show()
        }
    }
}