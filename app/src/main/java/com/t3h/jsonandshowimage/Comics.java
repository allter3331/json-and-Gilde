package com.t3h.jsonandshowimage;

import java.util.HashMap;
import java.util.Map;

public class Comics {
    private Integer categoryId;
    private Integer storyId;
    private Integer id;
    private String name;
    private String author;
    private Integer status;
    private String kind;
    private String image;
    private String link;
    private String content;
    private Integer trend;
    private Integer update;
    private Integer check;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTrend() {
        return trend;
    }

    public void setTrend(Integer trend) {
        this.trend = trend;
    }

    public Integer getUpdate() {
        return update;
    }

    public void setUpdate(Integer update) {
        this.update = update;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    public String toString(){
        String string = "category_id: " +getCategoryId()+
                "\nstory_id:"+getStoryId()+
                "\nid:"+getId()+
                "\nname:"+getName()+
                "\nauthor:"+getAuthor()+
                "\nstatus:"+getStatus()+
                "\nkind:"+getKind()+
                "\nimage:"+getImage()+
                "\nlink:"+getLink()+
                "\ncontent:"+getContent()+
                "\ntrend:"+getTrend()+
                "\nupdate:"+getUpdate()+
                "\ncheck:"+getCheck();
        return string;
    }
}
