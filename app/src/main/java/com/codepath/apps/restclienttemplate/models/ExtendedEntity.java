package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by hison7463 on 10/31/16.
 */
@Parcel
public class ExtendedEntity {

    VideoInfo videoInfo;
    String type;

    public ExtendedEntity() {
    }

    public ExtendedEntity(VideoInfo videoInfo, String type) {
        this.videoInfo = videoInfo;
        this.type = type;
    }

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExtendedEntity{" +
                "videoInfo=" + videoInfo +
                ", type='" + type + '\'' +
                '}';
    }

    public static ExtendedEntity fromJsonObject(JSONObject object) {
        ExtendedEntity entity = new ExtendedEntity();
        try {
            entity.setVideoInfo(VideoInfo.fromJsonObject(object.getJSONObject("video_info")));
            entity.setType(object.getString("type"));
        } catch (JSONException e) {
            e.printStackTrace();
            return entity;
        }
        return entity;
    }
}
