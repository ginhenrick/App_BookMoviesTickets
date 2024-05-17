package com.example.bookmoviestickets.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.bookmoviestickets.Adapters.ActorListAdapter;
import com.example.bookmoviestickets.Adapters.CategoryEachFilmListAdapter;
import com.example.bookmoviestickets.Domain.FilmItems;
import com.example.bookmoviestickets.R;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieSummaryInfo, movieActorInfo;
    private int idFilm;
    private ImageView pic2;
    private RecyclerView.Adapter adapterActorList, adapterDanhMuc;
    private RecyclerView recyclerViewActors, recyclerViewDanhMuc;
    private NestedScrollView scrollView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        senRequest();
    }



    @SuppressLint("WrongViewCast")
    private void initView() {
        titleTxt = findViewById(R.id.movieNameTxt);
        progressBar = findViewById(R.id.progressBarDetail);
        scrollView = findViewById(R.id.scrollView3);
        Log.d("DetailActivity", "scrollView: " + scrollView); // Thêm log để kiểm tra giá trị
        pic2 = findViewById(R.id.picDetail);
        movieRateTxt = findViewById(R.id.movieStar);
        movieTimeTxt = findViewById(R.id.movieTime);
        movieSummaryInfo = findViewById(R.id.movieSummary);
        movieActorInfo = findViewById(R.id.movieActorsInfo);
        ImageView backImg = findViewById(R.id.backImg);
        recyclerViewDanhMuc = findViewById(R.id.genreView);
        recyclerViewActors = findViewById(R.id.imagesRecyler);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDanhMuc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        backImg.setOnClickListener(v -> finish());
    }

    private void senRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItems item = gson.fromJson(response, FilmItems.class);
            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);
            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummaryInfo.setText(item.getPlot());
            movieActorInfo.setText(item.getActors());

            if (item.getImages()!=null){
                adapterActorList = new ActorListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActorList);
            }
            if (item.getGenres()!=null) {
                adapterDanhMuc = new CategoryEachFilmListAdapter(item.getGenres());
                recyclerViewDanhMuc.setAdapter(adapterDanhMuc);
            }

        }, error -> progressBar.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

}