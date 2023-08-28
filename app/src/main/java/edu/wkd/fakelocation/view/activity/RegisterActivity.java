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
import edu.wkd.fakelocation.data.api.ApiService;
import edu.wkd.fakelocation.databinding.ActivityRegisterBinding;
import edu.wkd.fakelocation.models.request.RegisterRequest;
import edu.wkd.fakelocation.models.response.RegisterResponse;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "zzzzzzzzzzz";
    private CustomProgressDialog dialog;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Khởi tạo UI
        initUI();

        binding.tvLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slilde_in_right, R.anim.slilde_out_right);
            finishAffinity();
        });

        binding.btnRegister.setOnClickListener(view -> {
            registerApp();
        });
    }

    private void registerApp() {
        String strEmail = binding.edEmail.getText().toString().trim();
        String strUsername = binding.edUsername.getText().toString().trim();
        String strPassword = binding.edPassword.getText().toString().trim();
        String strComfirmPassword = binding.edComfirmPassword.getText().toString().trim();
        RegisterRequest dataRegister = new RegisterRequest(strEmail, strUsername, strPassword);
        dialog.show();

        if(!strPassword.equals(strComfirmPassword)) {
            Toast.makeText(this, "Confirm password and password do not match", Toast.LENGTH_SHORT).show();
            dialog.cancel();
            return;
        }
        ApiService.apiService.register(dataRegister).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                Log.d(TAG, "onResponse: " + registerResponse.toString());
                Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                overridePendingTransition(R.anim.slilde_in_right, R.anim.slilde_out_right);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("zzzzzzzzz", "Register: " + t.toString());
                dialog.cancel();
            }
        });
    }

    private void initUI() {
        dialog = new CustomProgressDialog(RegisterActivity.this, 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slilde_in_right, R.anim.slilde_out_right);
    }
}
