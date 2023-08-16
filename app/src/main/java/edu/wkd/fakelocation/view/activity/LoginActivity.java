package edu.wkd.fakelocation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.api.ApiService;
import edu.wkd.fakelocation.models.request.LoginRequest;
import edu.wkd.fakelocation.models.response.LoginResponse;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import edu.wkd.fakelocation.util.Utit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "zzzzzzzzzzzz";
    private TextView tv_register;
    private Button btn_login;
    private EditText ed_email;
    private EditText ed_password;
    private TextView tv_forget_password;
    private CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        init();

        btn_login.setOnClickListener(v -> {
            loginApp();
        });

        tv_register.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            // pending animation activity
            overridePendingTransition(R.anim.slilde_in_left, R.anim.slilde_out_left);
        });

        tv_forget_password.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
            // pending animation activity
            overridePendingTransition(R.anim.slilde_in_left, R.anim.slilde_out_left);
        });
    }

    private void loginApp() {
        String strEmail = ed_email.getText().toString().trim();
        String strPassword = ed_password.getText().toString().trim();
        LoginRequest dataLogin = new LoginRequest(strEmail, strPassword);
        dialog.show();

        ApiService.apiService.login(dataLogin).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                Utit.TOKEN = loginResponse.getToken(); // Set data vào token để ở đâu cx có thể sử dụng
                if (Utit.TOKEN != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getKetqua(), Toast.LENGTH_SHORT).show();
                }
                dialog.cancel();
                Log.d(TAG, "onResponse: " + loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG, "Login: " + t.toString());
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }

    private void init() {
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        tv_forget_password = findViewById(R.id.tv_forget_password);

        dialog = new CustomProgressDialog(LoginActivity.this, 1);
    }
}