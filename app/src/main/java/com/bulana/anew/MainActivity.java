package com.bulana.anew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public int networkCallsCounter;
    private final String STATE_LIST = "Adapter data";
    public ArrayList<ArticleModel> articlesList;
    private View loadingView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingView = findViewById(R.id.loading_spinner);

        if (savedInstanceState != null) {
            articlesList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            networkCallsCounter = Integer.parseInt(savedInstanceState.getString("COUNTER"));
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

            articleAdapter = new ArticleAdapter(MainActivity.this, articlesList);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(articleAdapter);

            loadingView.setVisibility(View.GONE);

        } else {
            initialise();
        }

    }

    private void initialise() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        articleAdapter = new ArticleAdapter(MainActivity.this, new ArrayList<ArticleModel>());

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(articleAdapter);
        //  loadingView.setVisibility(View.GONE);
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
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "onResume");

        //prep articles
        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
            @Override
            public void processFinish(ArrayList<ArticleModel> articlesList) {

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


                //Set article data
                loadingView.setVisibility(View.GONE);
                articleAdapter.updateData(articlesList);

                String toastString = String.format(networkCallsCounter + " ");
                Toast toast = Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();


                networkCallsCounter++;

                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListener() {

                    @Override
                    public void onArticleSelected(ArticleModel articleData) {

                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("url", articleData.getNewsUrl());
                        startActivity(intent);

                    }
                });
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("COUNTER", networkCallsCounter + "");
        outState.putParcelableArrayList(STATE_LIST, articlesList);
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
