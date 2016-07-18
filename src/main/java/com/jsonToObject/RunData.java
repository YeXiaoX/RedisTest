package com.jsonToObject;


import java.text.SimpleDateFormat;
import java.util.Date;

public class RunData {

    private String user_id;

    private String name;

    private String avatar;

    private Integer gender;

    private Long rank;

    private String cost_time;

    private String cost_time_str;

    private boolean follow_state;

    private Long like_num;

    private boolean like_state;//true表示已点赞

    public RunData() {
        super();
    }

    public RunData(String user_id, String name, String avatar, Integer gender) {
        super();
        this.user_id = user_id;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCost_time() {
        return cost_time;
    }

    public void setCost_time(String cost_time) {
        this.cost_time = cost_time;

    }

    public boolean isFollow_state() {
        return follow_state;
    }

    public void setFollow_state(boolean follow_state) {
        this.follow_state = follow_state;
    }

    public Long getLike_num() {
        return like_num;
    }

    public void setLike_num(Long like_num) {
        this.like_num = like_num;
    }

    public boolean isLike_state() {
        return like_state;
    }

    public void setLike_state(boolean like_state) {
        this.like_state = like_state;
    }

    public String getCost_time_str() {
        return cost_time_str;
    }

    public void setCost_time_str(String cost_time_str) {
        this.cost_time_str = cost_time_str;
    }

    @Override
    public String toString() {
        return "RunData{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", rank=" + rank +
                ", cost_time='" + cost_time + '\'' +
                ", cost_time_str='" + cost_time_str + '\'' +
                ", follow_state=" + follow_state +
                ", like_num=" + like_num +
                ", like_state=" + like_state +
                '}';
    }
}
