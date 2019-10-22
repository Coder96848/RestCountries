package com.example.restcountries.Utils;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restcountries.Activities.CountryActivity;
import com.example.restcountries.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private List<String> names = new ArrayList<>();

    public void setItem(List<String> item) {
        this.names = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countries_names_result_list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        holder.Bind(names.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CountryActivity.class);
                intent.putExtra("NAME_COUNTRY", names.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView countriesNameTextView;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            countriesNameTextView = itemView.findViewById(R.id.nameTextView);
        }

        public void Bind(String names) {

            countriesNameTextView.setText(names);

        }
    }
}
