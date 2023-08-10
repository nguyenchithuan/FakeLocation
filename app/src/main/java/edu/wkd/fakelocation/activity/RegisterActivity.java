package edu.wkd.fakelocation.activity;

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
import edu.wkd.fakelocation.models.postdata.DataRegister;
import edu.wkd.fakelocation.models.response.ResponseRegister;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "zzzzzzzzzzz";
    private TextView tv_login;
    private EditText ed_email;
    private EditText ed_username;
    private EditText ed_password;
    private EditText ed_comfirm_password;
    private Button btn_register;
    private CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Khởi tạo UI
        initUI();

        tv_login.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slilde_in_right, R.anim.slilde_out_right);
            finishAffinity();
        });

        btn_register.setOnClickListener(view -> {
            registerApp();
        });
    }

    private void registerApp() {
        String strEmail = ed_email.getText().toString().trim();
        String strUsername = ed_username.getText().toString().trim();
        String strPassword = ed_password.getText().toString().trim();
        String strComfirmPassword = ed_comfirm_password.getText().toString().trim();
        DataRegister dataRegister = new DataRegister(strEmail, strUsername, strPassword);
        dialog.show();

        if(!strPassword.equals(strComfirmPassword)) {
            Toast.makeText(this, "Confirm password and password do not match", Toast.LENGTH_SHORT).show();
            dialog.cancel();
            return;
        }
        ApiService.apiService.register(dataRegister).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                ResponseRegister responseRegister = response.body();
                Log.d(TAG, "onResponse: " + responseRegister.toString());
                Toast.makeText(RegisterActivity.this, responseRegister.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                overridePendingTransition(R.anim.slilde_in_right, R.anim.slilde_out_right);
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("zzzzzzzzz", "Register: " + t.toString());
                dialog.cancel();
            }
        });
    }

    private void initUI() {
        tv_login = findViewById(R.id.tv_login);
        ed_email = findViewById(R.id.ed_email);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        ed_comfirm_password = findViewById(R.id.ed_comfirm_password);
        btn_register = findViewById(R.id.btn_register);

        dialog = new CustomProgressDialog(RegisterActivity.this, 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slilde_in_right, R.anim.slilde_out_right);
    }
}
