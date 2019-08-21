//package com.bulana.anew;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Build;
//import android.support.annotation.RequiresApi;
//import android.support.design.widget.NavigationView;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class NewsActivity extends AppCompatActivity {
//
//    //fields init
//    private RecyclerView recyclerView;
//    private ArticleAdapter articleAdapter;
//    public ArrayList<ArticleModel> mArticlesList;
//
//    private static final String LOG_TAG = MainActivity.class.getSimpleName();
//    private final String STATE_LIST = "Adapter data";
//
//    private View loadingView;
//    private View noDataImage;
//    public int networkCallsCounter;
//
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//
//    //fragment setup
//    private FragmentManager fragmentManager;
//    private Fragment fragment = null;
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news);
//
//
//        //Tool bar setup
//        //Toolbar toolbar = findViewById(R.id.app_toolbar);
//        //toolbar.setTitle("Nkosi");
//        //setSupportActionBar(toolbar);
//
//
//        //Drawable menu setup
////        DrawerLayout drawer = findViewById(R.id.drawer_layout);
////        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
////        drawer.addDrawerListener(toggle);
////        toggle.syncState();
//
//        //Fragments
//        fragmentManager = getSupportFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragment = new BitcoinFragment();
//        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
//        fragmentTransaction.commit();
//
//        //Navigation
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        //View header = navigationView.inflateHeaderView(R.layout.nav_drawer_header);
//        //TextView profileName = header.findViewById(R.id.profile_name);
//
//        //TO DO: set user's name here:
//        //profileName.setText("User's Name");
//
////        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(MenuItem item) {
////                int id = item.getItemId();
////
////                if (id == R.id.nav_bitcoin) {
////                    fragment = new BitcoinFragment();
////
////                } else if (id == R.id.nav_head_lines) {
////                    fragment = new TopBusinessHeadLinesFragment();
//////
////                }
////                //else if (id == R.id.nav_personal_profile) {
//////                    fragment = new ReferenceFragment();
//////
//////                }
////                FragmentTransaction transaction = fragmentManager.beginTransaction();
////                transaction.replace(R.id.main_container_wrapper, fragment);
////                transaction.commit();
////
////                DrawerLayout drawer = findViewById(R.id.drawer_layout);
////                assert drawer != null;
////                drawer.closeDrawer(GravityCompat.START);
////                return true;
////            }
////        });
//
//        loadingView = findViewById(R.id.loading_spinner);
//        noDataImage = findViewById(R.id.noData);
//     //   noDataImage.setVisibility(View.GONE);
//
//        //checking previously saved articles
////        if (savedInstanceState != null) {
////            mArticlesList = savedInstanceState.getParcelableArrayList(STATE_LIST);
////            networkCallsCounter = Integer.parseInt(savedInstanceState.getString("COUNTER"));
////            recyclerView = findViewById(R.id.fragmentRecyclerView);
////
////            articleAdapter = new ArticleAdapter(NewsActivity.this, mArticlesList);
////            recyclerView.setLayoutManager(new LinearLayoutManager(NewsActivity.this));
////            recyclerView.setAdapter(articleAdapter);
////
////            //set spinner to invisible
////            loadingView.setVisibility(View.GONE);
////
////        } else {
//            initialise();
////        }
//
//    }
//
////    //Initializer for RecyclerView, Adapter
//    private void initialise() {
////        recyclerView = findViewById(R.id.fragmentRecyclerView);
////        articleAdapter = new ArticleAdapter(NewsActivity.this, new ArrayList<ArticleModel>());
////
////        recyclerView.setLayoutManager(new LinearLayoutManager(NewsActivity.this));
////        recyclerView.setAdapter(articleAdapter);
//
//        //tab init
//        tabLayout = findViewById(R.id.tabs);
////        tabLayout.addTab(tabLayout.newTab().setText("Home"));
////        tabLayout.addTab(tabLayout.newTab().setText("About"));
////        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
////        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        //view pager init
//        viewPager = findViewById(R.id.view_pager);
//        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(tabsAdapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//
//    }
//
//    //internet connection check
//    private boolean isConnected(){
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        return networkInfo != null && networkInfo.isConnected();
//    }
//
//
//    //Activity Life Cycles
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d(LOG_TAG, "onResume");
//
//        //No Internet Connection dialog
//        if(!isConnected()){
//            new AlertDialog.Builder(this).setIcon(R.drawable.no_internet)
//                    .setTitle("Internet Connection Alert")
//                    .setMessage("Please Check your Internet")
//                    .setPositiveButton("Close", new DialogInterface.OnClickListener(){
//                        public void onClick(DialogInterface dialog, int which) {
//                            noDataImage.setVisibility(View.VISIBLE);
//                        }
//                    })
//                    .show();
//        }
//
//        //set no data image to invisible
//        noDataImage.setVisibility(View.GONE);
//
//        //articleData obj, ArticleListAsyncResponse obj created
////        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
////
////            @Override
////            public void processFinish(ArrayList<ArticleModel> articlesList) {
////
////                //First Simulation
////                //Set article data
////                loadingView.setVisibility(View.GONE);
////                articleAdapter.updateData(articlesList);
////                mArticlesList = articlesList;
////
////                //setting listener on adapter, view holder
////                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListener() {
////
////                    @Override
////                    public void onArticleSelected(ArticleModel articleData) {
////
////                        Intent intent = new Intent(NewsActivity.this, DetailsActivity.class);
////                        intent.putExtra("url", articleData.getNewsUrl());
////                        startActivity(intent);
////
////                    }
////                });
////
////                //Second Simulation
////                if (networkCallsCounter == 1) {
////                    ArticleModel article = new ArticleModel();
////                    article.setAuthor("AUTHOR");
////                    article.setTitle("TITLE");
////                    article.setDescription(("description"));
////                    article.setImageUrl("urlToImage");
////                    article.setPublishedDate("publishedAt");
////                    article.setNewsUrl("url");
////                    articlesList.add(0,article);
////                }
////
////                //Third Simulation
////                if(networkCallsCounter == 2){
////                    //clear data in list
////                    articlesList.clear();
////
////                    //check size
////                    if(articlesList.size() == 0){
////                        //show  no data image
////                        noDataImage.setVisibility(View.VISIBLE);
////                    }
////                }networkCallsCounter++;
////            }
////        });
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.action_settings, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
//
//
//
