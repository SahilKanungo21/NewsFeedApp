package com.application.newsfeed.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsfeed.Pojo.Model;
import com.application.newsfeed.R;
import com.application.newsfeed.WebViewActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> newsContentList;

    public Adapter(Context context, ArrayList<Model> newsContentList) {
        this.context=context;
        this.newsContentList=newsContentList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url",newsContentList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.mTime.setText(" Published At:- "+newsContentList.get(position).getPublishedAt());
        holder.mAuthor.setText(newsContentList.get(position).getAuthor());
        holder.mHeading.setText(newsContentList.get(position).getTitle());
        holder.mContent.setText(newsContentList.get(position).getDescription());
        Glide.with(context).load(newsContentList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsContentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mHeading,mContent,mAuthor,mTime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeading=itemView.findViewById(R.id.mainHeading);
            mContent=itemView.findViewById(R.id.content);
            mAuthor=itemView.findViewById(R.id.author);
            mTime=itemView.findViewById(R.id.publishedAt);
            imageView=itemView.findViewById(R.id.imageView);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
