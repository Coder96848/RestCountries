package com.example.restcountries.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.restcountries.Interfaces.OnCountryListener;
import com.example.restcountries.Models.Country;
import com.example.restcountries.Models.Currency;
import com.example.restcountries.R;
import com.example.restcountries.Utils.CountryRequest;

import java.util.List;

public class CountryActivity extends AppCompatActivity {

    private CountryRequest mCountryRequest;
    private TextView nameCountryTextView;
    private TextView capitalCountryTextView;
    private TextView curerenciesNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        init();

        mCountryRequest.setListener(new OnCountryListener() {
            @Override
            public void getResult(List<Country> country) {
                if(country !=null) {
                    for (Country con : country) {
                        nameCountryTextView.setText(con.getName());
                        capitalCountryTextView.setText(con.getCapital());
                        List<Currency> currency = con.getCurrencies();
                        for (Currency cur : currency) {
                            curerenciesNameTextView.setText(cur.getName());
                        }
                    }
                }
            }

            @Override
            public void getError(String e) {
                if(e !=null) {
                    nameCountryTextView.setText(e);
                }
            }

        });

        mCountryRequest.getResponse();
    }

    private void init() {

        mCountryRequest = new CountryRequest();
        mCountryRequest.setCountryName(getIntent().getStringExtra("NAME_COUNTRY"));

        nameCountryTextView = findViewById(R.id.nameCountryTextView);
        capitalCountryTextView = findViewById(R.id.capitalCountryTextView);
        curerenciesNameTextView = findViewById(R.id.curerenciesNameTextView);

    }
}
