package uz.frodo.movies

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPref {
    lateinit var shPref:SharedPreferences
    fun init(context: Context){
        shPref = context.getSharedPreferences("DATA", MODE_PRIVATE)
    }
    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor)->Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var list:ArrayList<Film>
        get() {
            val json = shPref.getString("key",null)
            return if (json != null){
                val type=object : TypeToken<ArrayList<Film>>(){}.type
                Gson().fromJson(json,type)
            }else
                arrayListOf()
        }
        set(value) {
            val json = Gson().toJson(value)
            if (json != null){
                shPref.edit {
                    it.putString("key",json)
                }
            }
        }
    fun addList(film:Film){
        var updated = list
        updated.add(film)
        list = updated
    }
    fun removeList(film:Film){
        var updated = list
        updated.remove(film)
        list = updated
    }
    fun editList(position:Int,film:Film){
        var updated = list
        updated[position] = film
        println("$film")
        list = updated
    }
}