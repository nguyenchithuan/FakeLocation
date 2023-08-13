package edu.wkd.fakelocation.models.response;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("account")
    private Account account;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "ResponseRegister{" +
                "message='" + message + '\'' +
                ", account=" + account +
                '}';
    }

    public class Account {
        @SerializedName("email")
        public String email;

        @Override
        public String toString() {
            return "Account{" +
                    "email='" + email + '\'' +
                    '}';
        }
    }
}
