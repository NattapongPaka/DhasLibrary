package app.dhaslibrary.util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dev on 14/12/2559.
 */

public class RxMessage implements Parcelable {

    private int code;
    private String value;

    public RxMessage(int code, String value) {
        this.code = code;
        this.value = value;
    }


    protected RxMessage(Parcel in) {
        code = in.readInt();
        value = in.readString();
    }

    public static final Creator<RxMessage> CREATOR = new Creator<RxMessage>() {
        @Override
        public RxMessage createFromParcel(Parcel in) {
            return new RxMessage(in);
        }

        @Override
        public RxMessage[] newArray(int size) {
            return new RxMessage[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(value);
    }
}
