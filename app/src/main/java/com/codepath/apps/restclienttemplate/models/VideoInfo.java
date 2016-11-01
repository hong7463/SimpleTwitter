package com.codepath.apps.restclienttemplate.models;

import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.List;

/**
 * Created by hison7463 on 10/31/16.
 */
@Parcel
public class VideoInfo {

    List<Variant> variants;

    public VideoInfo() {
    }

    public VideoInfo(List<Variant> variants) {
        this.variants = variants;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "variants=" + variants +
                '}';
    }

    public static VideoInfo fromJsonObject(JSONObject object) {
        VideoInfo videoInfo = new VideoInfo();
        try {
            videoInfo.setVariants(Variant.fromJsonArray(object.getJSONArray("variants")));
        } catch (JSONException e) {
            e.printStackTrace();
            return videoInfo;
        }
        return videoInfo;
    }

}
