package uz.frodo.movies

interface AdapterListner {
    fun addItem(film: Film)
    fun deleteItem(list: ArrayList<Film>,position: Int)
    fun editItem(film: Film,position:Int)
    fun infoItem(film: Film)

}