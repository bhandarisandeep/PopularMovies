package com.example.sandeep.popularmovies.alldatafiles;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/*
**this class data will be used for the app since it is the data which we get when we first run the api in one page

 */
public class Result implements Parcelable {

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

/*Constructor*/

    public Result(){}

    public Result(int movieId, String movieTitle,
                  String moviePosterPath,
                  String movieLanguage,
                  String movieOverview,
                  String movieReleaseDate,
                  Float movieRatings) {


        id=movieId;
        title=movieTitle;
        posterPath=moviePosterPath;
        originalLanguage=movieLanguage;
        overview=movieOverview;
        releaseDate=movieReleaseDate;
        voteAverage=movieRatings;
    }

    public Result( String title, String posterPath,
            String originalLanguage,
            String overview,
            String releaseDate,
            Float voteAverage)
    {
      this.title=title;
      this.posterPath=posterPath;
      this.overview=overview;
      this.originalLanguage=originalLanguage;
      this.releaseDate=releaseDate;
      this.voteAverage=voteAverage;
    }




    protected Result(Parcel in) {
        if (in.readByte() == 0) {
            voteCount = null;
        } else {
            voteCount = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        byte tmpVideo = in.readByte();
        video = tmpVideo == 0 ? null : tmpVideo == 1;
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readFloat();
        }
        title = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        overview = in.readString();
        releaseDate = in.readString();
    }

    /*The read and write step in the parcel is in the same order so that it can be passed to intent properly*/

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(posterPath);
        dest.writeString(title);
        dest.writeString(originalLanguage);
        dest.writeDouble(voteAverage);
        dest.writeString(releaseDate);
        dest.writeString(overview);
        dest.writeInt(id);

    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[0];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    public Integer getVoteCount()
    {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount)
    {
        this.voteCount = voteCount;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Boolean getVideo()
    {
        return video;
    }

    public void setVideo(Boolean video)
    {
        this.video = video;
    }

    public String getVoteAverage()


    {
        return "( "+voteAverage.toString()+"/10 )";
    }

    public void setVoteAverage(Float voteAverage)

    {
        this.voteAverage = voteAverage;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Double getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Double popularity)
    {
        this.popularity = popularity;
    }

    public String getPosterPath()
    {
        String base_url="http://image.tmdb.org/t/p/w500";
        return (base_url+posterPath);
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage()
    {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle()
    {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle)
    {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds()
    {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds)
    {
        this.genreIds = genreIds;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public static List<Result> DataFromJSON(String jsonstring){
        Gson gson =new Gson();
        JsonElement jsonElement = new Gson().fromJson(jsonstring,JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");
        Type listType = new TypeToken<List<Result>>()
        {}.getType();
        return gson.fromJson(jsonArray.toString(),listType);
    }



}