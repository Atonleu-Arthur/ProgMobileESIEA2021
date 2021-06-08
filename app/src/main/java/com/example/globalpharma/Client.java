package com.example.globalpharma;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.globalpharma.data.Interface.Actu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static  final String BASE_URL="https://newsapi.org/v2/";
    private static Client apiClient;
    private    static Retrofit retrofit;
    private static Gson gsonInstance;
    private static Actu news;
    private static SharedPreferences sharedPreferencesInstances;
    private  Client()
    {
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Gson getGson (){
        if (gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }

        return gsonInstance;
    }
    public static synchronized Client getInstance()
    {
        if (apiClient==null)
        {
            apiClient=new Client();
        }
        return apiClient;
    }
    public Actu getApi()
    {
        return retrofit.create(Actu.class);
    }
    public static SharedPreferences getSharedPreferencesInstances(Context context){
        if (sharedPreferencesInstances == null){
            sharedPreferencesInstances = context.getSharedPreferences("application_esa", Context.MODE_PRIVATE);
        }

        return sharedPreferencesInstances;
    }
}
