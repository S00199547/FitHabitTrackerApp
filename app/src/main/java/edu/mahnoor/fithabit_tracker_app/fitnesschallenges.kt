package edu.mahnoor.fithabit_tracker_app
import android.os.Parcel
import android.os.Parcelable

data class fitnesschallenges(val name: String, val duration: Int, var steps: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(duration)
        parcel.writeInt(steps)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<fitnesschallenges> {
        override fun createFromParcel(parcel: Parcel): fitnesschallenges {
            return fitnesschallenges(parcel)
        }

        override fun newArray(size: Int): Array<fitnesschallenges?> {
            return arrayOfNulls(size)
        }
    }
}