package edu.wkd.fakelocation.models.obj;

import com.google.gson.annotations.SerializedName;

public class ChangeBackground {

    @SerializedName("id_image")
    private int idImage;
    @SerializedName("id_user")
    private int idUser;
    private String linkImage;
    @SerializedName("comment_count")
    private int commentCount;
    @SerializedName("created_at")
    private String createdAt;

    public ChangeBackground() {

    }

    public ChangeBackground(int idImage, int idUser, String linkImage, int commentCount, String createdAt) {
        this.idImage = idImage;
        this.idUser = idUser;
        this.linkImage = linkImage;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ChangeBackground{" +
                "idImage=" + idImage +
                ", idUser=" + idUser +
                ", linkImage=" + linkImage +
                ", commentCount=" + commentCount +
                ", createdAt=" + createdAt +
                '}';
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
