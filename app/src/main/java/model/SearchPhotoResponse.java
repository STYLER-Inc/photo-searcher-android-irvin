package model;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SearchPhotoResponse {
    @SerializedName("page")
    public int page;
    @SerializedName("pages")
    public int pages;
    @SerializedName("perpage")
    public int perpage;
    @SerializedName("total")
    public String total;
    @SerializedName("photo")
    public List<Photo> photo;

    public static class Photo {
        @SerializedName("id")
        public int id;
        @SerializedName("owner")
        public String owner;
        @SerializedName("secret")
        public String secret;
        @SerializedName("server")
        public String server;
        @SerializedName("farm")
        public int farm;
        @SerializedName("title")
        public String title;
        @SerializedName("ispublic")
        public int ispublic;
        @SerializedName("isfriend")
        public int isfriend;
        @SerializedName("isfamily")
        public int isfamily;
    }
}
