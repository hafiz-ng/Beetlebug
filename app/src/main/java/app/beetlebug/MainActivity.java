package app.beetlebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import app.beetlebug.ctf.BinaryPatchActivity;
import app.beetlebug.ctf.RootDetectorActivity;
import app.beetlebug.home.AndroidComponentsHome;
import app.beetlebug.home.BiometricFragmentHome;
import app.beetlebug.home.DatabaseFragmentHome;
import app.beetlebug.home.InsecureStorageFragmentHome;
import app.beetlebug.home.DeviceFragmentHome;
import app.beetlebug.home.SecretsFragmentHome;
import app.beetlebug.home.SensitiveDataFragmentHome;
import app.beetlebug.home.WebViewFragmentHome;
import app.beetlebug.user.UserProfileActivity;
import app.beetlebug.user.UserSignUp;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    ScrollView mScrollview;
    CardView mCardView1;
    BottomNavigationView bottomNavigationView;
    RelativeLayout mToolbar;
    public static SharedPreferences preferences;
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

        encryptPreferences();
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

    public void patchFragment(View view) {
        Intent i = new Intent(MainActivity.this, BinaryPatchActivity.class);
        startActivity(i);
    }



    public boolean onMenuItemClick(MenuItem item) {
        // Toast message on menu item clicked
        switch (item.getItemId()) {
            case R.id.clear:
                SharedPreferences sharedPreferences_flg = getSharedPreferences("flag_scores", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_flg = sharedPreferences_flg.edit();
                editor_flg.clear();
                editor_flg.commit();
                Toast.makeText(this, "All flags cleared!", Toast.LENGTH_LONG).show();

                return true;

            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent logout = new Intent(MainActivity.this, UserSignUp.class);
                startActivity(logout);
                return true;

            case R.id.about:
                showDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_about, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        alertDialog.show();
    }

    public void openDeveloperPage(View view) {
        String url = "http://www.hafiz.ng";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pop_up_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(MainActivity.this);
        popup.show();
    }

    private void encryptPreferences() {
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("3_pref", "MHgxNDQyYzA0");
        editor.putString("4_ext_store", "MHgzOTgyYyU0");
        editor.putString("5_sqlite", "MHgxMTcyYzA0");
        editor.putString("6_activity", "MHgzMzRmMjIx");
        editor.putString("7_content", "MHg3MzM0MjFN");
        editor.putString("8_service", "MHgyMjIxMDNB");
        editor.putString("9_log", "MHg1NTU0MWQz");
        editor.putString("10_sqli", "MHg5MTMzNFox");
        editor.putString("11_firebase", "MHgzMzY1QTEw");
        editor.putString("12_url", "MHgzM2YzMzQx");
        editor.putString("13_xss", "MHg2NnI5MjE0");
        editor.putString("14_clip", "MHgxMTMyYzQh");
        editor.putString("15_fingerprint", "MHg0M0oxMjMm");
        editor.putString("16_patch", "MHgzM2U5JGU=");
        editor.apply();
    }

}