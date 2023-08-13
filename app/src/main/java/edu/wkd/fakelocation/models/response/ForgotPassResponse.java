package edu.wkd.fakelocation.models.response;

public class ForgotPassResponse {
    private String ketqua;

    public ForgotPassResponse(String ketqua) {
        this.ketqua = ketqua;
    }

    @Override
    public String toString() {
        return "ResponseForgetPass{" +
                "ketqua='" + ketqua + '\'' +
                '}';
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }
}
