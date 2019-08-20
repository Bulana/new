package com.bulana.anew;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //fields init
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    public ArrayList<ArticleModel> mArticlesList;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final String STATE_LIST = "Adapter data";

    private View loadingView;
    private View noDataImage;
    public int networkCallsCounter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingView = findViewById(R.id.loading_spinner);
        noDataImage = findViewById(R.id.noData);
        noDataImage.setVisibility(View.GONE);

        //checking previously saved articles
        if (savedInstanceState != null) {
            mArticlesList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            networkCallsCounter = Integer.parseInt(savedInstanceState.getString("COUNTER"));
            recyclerView = findViewById(R.id.recyclerView);

            articleAdapter = new ArticleAdapter(MainActivity.this, mArticlesList);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(articleAdapter);

            //set spinner to invisible
            loadingView.setVisibility(View.GONE);

        } else {
            initialise();
        }
    }

    //Initializer for RecyclerView, Adapter
    private void initialise() {
        recyclerView = findViewById(R.id.recyclerView);
        articleAdapter = new ArticleAdapter(MainActivity.this, new ArrayList<ArticleModel>());

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(articleAdapter);
    }

     //internet connection check
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }


    //Activity Life Cycles
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");

        //No Internet Connection dialog
        if(!isConnected()){
            new AlertDialog.Builder(this).setIcon(R.drawable.no_internet)
                    .setTitle("Internet Connection Alert")
                    .setMessage("Please Check your Internet")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            noDataImage.setVisibility(View.VISIBLE);
                        }
                    })
                    .show();
        }

        //set no data image to invisible
        noDataImage.setVisibility(View.GONE);

        //articleData obj, ArticleListAsyncResponse obj created
        new ArticleData().getNewsList(new ArticleListAsyncResponse() {

            @Override
            public void processFinish(ArrayList<ArticleModel> articlesList) {

                //First Simulation
                //Set article data
                loadingView.setVisibility(View.GONE);
                articleAdapter.updateData(articlesList);
                mArticlesList = articlesList;

                //setting listener on adapter, view holder
                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListener() {

                    @Override
                    public void onArticleSelected(ArticleModel articleData) {

                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("url", articleData.getNewsUrl());
                        startActivity(intent);

                    }
                });

                //Second Simulation
                if (networkCallsCounter == 1) {
                    ArticleModel article = new ArticleModel();
                    article.setAuthor("AUTHOR");
                    article.setTitle("TITLE");
                    article.setDescription(("description"));
                    article.setImageUrl("urlToImage");
                    article.setPublishedDate("publishedAt");
                    article.setNewsUrl("url");
                    articlesList.add(0,article);
                }

                //Third Simulation
                if(networkCallsCounter == 2){
                    //clear data in list
                    articlesList.clear();

                    //check size
                    if(articlesList.size() == 0){
                        //show  no data image
                        noDataImage.setVisibility(View.VISIBLE);
                    }
                }networkCallsCounter++;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

}
