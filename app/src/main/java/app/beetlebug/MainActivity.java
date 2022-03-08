package app.beetlebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.concurrent.Executor;

import app.beetlebug.fragments.InsecureStorageFragment;
import app.beetlebug.fragments.SecretsFragment;

public class MainActivity extends AppCompatActivity {

    ScrollView mScrollview;
    CardView mCardView1;
    BottomNavigationView bottomNavigationView;
    RelativeLayout mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScrollview = findViewById(R.id.scroll_view);
        mToolbar = findViewById(R.id.toolbar);


        mCardView1 = findViewById(R.id.secretCard);



        // bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_flag:
                        Intent intent2 = new Intent(MainActivity.this, FlagsOverview.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_user:
//                        Intent intent3 = new Intent(HomeActivity.this, CTF_Captured.class);
//                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }
    }

    public void goBack(View view) {
    }

    public void secretCtf(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        Fragment fragment = new SecretsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }
}