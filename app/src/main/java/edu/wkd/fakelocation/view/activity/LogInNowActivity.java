package edu.wkd.fakelocation.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.databinding.ActivityLogInNowBinding;

public class LogInNowActivity extends AppCompatActivity {
    private ActivityLogInNowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInNowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding.btnLoginNow.setOnClickListener(view -> {
            Intent intent = new Intent(LogInNowActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}