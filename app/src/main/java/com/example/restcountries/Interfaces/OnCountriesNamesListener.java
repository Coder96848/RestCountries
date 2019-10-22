package com.example.restcountries.Interfaces;

import com.example.restcountries.Models.CountryName;

import java.util.List;

public interface OnCountriesNamesListener {
    void getResult(List<CountryName> countryNames);

    void getError(String e);
}
