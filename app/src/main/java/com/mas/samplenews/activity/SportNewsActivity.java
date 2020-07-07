package com.mas.samplenews.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mas.samplenews.R;
import com.mas.samplenews.adapter.NewsAdapter;
import com.mas.samplenews.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mas.samplenews.BuildConfig.GET_CATEGORY_SPORTS;

public class SportNewsActivity extends AppCompatActivity implements NewsAdapter.onSelectData{
    RecyclerView rvSportNews;
    NewsAdapter newsAdapter;
    List<NewsModel> newsModelList = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_news);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvSportNews = findViewById(R.id.rvSportNews);
        rvSportNews.setHasFixedSize(true);
        rvSportNews.setLayoutManager(new LinearLayoutManager(this));

        setupToolbar();
        loadJSON();
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.tbSportNews);
        toolbar.setTitle("Berita Olahraga");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadJSON(){
        progressDialog.show();
        AndroidNetworking.get(GET_CATEGORY_SPORTS)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray jsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                NewsModel newsModel = new NewsModel();
                                newsModel.setTitleNews(jsonObject.getString("title"));
                                newsModel.setUrlNews(jsonObject.getString("url"));
                                newsModel.setPublishedATNews(jsonObject.getString("publishedAt"));
                                newsModel.setUrlToImageNews(jsonObject.getString("urlToImage"));

                                newsModelList.add(newsModel);
                                showNews();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(SportNewsActivity.this,
                                    "Gagal menampilkan data!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(SportNewsActivity.this,
                                "Tidak ada jaringan Internet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showNews(){
        newsAdapter = new NewsAdapter(SportNewsActivity.this, newsModelList, this);
        rvSportNews.setAdapter(newsAdapter);
    }

    @Override
    public void onSelected(NewsModel newsModel) {
        startActivity(new Intent(SportNewsActivity.this, OpenNewsActivity.class)
                .putExtra("url", newsModel.getUrlNews()));
    }
}
