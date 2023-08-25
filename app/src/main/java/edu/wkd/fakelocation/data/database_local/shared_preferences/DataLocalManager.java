package edu.wkd.fakelocation.data.database_local.shared_preferences;

import android.content.Context;

public class DataLocalManager {
    // Lưu user token trong local
    private static DataLocalManager instance;
    private MySharedPreferencesToken mySharedPreferencesToken;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferencesToken = new MySharedPreferencesToken(context);
    }

    public static DataLocalManager getInstance() {
        if(instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setDataToken(String value) {
        DataLocalManager.instance.mySharedPreferencesToken.putValueToken("token", value);
    }

    public static String getDataToken() {
        return  DataLocalManager.instance.mySharedPreferencesToken.getValueToken("token");
    }
}
