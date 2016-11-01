package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by hison7463 on 10/30/16.
 */
@Parcel
public class User {

    String name;
    String profile_image_url;
    String atName;

    public User() {
    }

    public User(String name, String profile_image_url, String atName) {
        this.name = name;
        this.profile_image_url = profile_image_url;
        this.atName = atName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", atName='" + atName + '\'' +
                '}';
    }

    public static User fromJsonObject(JSONObject object) {
        User user = new User();
        try {
            user.setName(object.getString("name"));
            user.setProfile_image_url(object.getString("profile_image_url"));
            user.setAtName(object.getString("screen_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
