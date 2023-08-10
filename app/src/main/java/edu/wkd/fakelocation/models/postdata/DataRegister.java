package edu.wkd.fakelocation.models.postdata;

import com.google.gson.annotations.SerializedName;

public class DataRegister {
    @SerializedName("email")
    private String email;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("password")
    private String password;

    public DataRegister(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataRegister{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
