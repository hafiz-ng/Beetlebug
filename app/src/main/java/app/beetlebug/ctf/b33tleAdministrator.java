package app.beetlebug.ctf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

import app.beetlebug.R;
import app.beetlebug.db.DatabaseHelper;
import app.beetlebug.fragments.AddUserFragment;
import app.beetlebug.utils.MyLoader;

public class b33tleAdministrator extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    RelativeLayout relativeLayout, relativeLayout2;
    LinearLayout linearLayout;
    LinearLayout flg;

    private DatabaseHelper myHelper;

    private ListView listView;

    // we can use the SimpleCursorAdapter to load a cursor object.
    // The cursor object is the result set. But in order to use the
    // SimpleCursorAdapter we have to get the columns names and the
    // resources that we want to populate those columns names with
    private SimpleCursorAdapter adapter;

    private static final int LOADER_ID = 1976;

    // from String[] array maps the database columns
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.PASS};

    // to int[] array maps textviews of the resources
    final int[] to = new int[] {R.id.id, R.id.name};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b33tle_activtity);
        relativeLayout = findViewById(R.id.toolbar);
        relativeLayout2 = findViewById(R.id.relativeLayout);
        linearLayout = findViewById(R.id.linear_layout_scroll);
        flg = findViewById(R.id.ctfLayout);

        if(Build.VERSION.SDK_INT>=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }

        // initialize
        myHelper = new DatabaseHelper(this);
        myHelper.open();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.textViewEmpty));

        adapter = new SimpleCursorAdapter(this, R.layout.users, null, from, to, 0 );

        listView.setAdapter(adapter);

        // initialize the Loader in onCreate()
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    public void addUser(View view) {
        relativeLayout.setVisibility(View.GONE);
        relativeLayout2.setVisibility(View.GONE);
        flg.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        Fragment fragment = new AddUserFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new MyLoader(this, myHelper);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        // swap the new cursor in.
        // The framework that will take care of closing the old cursor once we return
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }



}