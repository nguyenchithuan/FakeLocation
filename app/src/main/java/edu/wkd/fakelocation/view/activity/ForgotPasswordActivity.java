package edu.wkd.fakelocation.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.data.api.ApiService;
import edu.wkd.fakelocation.databinding.ActivityForgotPasswordBinding;
import edu.wkd.fakelocation.models.request.ForgotPassRequest;
import edu.wkd.fakelocation.models.response.ForgotPassResponse;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    private CustomProgressDialog dialog;
    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        init();

        binding.btnSend.setOnClickListener(view -> {
            forgotPasswordApp();
        });
    }

    private void forgotPasswordApp() {
        String strEmail = binding.edEmail.getText().toString().trim();
        ForgotPassRequest dataForgotPass = new ForgotPassRequest(strEmail);
        dialog.show();

        ApiService.apiService.forgetPassword(dataForgotPass).enqueue(new Callback<ForgotPassResponse>() {
            @Override
            public void onResponse(Call<ForgotPassResponse> call, Response<ForgotPassResponse> response) {
                ForgotPassResponse forgotPassResponse = response.body();
                dialog.cancel();
                Toast.makeText(ForgotPasswordActivity.this, forgotPassResponse.getKetqua(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ForgotPassResponse> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("zzzzzzzzz", "ForgotPassword: " + t.toString());
                dialog.cancel();
            }
        });
    }

    private void init() {
        dialog = new CustomProgressDialog(ForgotPasswordActivity.this, 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slilde_in_right, R.anim.slilde_out_right);
    }
}