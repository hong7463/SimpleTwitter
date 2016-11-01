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
public class Variant {

    long bitrate;
    String contentType;
    String url;

    public Variant() {
    }

    public Variant(long bitrate, String contentType, String url) {
        this.bitrate = bitrate;
        this.contentType = contentType;
        this.url = url;
    }

    public long getBitrate() {
        return bitrate;
    }

    public void setBitrate(long bitrate) {
        this.bitrate = bitrate;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "bitrate=" + bitrate +
                ", contentType='" + contentType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static Variant fromJsonObject(JSONObject object) {
        Variant variant = new Variant();
        try {
            variant.setBitrate(object.getLong("bitrate"));
            variant.setContentType(object.getString("content_type"));
            variant.setUrl(object.getString("url"));
        } catch (JSONException e) {
            e.printStackTrace();
            return variant;
        }
        return variant;
    }

    public static List<Variant> fromJsonArray(JSONArray array) {
        List<Variant> list = new ArrayList<>(array.length());
        for(int i = 0; i < array.length(); i++) {
            try {
                list.add(Variant.fromJsonObject(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return list;
    }
}
