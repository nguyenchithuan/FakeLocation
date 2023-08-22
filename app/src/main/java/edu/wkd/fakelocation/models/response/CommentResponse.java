package edu.wkd.fakelocation.models.response;

import com.google.gson.annotations.SerializedName;

import edu.wkd.fakelocation.models.obj.Comment;

public class CommentResponse {
    @SerializedName("comment")
    private Comment comment;
    @SerializedName("message")
    private String message;

    public CommentResponse() {
    }

    public CommentResponse(Comment comment, String message) {
        this.comment = comment;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "comment=" + comment +
                ", message='" + message + '\'' +
                '}';
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
