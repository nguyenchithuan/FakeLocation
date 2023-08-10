package edu.wkd.fakelocation.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import edu.wkd.fakelocation.R;

public class CustomProgressDialog extends Dialog {
    private String title;
    private String message;
    // 0 là load data, 1 là load auth
    public CustomProgressDialog(@NonNull Context context, int type) {
        super(context);

        // Căn chỉnh cho dialog với thuộc tính window..
        configProgressDialog();

        // Thao tác với ui
        layoutProgressDialog(context, type);
    }


    private void layoutProgressDialog(Context context, int type) {
        View view = LayoutInflater.from(context).inflate(R.layout.loading_layout, null);
        setContentView(view);
        TextView title = view.findViewById(R.id.tv_title_progress);
        TextView message = view.findViewById(R.id.tv_message_progress);
        if(type == 1) {
            message.setVisibility(View.GONE);
        }
    }

    private void configProgressDialog() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String email) {
        this.message = email;
    }
}
