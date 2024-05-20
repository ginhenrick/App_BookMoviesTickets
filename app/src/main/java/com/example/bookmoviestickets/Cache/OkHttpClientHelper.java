//package com.example.bookmoviestickets.Cache;
//
//import com.example.bookmoviestickets.MyApplication;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Cache;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//
//public class OkHttpClientHelper {
//    public static OkHttpClient getOkHttpClient() {
//        // Tạo cache 10MB trong thư mục cache của ứng dụng
//        Cache cache = new Cache(new File(MyApplication.getAppContext().getCacheDir(), "http-cache"), 10 * 1024 * 1024);
//
//        // Tạo HttpLoggingInterceptor để log thông tin yêu cầu và phản hồi
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Log toàn bộ thông tin
//
//        return new OkHttpClient.Builder()
//                .cache(cache) // Áp dụng cache
//                .addInterceptor(new CacheInterceptor(cache)) // Áp dụng interceptor cache
//                .addInterceptor(loggingInterceptor) // Áp dụng interceptor log
//                .connectTimeout(10, TimeUnit.SECONDS) // Thiết lập thời gian kết nối tối đa
//                .readTimeout(10, TimeUnit.SECONDS) // Thiết lập thời gian đọc dữ liệu tối đa
//                .writeTimeout(10, TimeUnit.SECONDS) // Thiết lập thời gian ghi dữ liệu tối đa
//                .build();
//    }
//}
