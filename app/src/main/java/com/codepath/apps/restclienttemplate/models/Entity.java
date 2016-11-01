package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hison7463 on 10/31/16.
 */
@Parcel
public class Entity {

    List<Media> medias;

    public Entity() {
    }

    public Entity(List<Media> medias) {
        this.medias = medias;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "medias=" + medias +
                '}';
    }

    public static Entity fromJsonObject(JSONObject object) {
        Entity res = new Entity();
        try {
            if(object.get("media") != null) {
                res.setMedias(Media.fromJsonArray(object.getJSONArray("media")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return res;
        }
        return res;
    }
}
