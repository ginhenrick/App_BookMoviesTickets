package com.example.bookmoviestickets.Activities;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookmoviestickets.Adapters.CategoryListAdapter;
import com.example.bookmoviestickets.Adapters.FilmListAdapter;
import com.example.bookmoviestickets.Adapters.SliderAdapters;
import com.example.bookmoviestickets.Domain.GenresItem;
import com.example.bookmoviestickets.Domain.ListFilm;
import com.example.bookmoviestickets.Domain.SliderItems;
import com.example.bookmoviestickets.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTopPhim, adapterSapChieu, adapterDanhMuc;
    private RecyclerView recyclerViewTopPhim, recyclerViewSapChieu, recyclerViewDanhMuc;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2, mStringRequest3;
    private ProgressBar loading1, loading2, loading3;
    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();

    private BottomAppBar bottomAppBar;

    private FirebaseAuth mAuth;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        initView();
        banners();
        sendRequestTopPhim();
        sendRequestSapChieu();
        sendRequestDanhMuc();

        bottomAppBar = findViewById(R.id.bottomAppBar);

        // Thêm hover cho icon Explorer
        ImageView explorerIcon = findViewById(R.id.explorerIcon);
        StateListAnimator stateListAnimator = (StateListAnimator) AnimatorInflater.loadStateListAnimator(this, R.animator.button_hover_animator);
        explorerIcon.setStateListAnimator(stateListAnimator);
        // Thêm hover cho các icon còn lại (Favorite, History, Profile)
        ImageView favoriteIcon = findViewById(R.id.favoriteIcon);
        favoriteIcon.setStateListAnimator(stateListAnimator);
        ImageView historyIcon = findViewById(R.id.historyIcon);
        historyIcon.setStateListAnimator(stateListAnimator);
        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setStateListAnimator(stateListAnimator);
        // Xử lý click cho Explorer (TextView và ImageView)
        TextView explorerText = findViewById(R.id.explorerText);
        explorerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestTopPhim();
                sendRequestSapChieu();
                sendRequestDanhMuc();
            }
        });

        explorerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestTopPhim();
                sendRequestSapChieu();
                sendRequestDanhMuc();
            }
        });

        // Xử lý click cho Profile (chỉ ImageView)

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra đăng nhập trong ImageView
                if (null != mAuth.getCurrentUser()) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                } else {
                    // Chuyển đến LoginActivity nếu chưa đăng nhập
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Kiểm tra xem người dùng đã đăng nhập
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Người dùng đã đăng nhập
        } else {
            // Người dùng chưa đăng nhập
        }
    }



    private void initView() {
        viewPager2 = findViewById(R.id.viewpageSlider);
        recyclerViewTopPhim = findViewById(R.id.view1);
        recyclerViewTopPhim.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSapChieu = findViewById(R.id.view3);
        recyclerViewSapChieu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDanhMuc = findViewById(R.id.view2);
        recyclerViewDanhMuc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);
        loading3 = findViewById(R.id.loading3);
    }

    private void banners() {
        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.wide));
        sliderItems.add(new SliderItems(R.drawable.wide1));
        sliderItems.add(new SliderItems(R.drawable.wide3));

        viewPager2.setAdapter(new SliderAdapters(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideRunnable);
            }
        });
    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunnable, 2000);
    }

    private void sendRequestTopPhim() {
        mRequestQueue = Volley.newRequestQueue(this /*,new HurlStack((HurlStack.UrlRewriter) OkHttpClientHelper.getOkHttpClient())*/);
        loading1.setVisibility(View.VISIBLE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterTopPhim = new FilmListAdapter(items);
            recyclerViewTopPhim.setAdapter(adapterTopPhim);
        }, error -> {
            loading1.setVisibility(View.GONE);
            Log.i("", "onErrorResponse: " + error.toString());
        });

        // Thêm vào request queue
        mRequestQueue.add(mStringRequest);
    }


    private void sendRequestSapChieu() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading3.setVisibility(View.VISIBLE);
        mStringRequest3 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", response -> {
            Gson gson = new Gson();
            loading3.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterSapChieu = new FilmListAdapter(items);
            recyclerViewSapChieu.setAdapter(adapterSapChieu);
        }, error -> {
            loading1.setVisibility(View.GONE);
            Log.i("UiLover", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest3);
    }

    private void sendRequestDanhMuc() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);
            ArrayList<GenresItem> catList = gson.fromJson(response, new TypeToken<ArrayList<GenresItem>>() {
            }.getType());
            adapterDanhMuc = new CategoryListAdapter(catList);
            recyclerViewDanhMuc.setAdapter(adapterDanhMuc);
        }, error -> {
            loading2.setVisibility(View.GONE);
            Log.i("", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest2);
    }
}