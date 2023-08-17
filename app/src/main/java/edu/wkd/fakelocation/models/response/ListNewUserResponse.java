package edu.wkd.fakelocation.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import edu.wkd.fakelocation.models.obj.User;

public class ListNewUserResponse {
    @SerializedName("new_users")
    private ArrayList<User> newUsers;

    public ListNewUserResponse() {
    }

    public ListNewUserResponse(ArrayList<User> newUsers) {
        this.newUsers = newUsers;
    }

    @Override
    public String toString() {
        return "ListNewUserResponse{" +
                "newUsers=" + newUsers +
                '}';
    }

    public ArrayList<User> getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(ArrayList<User> newUsers) {
        this.newUsers = newUsers;
    }
}
