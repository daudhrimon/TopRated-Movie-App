package com.daud.topmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.daud.topmovies.Activity.DetailsActivity;
import com.daud.topmovies.Model.Result;
import com.daud.topmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopMovieAdapter extends RecyclerView.Adapter<TopMovieAdapter.TopMovieVH> {
    private Context context;
    private List<Result> list;

    public TopMovieAdapter(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TopMovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_view_holder,parent,false);
        return new TopMovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMovieVH holder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/original/"+list.get(position).getPosterPath()).into(holder.mvPoster);
        holder.mvTitle.setText(list.get(position).getTitle());
        holder.mvReDate.setText("Release : "+list.get(position).getReleaseDate());
        holder.mvRating.setText("Rating : "+list.get(position).getVoteAverage().toString());

        holder.mvItem.setOnClickListener(view -> {
            mvItemClickListener(list.get(position).getId());
        });
    }

    private void mvItemClickListener(Integer id) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("movie_id",id.toString());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TopMovieVH extends RecyclerView.ViewHolder {
        private ImageView mvPoster;
        private TextView mvTitle,mvRating,mvReDate;
        private LinearLayout mvItem;
        public TopMovieVH(@NonNull View itemView) {
            super(itemView);
            mvPoster = itemView.findViewById(R.id.mvPoster);
            mvTitle = itemView.findViewById(R.id.mvTitle);
            mvRating = itemView.findViewById(R.id.mvRating);
            mvReDate = itemView.findViewById(R.id.mvReDate);
            mvItem = itemView.findViewById(R.id.mvItem);
        }
    }
}
