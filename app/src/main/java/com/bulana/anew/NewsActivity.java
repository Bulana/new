package com.bulana.anew;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    //fragment setup
    private FragmentManager fragmentManager;
    private Fragment fragment = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        toolbar.setTitle("Nkosi");
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new BitcoinFragment();
        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        fragmentTransaction.commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.inflateHeaderView(R.layout.nav_drawer_header);
        TextView profileName = header.findViewById(R.id.profile_name);

        //TO DO: set user's name here:
        profileName.setText("User's Name");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_bitcoin) {
                    fragment = new BitcoinFragment();

                } else if (id == R.id.nav_head_lines) {
                    fragment = new TopBusinessHeadLinesFragment();
//
                }
                //else if (id == R.id.nav_personal_profile) {
//                    fragment = new ReferenceFragment();
//
//                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container_wrapper, fragment);
                transaction.commit();

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                assert drawer != null;
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



