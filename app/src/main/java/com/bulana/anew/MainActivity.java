package com.bulana.anew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();

        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
            @Override
            public void processFinish(ArrayList<ArticleModel> articles) {
                articleAdapter.updateData(articles);
            }
        });
    }

    private void initialise() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        articleAdapter = new ArticleAdapter(getApplicationContext(), null);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(articleAdapter);
    }


}