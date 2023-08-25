package edu.wkd.fakelocation.data.database_local.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferencesToken {
    private static final String MY_SHARES_PREFERENCES = "MY_SHARES_PREFERENCES";
    private Context context;

    public MySharedPreferencesToken(Context context) {
        this.context = context;
    }

    public void putValueToken(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARES_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getValueToken(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARES_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
}
