package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotosResponse implements Parcelable {

    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("stat")
    @Expose
    private String stat;
    public final static Parcelable.Creator<PhotosResponse> CREATOR = new Creator<PhotosResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PhotosResponse createFromParcel(Parcel in) {
            return new PhotosResponse(in);
        }

        public PhotosResponse[] newArray(int size) {
            return (new PhotosResponse[size]);
        }

    };

    protected PhotosResponse(Parcel in) {
        this.photos = ((Photos) in.readValue((Photos.class.getClassLoader())));
        this.stat = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PhotosResponse() {
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(photos);
        dest.writeValue(stat);
    }

    public int describeContents() {
        return 0;
    }
}
