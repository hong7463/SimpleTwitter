package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hison7463 on 10/30/16.
 */
@Parcel
public class Tweet {

    long id;
    String text;
    User user;
    String created;
    Entity entity;
    ExtendedEntity extendedEntity;

    public Tweet() {
    }

    public Tweet(long id, String text, User user, String created, Entity entity, ExtendedEntity extendedEntity) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.created = created;
        this.entity = entity;
        this.extendedEntity = extendedEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Entity getEntity() {
        return entity;
    }

    public ExtendedEntity getExtendedEntity() {
        return extendedEntity;
    }

    public void setExtendedEntity(ExtendedEntity extendedEntity) {
        this.extendedEntity = extendedEntity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", created='" + created + '\'' +
                ", entity=" + entity +
                ", extendedEntity=" + extendedEntity +
                '}';
    }

    public static Tweet fromJsonObject(JSONObject object) {
        Tweet tweet = new Tweet();
        try {
            tweet.setId(object.getLong("id"));
            tweet.setText(object.getString("text"));
            tweet.setUser(User.fromJsonObject(object.getJSONObject("user")));
            tweet.setCreated(object.getString("created_at"));
            tweet.setEntity(Entity.fromJsonObject(object.getJSONObject("entities")));
            tweet.setExtendedEntity(ExtendedEntity.fromJsonObject(object.getJSONObject("extended_entities")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray array) {
        List<Tweet> res = new ArrayList<Tweet>(array.length());
        for(int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = array.getJSONObject(i);
                res.add(Tweet.fromJsonObject(object));
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return res;
    }
}
