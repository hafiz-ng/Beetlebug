package app.beetlebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import app.beetlebug.home.AndroidComponentsHome;
import app.beetlebug.fragments.SecretsFragment;
import app.beetlebug.fragments.WebViewFragment;
import app.beetlebug.home.BiometricFragmentHome;
import app.beetlebug.home.DatabaseFragmentHome;
import app.beetlebug.home.InsecureStorageFragmentHome;
import app.beetlebug.home.NetworkFragmentHome;
import app.beetlebug.home.SecretsFragmentHome;
import app.beetlebug.home.SensitiveDataFragmentHome;
import app.beetlebug.home.WebViewFragmentHome;
import app.beetlebug.user.UserProfileActivity;
import app.beetlebug.utils.CustomTypeFaceSpan;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ScrollView mScrollview;
    CardView mCardView1;
    BottomNavigationView bottomNavigationView;
    RelativeLayout mToolbar;

    Context context;


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
                        Intent intent3 = new Intent(MainActivity.this, UserProfileActivity.class);
                        startActivity(intent3);
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


    public void secretCtf(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new SecretsFragmentHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void dataStorageFragment(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new InsecureStorageFragmentHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void webViewFragment(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new WebViewFragmentHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void databaseFragment(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new DatabaseFragmentHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void biometricFragment(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new BiometricFragmentHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void AndroidComponentsFragment(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new AndroidComponentsHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void infoDisclosureCTF(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new SensitiveDataFragmentHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void networkComFragment(View view) {
        mScrollview.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        Fragment fragment = new NetworkFragmentHome();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }



    public boolean onMenuItemClick(MenuItem item) {
        // Toast message on menu item clicked
        switch (item.getItemId()) {
            case R.id.clear:
                Toast.makeText(this, "Flags cleared!", Toast.LENGTH_LONG).show();
                return true;

            case R.id.logout:
                Toast.makeText(this, "Logout ...", Toast.LENGTH_LONG).show();
                return true;

            case R.id.about:
                Toast.makeText(this, "About ...", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pop_up_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(MainActivity.this);
        popup.show();

        Menu menu = popup.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);
            applyFontToMenuItem(mi);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/kanit_regular.ttf");
//        SpannableString mNewTitle = new SpannableString(mi.getTitle());
//        mNewTitle.setSpan(new CustomTypeFaceSpan("", font, Color.WHITE), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        mi.setTitle(mNewTitle);
    }
}