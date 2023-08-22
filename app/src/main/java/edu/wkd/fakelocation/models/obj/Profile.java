package edu.wkd.fakelocation.models.obj;

import com.google.gson.annotations.SerializedName;

public class Profile {
    private int id;
    @SerializedName("user_name")
    private String userUame;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("comment_count")
    private int commenCount;
    @SerializedName("image_count")
    private int imageCount;
    private String email;
    @SerializedName("link_avatar")
    private String linkAvatar;

    public Profile() {
    }

    public Profile(int id, String userUame, String fullName, int commenCount, int imageCount, String email, String linkAvatar) {
        this.id = id;
        this.userUame = userUame;
        this.fullName = fullName;
        this.commenCount = commenCount;
        this.imageCount = imageCount;
        this.email = email;
        this.linkAvatar = linkAvatar;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", userUame='" + userUame + '\'' +
                ", fullName='" + fullName + '\'' +
                ", commenCount=" + commenCount +
                ", imageCount=" + imageCount +
                ", email='" + email + '\'' +
                ", linkAvatar='" + linkAvatar + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserUame() {
        return userUame;
    }

    public void setUserUame(String userUame) {
        this.userUame = userUame;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCommenCount() {
        return commenCount;
    }

    public void setCommenCount(int commenCount) {
        this.commenCount = commenCount;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }
}
