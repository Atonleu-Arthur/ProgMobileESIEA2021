package com.example.globalpharma.presentation.Controller.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalpharma.data.Api_Parameter.Articles;
import com.example.globalpharma.presentation.Model.actuSante;
import com.example.globalpharma.R;
import com.example.globalpharma.presentation.Views.ActuDetail;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class ActuAdapter extends RecyclerView.Adapter<ActuAdapter.ActuViewHolder>{

    Context mContext;
    List<actuSante> mData;
    List<Articles> articles;
    public FragmentManager fragmentManager;
    //in your constructor add FragmentManager

    public ActuAdapter(Context context, List<Articles> articles, FragmentManager f_manager) {
        mContext = context;
        this.articles = articles;
        this.fragmentManager=f_manager;
    }



    @NonNull
    @Override
    public ActuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_actu, viewGroup,false);
        return new ActuViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ActuViewHolder holder, int position) {

        final Articles a=articles.get(position);
        String url =a.getUrl();
        holder.txt_titre.setText(a.getTitle());
        holder.txt_description.setText(a.getPublishedAt());
        String imageUrl=a.getUrlToImage();
        Picasso.get().load(imageUrl).into(holder.image_actu);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString(url,a.getUrl());
                ActuDetail detail = new ActuDetail();
                detail.setArguments(bundle);
                bundle.putString("title",a.getTitle());
                bundle.putString("source",a.getSource().getName());
                bundle.putString("time",a.getPublishedAt());
                bundle.putString("imageUrl",a.getUrlToImage());
                bundle.putString("url",a.getUrl());
                bundle.putString("decs",a.getDescription());
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, detail)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ActuViewHolder extends RecyclerView.ViewHolder{

        TextView txt_titre, txt_description;
        ImageView image_actu;
RelativeLayout relativeLayout;

        public ActuViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_titre = itemView.findViewById(R.id.newsTitle);
            txt_description = itemView.findViewById(R.id.tvDate);
            image_actu = itemView.findViewById(R.id.image);
            relativeLayout=itemView.findViewById(R.id.actuRelativ);





        }
    }

    public String getCountry()
    {
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return  country.toLowerCase();

    }
}
