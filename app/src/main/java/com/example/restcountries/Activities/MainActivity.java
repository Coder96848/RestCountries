package com.example.restcountries.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restcountries.Interfaces.OnCountriesNamesListener;
import com.example.restcountries.Models.CountryName;
import com.example.restcountries.R;
import com.example.restcountries.Utils.CountryNamesRequest;
import com.example.restcountries.Utils.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter recyclerAdapter;
    private CountryNamesRequest mCountryNamesRequest;
    private List<String> mNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        mCountryNamesRequest.setListener(new OnCountriesNamesListener() {
            @Override
            public void getResult(List<CountryName> countryNames) {
                if(countryNames !=null) {
                    for (CountryName country : countryNames) {
                        mNamesList.add(country.getName());
                        recyclerAdapter.setItem(mNamesList);
                    }
                }
            }
            @Override
            public void getError(String e) {
                mNamesList.add(e);
            }
        });

        mCountryNamesRequest.getResponse();
    }

    public void Init() {

        mNamesList = new ArrayList<>();
        mCountryNamesRequest = new CountryNamesRequest();

        RecyclerView resultRecyclerView = findViewById(R.id.countriesRecyclerView);
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter = new RecyclerAdapter();
        resultRecyclerView.setAdapter(recyclerAdapter);

    }
}
