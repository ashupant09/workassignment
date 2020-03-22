package com.assignment.repo.pojo

import android.os.Parcel
import android.os.Parcelable
import com.assignment.repo.network.AppResponse

data class RepoList(var repoData: List<Repositories>?): AppResponse,Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Repositories)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(repoData)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoList> {
        override fun createFromParcel(parcel: Parcel): RepoList {
            return RepoList(parcel)
        }

        override fun newArray(size: Int): Array<RepoList?> {
            return arrayOfNulls(size)
        }
    }
}