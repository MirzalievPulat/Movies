package uz.frodo.movies

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class Film(val name:String?,val author:String?,val review:String?,val date: String? ) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(author)
        parcel.writeString(review)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}
