package edu.wkd.fakelocation.models.obj;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("id_comment")
    private int idComment;
    @SerializedName("id_user")
    private int idUser;
    @SerializedName("id_image")
    private int idImage;
    @SerializedName("link_avatar")
    private String linkAvatar;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("noidung_comment")
    private String noidungComment;
    @SerializedName("created_at")
    private String createdAt;

    public Comment() {
    }

    public Comment(int idComment, int idUser, int idImage, String linkAvatar, String userName, String noidungComment, String createdAt) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.idImage = idImage;
        this.linkAvatar = linkAvatar;
        this.userName = userName;
        this.noidungComment = noidungComment;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", idUser=" + idUser +
                ", idImage=" + idImage +
                ", linkAvatar='" + linkAvatar + '\'' +
                ", userName='" + userName + '\'' +
                ", noidungComment='" + noidungComment + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoidungComment() {
        return noidungComment;
    }

    public void setNoidungComment(String noidungComment) {
        this.noidungComment = noidungComment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
