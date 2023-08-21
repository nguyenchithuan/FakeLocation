package edu.wkd.fakelocation.models.request;

import com.google.gson.annotations.SerializedName;

public class CommentRequest {
    @SerializedName("id_image")
    private int idImage;
    @SerializedName("noidung_comment")
    private String comment;
    @SerializedName("linkImage")
    private String linkImage;

    public CommentRequest() {
    }

    public CommentRequest(int idImage, String comment, String linkImage) {
        this.idImage = idImage;
        this.comment = comment;
        this.linkImage = linkImage;
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "idImage=" + idImage +
                ", comment='" + comment + '\'' +
                ", linkImage='" + linkImage + '\'' +
                '}';
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
