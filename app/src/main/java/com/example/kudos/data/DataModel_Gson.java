package com.example.kudos.data;

import android.os.Parcel;
import android.os.Parcelable;

import static java.lang.System.out;

public class DataModel_Gson implements Parcelable {

    private  int mData;

    protected DataModel_Gson(Parcel in) {
        mData=in.readInt();
    }



    public static final Creator<DataModel_Gson> CREATOR = new Creator<DataModel_Gson>() {
        @Override
        public DataModel_Gson createFromParcel(Parcel in) {
            return new DataModel_Gson(in);
        }

        @Override
        public DataModel_Gson[] newArray(int size) {
            return new DataModel_Gson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        out.write(mData);
    }
}
