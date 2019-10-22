package com.example.restcountries.Interfaces;

import com.example.restcountries.Models.Country;

import java.util.List;


public interface OnCountryListener {

    void getResult(List<Country> country);

    void getError(String e);
}
