package com.application.newsfeed.Fragments;

import static com.application.newsfeed.Constants.Constant.API_KEY;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsfeed.Pojo.Model;
import com.application.newsfeed.Pojo.NewsPojo;
import com.application.newsfeed.R;
import com.application.newsfeed.RecyclerAdapter.Adapter;
import com.application.newsfeed.Utils.ApiUtilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {

    ArrayList<Model> newsContentList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewOfEntertainment;
    private String Category = "entertainment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.entertainment_fragment, null);
        recyclerViewOfEntertainment = view.findViewById(R.id.recyclerviewofentertainment);
        newsContentList = new ArrayList<>();
        recyclerViewOfEntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), newsContentList);
        recyclerViewOfEntertainment.setAdapter(adapter);

        findNews();
        return view;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getNewsByCategory(country, Category, 100, API_KEY).enqueue(new Callback<NewsPojo>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<NewsPojo> call, @NonNull Response<NewsPojo> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    newsContentList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsPojo> call, @NonNull Throwable t) {

            }
        });
    }
}
