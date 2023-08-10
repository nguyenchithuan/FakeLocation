package edu.wkd.fakelocation.models.response;

public class ResponseForgotPass {
    private String ketqua;

    public ResponseForgotPass(String ketqua) {
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
