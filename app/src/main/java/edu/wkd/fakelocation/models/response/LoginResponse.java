package edu.wkd.fakelocation.models.response;

import edu.wkd.fakelocation.models.obj.User;

public class LoginResponse {
    private String ketqua;
    private String token;
    private User user;

    public LoginResponse(String ketqua, String token, User user) {
        this.ketqua = ketqua;
        this.token = token;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResponseLogin{" +
                "ketqua='" + ketqua + '\'' +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
