package com.bulana.anew;


import android.os.Parcel;
import android.os.Parcelable;

public class ArticleModel implements Parcelable {

    private String author;
    private String title;
    private String description;
    private String newsUrl;
    private String imageUrl;
    private String publishedDate;


    protected ArticleModel(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        newsUrl = in.readString();
        imageUrl = in.readString();
        publishedDate = in.readString();
    }
    public ArticleModel(){}

    public static final Creator<ArticleModel> CREATOR = new Creator<ArticleModel>() {
        @Override
        public ArticleModel createFromParcel(Parcel in) {
            return new ArticleModel(in);
        }

        @Override
        public ArticleModel[] newArray(int size) {
            return new ArticleModel[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(newsUrl);
        dest.writeString(imageUrl);
        dest.writeString(publishedDate);
    }
}
