package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by valter.franco on 10/12/2017.
 */

 public class Historic implements Parcelable{


    protected Historic(Parcel in) {
    }

    public static final Creator<Historic> CREATOR = new Creator<Historic>() {
        @Override
        public Historic createFromParcel(Parcel in) {
            return new Historic(in);
        }

        @Override
        public Historic[] newArray(int size) {
            return new Historic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
