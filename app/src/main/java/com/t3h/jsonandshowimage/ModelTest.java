package com.t3h.jsonandshowimage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTest {

    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("story_id")
    @Expose
    private Integer storyId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("trend")
    @Expose
    private Integer trend;
    @SerializedName("update")
    @Expose
    private Integer update;
    @SerializedName("check")
    @Expose
    private Integer check;

    private Boolean checkCheckBox = false;

    public Boolean getCheckCheckBox() {
        return checkCheckBox;
    }

    public void setCheckCheckBox(Boolean checkCheckBox) {
        this.checkCheckBox = checkCheckBox;
    }

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
}
