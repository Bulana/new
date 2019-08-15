package com.bulana.anew;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private int countNextworkCalls;
    private final String STATE_LIST = "Adapter data";
    private ArrayList<ArticleModel> articles;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            articles = savedInstanceState.getParcelableArrayList(STATE_LIST);
            countNextworkCalls = Integer.parseInt(savedInstanceState.getString("COUNTER"));
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            articleAdapter = new ArticleAdapter(MainActivity.this, articles);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(articleAdapter);

        } else {
            initialise();
        }

    }

    private void initialise() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        articleAdapter = new ArticleAdapter(MainActivity.this, new ArrayList<ArticleModel>());

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(articleAdapter);
    }
    @Override
    public void onStart(){
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
            public void processFinish(final ArrayList<ArticleModel> articles) {
                //Set article data

                articleAdapter.updateData(articles);

                countNextworkCalls++;
                String toastString = String.format(countNextworkCalls+ " ");
                Toast toast=Toast.makeText(getApplicationContext(),toastString,Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();

                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListner() {

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
        outState.putString("COUNTER", countNextworkCalls+"");
        outState.putParcelableArrayList(STATE_LIST, articleAdapter.getArticles());
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
