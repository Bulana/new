package com.bulana.anew;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleData {

    //create the article's list
    ArrayList<ArticleModel> articles = new ArrayList<>();

    //sends a request,
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
                                //local article modal, prevent overrite
                                ArticleModel article = new ArticleModel();

                                //serialize json
                                article.setAuthor(articleObject.getString("author"));
                                article.setTitle(articleObject.getString("title"));
                                article.setDescription(articleObject.getString("description"));
                                article.setImageUrl(articleObject.getString("urlToImage"));
                                article.setPublishedDate(articleObject.getString("publishedAt"));
                                article.setNewsUrl(articleObject.getString("url"));

                                //add single articles to list of articles
                                articles.add(article);
                            }

                            if (callBack != null) {
                                //when data is back, call processFinish which is implemented in
                                callBack.processFinish(articles);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            //TODO: Handle exception
                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                            //TODO: Handle response
                    }
                });
        //volley's
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}