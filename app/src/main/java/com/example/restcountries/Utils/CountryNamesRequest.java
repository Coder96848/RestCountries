package com.example.restcountries.Utils;

import com.example.restcountries.Interfaces.APIService;
import com.example.restcountries.Interfaces.OnCountriesNamesListener;
import com.example.restcountries.Models.CountryName;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryNamesRequest {

    private String url = "https://restcountries.eu/";
    private APIService mApi;
    private Retrofit mRetrofit;
    private OnCountriesNamesListener mListener;

    public void setListener(OnCountriesNamesListener listener) {
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

        Call<List<CountryName>> call = mApi.countryNamesResponse("name");

        call.enqueue(new Callback<List<CountryName>>() {
            @Override
            public void onResponse(Call<List<CountryName>> call, retrofit2.Response<List<CountryName>> response) {
                if (response.isSuccessful()) {
                    if(mListener!=null) {
                        mListener.getResult(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CountryName>> call, Throwable t) {
                if(mListener!=null) {
                    mListener.getError(t.getMessage());
                }
            }
        });
    }
}
