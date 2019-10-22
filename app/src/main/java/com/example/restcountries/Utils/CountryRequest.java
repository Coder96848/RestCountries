package com.example.restcountries.Utils;

import com.example.restcountries.Interfaces.APIService;
import com.example.restcountries.Interfaces.OnCountryListener;
import com.example.restcountries.Models.Country;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryRequest {
    private String url = "https://restcountries.eu/";

    private String countryName;
    private APIService mApi;
    private Retrofit mRetrofit;
    private OnCountryListener mListener;

    public void setCountryName(String countryName) {

        char[] chars = countryName.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
            }
        }

        this.countryName = new String(chars);
    }

    public void setListener(OnCountryListener listener) {
        this.mListener = listener;
    }

    public void getResponse(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Accept", "application/json; charset=utf-8")
                        .addHeader("Accept-Encoding", "identity")
                        .build();
                return chain.proceed(request);
            }
        });

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(httpClient.build())
                .build();

        mApi = mRetrofit.create(APIService.class);

        Call<List<Country>> call = mApi.countryResponse(countryName, true);

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, retrofit2.Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    if(mListener!=null) {
                        mListener.getResult(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                if(mListener!=null) {
                    mListener.getError(t.getMessage());
                }
            }
        });
    }
}
