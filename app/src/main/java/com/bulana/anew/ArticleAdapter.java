package com.bulana.anew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ArticleModel> articles;

    public ArticleAdapter(Context context, ArrayList<ArticleModel> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View articleRow = LayoutInflater.from(context).inflate(R.layout.news_row, parent, false);
        return new ViewHolder(articleRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticleModel article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.publishedDate.setText(article.getPublishedDate());
        holder.author.setText(article.getAuthor());
        //Glide.with(context).load(article.getImageUrl().into(holder.articleImage));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void updateData(ArrayList<ArticleModel> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView author;
        private TextView title;
        private TextView description;
        private ImageView articleImage;
        private TextView publishedDate;

        public ViewHolder(View itemView){
            super(itemView);

            articleImage = itemView.findViewById(R.id.newsImage);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.descriptionNews);
            title = itemView.findViewById(R.id.newsTitle);
            publishedDate = itemView.findViewById(R.id.date);

        }
    }
}

