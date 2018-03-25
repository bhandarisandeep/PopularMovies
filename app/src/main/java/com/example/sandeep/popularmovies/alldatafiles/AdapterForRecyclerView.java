package com.example.sandeep.popularmovies.alldatafiles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandeep.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sawan on 3/20/2018.
 */

public class AdapterForRecyclerView extends RecyclerView.Adapter<AdapterForRecyclerView.NumberViewHolder> {
    private static final String TAG = AdapterForRecyclerView.class.getSimpleName();
    public List<Result> resultsList;
    private static int viewHolderCount;
    Context mContext;
    final private ListItemClickListener mOnClickListener;


   // Context context;

    public AdapterForRecyclerView(Context mainactivitycontext, ListItemClickListener listener) {
        mContext=mainactivitycontext;
        mOnClickListener = listener;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.main_app_ui_recycler_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position)
    {
      /*  Log.d(TAG, "#" + position);
        holder.bind(position);*/
      Result result =  resultsList.get(position);
      holder.movieName.setText(result.getOriginalTitle());
      holder.movieRating.setText(result.getVoteAverage());

        //  Log.d(TAG,"Movie Title Name : " +result.getOriginalTitle());
//        URL imageUrl=NetworkUtils.urlForImage(result.getPosterPath());
      String url = result.getPosterPath();
   // String url=imageUrl.toString();
        Log.d(TAG, "the url from the getPoster method is:" +url);
        Picasso.with(mContext).load(url).into(holder.moviePoster);
//        Glide.with(mContext)
//                .load(url)
//                .into(holder.moviePoster);
    }



    @Override
    public int getItemCount()
    {
        if(resultsList==null)
        {
            return 0;
        } else{
            return resultsList.size();
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(Result movieData);


    }

    class NumberViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener
    {
        ImageView moviePoster;
        TextView movieRating;
        TextView movieName;

        public NumberViewHolder(View itemView) {

            super(itemView);
            moviePoster=(ImageView)itemView.findViewById(R.id.iv_movie_poster);
            movieName=(TextView)itemView.findViewById(R.id.tv_movie_name);
            movieRating=(TextView)itemView.findViewById(R.id.tv_movie_ratings);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    int position=getAdapterPosition();
                    Result list=resultsList.get(position);
                    mOnClickListener.onListItemClick(list);
                }
            }).start();


        }
    }

    public void setData(List<Result> rslt)
    {
        resultsList=rslt;
        notifyDataSetChanged();
    }
   }
