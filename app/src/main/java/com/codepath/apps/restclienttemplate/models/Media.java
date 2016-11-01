package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hison7463 on 10/31/16.
 */
@Parcel
public class Media {

    long id;
    String mediaUrl;

    public Media() {
    }

    public Media(long id, String mediaUrl) {
        this.id = id;
        this.mediaUrl = mediaUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", mediaUrl='" + mediaUrl + '\'' +
                '}';
    }

    public static Media fromJsonObject(JSONObject object) {
        Media media = new Media();
        try {
            media.setId(object.getLong("id"));
            media.setMediaUrl(object.getString("media_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return media;
    }

    public static List<Media> fromJsonArray(JSONArray array) {
        List<Media> list = new ArrayList<>(array.length());
        for(int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = array.getJSONObject(i);
                list.add(Media.fromJsonObject(object));
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return list;
    }
}
