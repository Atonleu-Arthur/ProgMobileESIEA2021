package com.example.globalpharma.presentation.Controller;

import android.content.SharedPreferences;

import com.example.globalpharma.util.Constants;
import com.example.globalpharma.data.Api_Parameter.Articles;
import com.example.globalpharma.data.Api_Parameter.Headlines;
import com.example.globalpharma.presentation.Controller.Adapter.ActuAdapter;
import com.example.globalpharma.presentation.Views.HomeFragment;
import com.example.globalpharma.Client;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private HomeFragment view;
    private String API_KEY="feaf15d95d1e4331b4dd36d0c8c7131c";
    final String country="fr";
    final String category="health";
    ActuAdapter newsAdapter;

    List<Articles> articles = new ArrayList<>();

    public MainController(HomeFragment homeFragment, Gson gson, SharedPreferences sharedPreferences) {
        this.view = homeFragment;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<Articles> articles = getDataFromCache();

        if(articles != null){
            view.showList(articles);
        } else {
            //makeApiCall();
            CallApi(country,category,API_KEY);
        }

    }


    private void saveList(List<Articles> articles) {
        String jsonString = gson.toJson(articles);

        sharedPreferences
                .edit()
                .putString(Constants.NEW_LIST, jsonString)
                .apply();
    }

    public  void CallApi(String country,String categori,String apiKey)
    {
        Call<Headlines> call= Client.getInstance().getApi().getHeadlines(country,categori,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles()!=null){
                    articles.clear();
                    articles=response.body().getArticles();

                    saveList(articles);
                    view.showList(articles);;
                }else {
                    view.showError();
                }
            }



            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

                view.showError();
            }
        });
    }

    private List<Articles> getDataFromCache() {
        String jsonNews = sharedPreferences.getString(Constants.NEW_LIST, null);

        if(jsonNews == null){
            return null;
        } else {
            Type listType = new TypeToken<ArrayList<Articles>>() {}.getType();
            return gson.fromJson(jsonNews, listType);
        }
    }
}
