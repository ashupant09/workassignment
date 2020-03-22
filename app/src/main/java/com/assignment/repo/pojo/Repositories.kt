package com.assignment.repo.pojo

import android.os.Parcel
import android.os.Parcelable

data class Repositories(
    var author:String?,
    var name:String?,
    var avatar: String?,
    var url: String?,
    var description: String?,
    var language: String?,
    var languageColor: String,
    var stars: String?,
    var forks: String?,
    var currentPeriodStars: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeString(url)
        parcel.writeString(description)
        parcel.writeString(language)
        parcel.writeString(languageColor)
        parcel.writeString(stars)
        parcel.writeString(forks)
        parcel.writeString(currentPeriodStars)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repositories> {
        override fun createFromParcel(parcel: Parcel): Repositories {
            return Repositories(parcel)
        }

        override fun newArray(size: Int): Array<Repositories?> {
            return arrayOfNulls(size)
        }
    }
}
