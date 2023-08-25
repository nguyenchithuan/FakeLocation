package edu.wkd.fakelocation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.data.database_local.room.UserDatabase;
import edu.wkd.fakelocation.data.database_local.shared_preferences.DataLocalManager;
import edu.wkd.fakelocation.models.obj.User;
import edu.wkd.fakelocation.view.fragment.HomeFragment;
import edu.wkd.fakelocation.view.fragment.PictureFragment;
import edu.wkd.fakelocation.view.fragment.ProfileFragment;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;
    private CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  transparent Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Khởi tạo ui
        init();
        eventMeowNavagationBottom();

        // // open fragment HomeFragment trong app
        openFragment(HomeFragment.newInstance());
    }

    private void init() {
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Khởi tạo icon, thiết lập màu sắc icon ở file.xml
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_image));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_person));

        // open iten 1 trong app
        bottomNavigation.show(1, true);

        dialog = new CustomProgressDialog(MainActivity.this, 0);
    }

    private void eventMeowNavagationBottom() {
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        openFragment(HomeFragment.newInstance());
                        break;
                    case 2:
                        openFragment(PictureFragment.newInstance());
                        break;
                    case 3:
                        List<User> listUser = UserDatabase.getInstance(MainActivity.this).userDao().getListUser();
                        String strToken = DataLocalManager.getDataToken();
                        Log.d("zzzz", "listUser_room: " + listUser.size());
                        Log.d("zzzz", "strToken: " + strToken);
                        if (strToken.length() == 0 || listUser.size() == 0) {
                            Intent intent = new Intent(MainActivity.this, LogInNowActivity.class);
                            startActivity(intent);
                        } else {
                            openFragment(ProfileFragment.newInstance());
                        }
                        break;
                }
                return null;
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navHostFragment, fragment);
        transaction.commit();

        // For Navigation Bar Color
        getWindow().setNavigationBarColor(Color.parseColor("#5C6AED"));
    }
}