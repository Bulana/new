package com.bulana.anew;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleData {

    ArrayList<ArticleModel> articles = new ArrayList<>();

    public void getNewsList(final ArticleListAsyncResponse  callBack) {
        String url = "https://newsapi.org/v1/articles?source=the-verge&apiKey=bfb9a3d5a5a640918fc9b8b075727373";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray articleArray = response.getJSONArray("articles");

                            for (int i = 0; i < articleArray.length();i++) {
                                JSONObject articleObject = articleArray.getJSONObject(i);
                                ArticleModel articleModel = new ArticleModel();

                                articleModel.setAuthor(articleObject.getString("author"));
                                articleModel.setTitle(articleObject.getString("title"));
                                articleModel.setDescription(articleObject.getString("description"));
                                articleModel.setImageUrl(articleObject.getString("urlToImage"));
                                articleModel.setPublishedDate(articleObject.getString("publishedAt"));
                                articleModel.setNewsUrl(articleObject.getString("url"));

                                articles.add(articleModel);
                            }if (null != callBack) callBack.processFinish(articles);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}