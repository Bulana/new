package com.bulana.anew;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SportsFragment extends Fragment {

    private ArticleAdapter articleAdapter;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public int networkCallsCounter;
    public ArrayList<ArticleModel> articlesList;
    private View loadingView;
    private View noDataImage;

    public SportsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflating view
        View view = inflater.inflate(R.layout.fragment_all_news, container, false);

        //
        noDataImage = view.findViewById(R.id.noData);
        loadingView = view.findViewById(R.id.loading_spinner);
        loadingView.setVisibility(View.VISIBLE);
        noDataImage.setVisibility(View.GONE);

        articleAdapter = new ArticleAdapter(getActivity());

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.fragmentRecyclerView);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setHasFixedSize(true);

        //regardless
        recyclerView.setAdapter(articleAdapter);

        //get data and set to articleList
        getData();


        //Loader and spinner
        if(articlesList != null) {
            loadingView.setVisibility(View.GONE);
            noDataImage.setVisibility(View.VISIBLE);

        }else{
            loadingView.setVisibility(View.GONE);
            noDataImage.setVisibility(View.GONE);
        }

        return view;
    }

    public void setListener() {
        articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListener() {

            @Override
            public void onArticleSelected(ArticleModel articleData) {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("url", articleData.getNewsUrl());
                startActivity(intent);
            }
        });
    }

    public List<ArticleModel> getData() {

        Log.d(LOG_TAG, "onResume");

        //prep articles
        new ArticleData().getNewsList(Constant.BITCOIN_URL,new ArticleListAsyncResponse() {
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
                //loadingView.setVisibility(View.GONE);
                if(articlesList != null && articlesList.size() > 0) {
                    articleAdapter.updateData(articlesList);
                    articleAdapter.setOnClickListener(null);
                    setListener();
                }

                networkCallsCounter++;


            }
        });
        return articlesList;
    }

}