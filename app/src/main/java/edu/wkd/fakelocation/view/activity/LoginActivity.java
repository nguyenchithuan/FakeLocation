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

import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.data.api.ApiService;
import edu.wkd.fakelocation.data.database_local.room.UserDatabase;
import edu.wkd.fakelocation.data.database_local.shared_preferences.DataLocalManager;
import edu.wkd.fakelocation.models.obj.User;
import edu.wkd.fakelocation.models.request.LoginRequest;
import edu.wkd.fakelocation.models.response.LoginResponse;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "zzzzzz";
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

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
                String token = "Bearer " + loginResponse.getToken(); // Set data vào token để ở đâu cx có thể sử dụng
                // Lưu token trong SharedPreferences
                DataLocalManager.setDataToken(token);
                // Lưu user trong room
                UserDatabase.getInstance(LoginActivity.this).userDao().insertUser(loginResponse.getUser());

                if (token.length() > 0) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getKetqua(), Toast.LENGTH_SHORT).show();
                }
                dialog.cancel();
                Log.d("zzzzz", "onResponse: " + loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("zzzzz", "Login: " + t.toString());
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