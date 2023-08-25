package edu.wkd.fakelocation.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import edu.wkd.fakelocation.R;

public class LogInNowActivity extends AppCompatActivity {
    private Button btnLoginNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_now);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        btnLoginNow = findViewById(R.id.btn_login_now);

        btnLoginNow.setOnClickListener(view -> {
            Intent intent = new Intent(LogInNowActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}