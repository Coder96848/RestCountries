package com.example.restcountries.Interfaces;

import com.example.restcountries.Models.Country;
import com.example.restcountries.Models.CountryName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("rest/v2/all")
    Call<List<CountryName>> countryNamesResponse(@Query("fields") String fields);

    @GET("rest/v2/name/{countryname}")
    Call<List<Country>> countryResponse(@Path("countryname") String countryName,
                                  @Query("fullText") boolean isFullText);
}


