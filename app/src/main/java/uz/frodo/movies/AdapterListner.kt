package uz.frodo.movies

interface AdapterListner {
    fun addItem(film: Film)
    fun deleteItem(film: Film)
    fun editItem(film: Film)

}