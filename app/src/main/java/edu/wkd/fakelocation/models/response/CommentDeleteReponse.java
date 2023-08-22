package edu.wkd.fakelocation.models.response;

public class CommentDeleteReponse {
    private String message;
    private String error;

    public CommentDeleteReponse() {
    }

    public CommentDeleteReponse(String message, String error) {
        this.message = message;
        this.error = error;
    }

    @Override
    public String toString() {
        return "CommentDeleteReponse{" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

