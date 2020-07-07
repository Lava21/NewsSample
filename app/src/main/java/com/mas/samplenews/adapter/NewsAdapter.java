package com.mas.samplenews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.mas.samplenews.R;
import com.mas.samplenews.model.NewsModel;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsModel> newsModelList;
    private NewsAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData{
        void onSelected(NewsModel newsModel);
    }

    public NewsAdapter(Context context, List<NewsModel> items, NewsAdapter.onSelectData xSelectData){
        this.mContext = context;
        this.newsModelList = items;
        this.onSelectData = xSelectData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NewsModel newsModel = newsModelList.get(position);

        Glide.with(mContext)
                .load(newsModel.getUrlToImageNews())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_list)
                .into(holder.ivImage);

        holder.tvTitle.setText(newsModel.getTitleNews());
        holder.tvPublushedAt.setText(newsModel.getPublishedATNews());
        holder.cvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(newsModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivImage;
        public TextView tvTitle, tvPublushedAt;
        public MaterialCardView cvNews;
        public View itemView;

        public ViewHolder(View itemView){
            super(itemView);

            cvNews = itemView.findViewById(R.id.cvNews);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPublushedAt = itemView.findViewById(R.id.tvPublishedAt);
            this.itemView = itemView;
        }
    }

}
