package com.example.experiments.Retrofir;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static String ROOT = "https://www.roservice.in/test_app/";

    private static Retrofit retrofit = null;
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public static Retrofit getClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT)
                    .client(getHeader())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
    public static OkHttpClient getHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .addNetworkInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request request = null;
                                Log.d("--Authorization-- ", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImYyMjhhZmU1Y2JkZTUwZTE0YzZlYjIxOTRjMjBhZDBjMmY2YmYyNmQzMWQ2YTEyZmQxNjg3NmI4OGZiMzEwODIyZGYwNmRjZmE2YjAyZjIzIn0.eyJhdWQiOiIxIiwianRpIjoiZjIyOGFmZTVjYmRlNTBlMTRjNmViMjE5NGMyMGFkMGMyZjZiZjI2ZDMxZDZhMTJmZDE2ODc2Yjg4ZmIzMTA4MjJkZjA2ZGNmYTZiMDJmMjMiLCJpYXQiOjE1NDY5MzkxMTQsIm5iZiI6MTU0NjkzOTExNCwiZXhwIjoxNTc4NDc1MTE0LCJzdWIiOiIxMDA1Iiwic2NvcGVzIjpbXX0.Z6V0i2pkQGqTDf0D2KSLT0CIhAw-1SYIjGcA9rslsSzmF3pmEWaIJfdtKhnDXIT4FgSEg30kO4sXSEssquntL0mpirP1aKQuV3UlA3Fl2WjpbPVnkO6c4tcdQPoQhRHtRbEWCzezUpDykRNC6Id3w1dYZ3lBbSG4w3not1f2X3VVM7tRXSbIHJdvnuqFNPT9stcBz8F3kGWlw-M33FmMGZuFTxsQ5mSxUkGyOAzeDyHoHUsxlMcm9IFPT0Zgz9K0aZyvLrgE1_51NhD-Wl_v5vsv0fQS0OYPKjl4DdD5ZXcaIUWhy-YmyLMgcDw10q7e0NPlZI0FOBxLroDeE7qjejOTISzQ8EpGLjmU_p5MllFR7B6NswrUmKGeEeSGxnqaSgyNq56mxY_ncPANoAtqsS59dOTc-zpcdjb2S1ARdojJLHFNhV3LFl9uKGAjQdPkOnJX6ADbc6o1x_koWo0MmvOnaNImoNKu9DzS1sGdxi0hSLmiZhKFQxXNqnUxISIHohzHRFeL8FoHJCg7SkS_r8ydeN97t8X0eQGMb3hE0r03l6N8EcUqbKnP9y4YmO0xZUCsnesNyfK35we9vY7yoQrzIWd-vBvIpdxA25GOeffF7Vdy9FkfSDOWuCqACoh6hXH2JYrYy-iIR_uAJac5-WTOmXqno2dGB02PeVwhE0E");
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVmMDI5ZDMyYTBjZjZiNTUyNzZiZDc3YzAzY2U3ZTY1NDNmYmUyMTU1ZjQ2NTRjZjZkOGM4MjEyMTc4YzJhZTAzMWFhMDg4ZTNmYmFmYzNlIn0.eyJhdWQiOiIxIiwianRpIjoiNWYwMjlkMzJhMGNmNmI1NTI3NmJkNzdjMDNjZTdlNjU0M2ZiZTIxNTVmNDY1NGNmNmQ4YzgyMTIxNzhjMmFlMDMxYWEwODhlM2ZiYWZjM2UiLCJpYXQiOjE1NDYwNzU4NDMsIm5iZiI6MTU0NjA3NTg0MywiZXhwIjoxNTc3NjExODQzLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.T6UZahyR20a9LRIPGhFDXOUWUG3fnL94OaTtM5nDzQ8CcqdWunO014SGXuMQA58d1AZLZ2K7erxEDB-S4YzmMDkNsLRhO0Avj5POaJrEsijgrIWpklkpD8JfZK5LzSGce6E2YGuLGe16ob5wP5gTjL9BS8AlQzRUk86YkRdVZL-alFJfdUGFg13dnIyFkk8KUbb7HfmvD6qEE5b_U970X50_md4aH1l1U6N6hHJ87K1qPncIGCmFCGBbfOoNO7P514Wgs03CZjQxMn1_BQJ2medZy2t_24Znl1GpbvAra7uqqLx4AjD6T3expaL86A1piy0XsEfA9dxtugyDBJ0Douou2RAgLAolxnFu9okk0f8Bs1mX6jWmBK33GUnUmlARY3Rz3385IbW3MyChZ7J8UM6ayBGQGzyIsynv5AZsMvAyBrlckegcBSDsLWWMXtiJk8rqbViRB6uW9etfD0yrW6Kz7k_wYjFiEfm-9Ss8rzOhgtCpiZZL7AhtBwpHeuHx1DdNBCDp7ev8zI1yFHPAiOQkD-Kjz-bn-2ipZv1XfRbfCfKj4Zm1S5jHfiCuiWY6hfowyfxD5ZfysT99ALwoLpea2rg9CN3ij4n47iuzlPSod9owV0gewqVcD9XiA1EZTYq2UFcmfIV-wJETcgZOV6f9iSzptElWahHP4CC_AEQ");

                                request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                )
                .build();
        return okClient;

    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
