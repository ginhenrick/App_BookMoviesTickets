//package com.example.bookmoviestickets.Cache;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
//import com.example.bookmoviestickets.MyApplication;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Cache;
//import okhttp3.CacheControl;
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class CacheInterceptor implements Interceptor {
//    private Cache cache;
//
//    public CacheInterceptor(Cache cache) {
//        this.cache = cache;
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//
//        // Kiểm tra kết nối mạng
////        if (isNetworkAvailable()) {
//            // Nếu có mạng, tải dữ liệu từ API và lưu cache
//            Response response = chain.proceed(request);
//            // Thiết lập thời gian cache
//            CacheControl cacheControl = new CacheControl.Builder()
//                    .maxAge(1, TimeUnit.HOURS) // Cache trong 1 giờ
//                    .maxStale(7, TimeUnit.DAYS) // Sử dụng cache cũ trong 7 ngày nếu không có mạng
//                    .build();
//            return response.newBuilder()
//                    .header("Cache-Control", cacheControl.toString())
//                    .build();
//        } else {
//            // Nếu không có mạng, sử dụng cache
//            Request cachedRequest = request.newBuilder()
//                    .cacheControl(new CacheControl.Builder()
//                            .onlyIfCached()
//                            .build())
//                    .build();
//            return chain.proceed(cachedRequest);
//        }
//    }
//
//    // Hàm kiểm tra kết nối mạng (Bạn có thể thay đổi cách kiểm tra)
//
//    private boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//        }
//        return false;
//    }
//}
