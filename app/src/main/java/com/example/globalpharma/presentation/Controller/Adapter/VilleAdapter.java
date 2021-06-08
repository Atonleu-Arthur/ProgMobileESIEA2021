package com.example.globalpharma.presentation.Controller.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalpharma.presentation.Model.Pharmacy_Location;
import com.example.globalpharma.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class VilleAdapter extends RecyclerView.Adapter<VilleAdapter.VilleViewHolder> {

    Context mContext;
    List<Pharmacy_Location> mData;
   public boolean showshimmer =true;

    public VilleAdapter(Context mContext,List<Pharmacy_Location> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public VilleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout= LayoutInflater.from(mContext).inflate(R.layout.item_allnight_pharmacy,parent,false);
        return new VilleViewHolder(layout);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull VilleViewHolder holder, int position) {
        holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        for (Pharmacy_Location pharmacy_location : mData
        ) {
            if (pharmacy_location.getGeoPoint()!=null)
            {
                if(showshimmer){
                    holder.shimmerFrameLayout.startShimmer();
                }
                else {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setShimmer(null);

                    holder.placename.setBackground(null);
                    holder.placename.setText(mData.get(position).getPharmacy().getPlaceName());

                    holder.Vicinity.setBackground(null);
                    holder.Vicinity.setText(mData.get(position).getPharmacy().getVicinoty());


                }
            }
            else
            {
                if(showshimmer){
                    holder.shimmerFrameLayout.startShimmer();
                }
                else {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setShimmer(null);

                    holder.placename.setBackground(null);
                    holder.placename.setText(mData.get(position).getPharmacy().getPlaceName());

                    holder.Vicinity.setBackground(null);
                    holder.Vicinity.setText(mData.get(position).getPharmacy().getVicinoty());
                    holder.indicator.setTextColor(R.color.colorAccent);
                    holder.indicator.setText(null);
                    holder.indicator.setText("Pas géolocalisé");


                }

            }
        }

    }



    @Override
    public int getItemCount() {
        int SHIMMER_ITEM_NUMBER=0;
return  showshimmer? SHIMMER_ITEM_NUMBER:mData.size();

    }

    public class VilleViewHolder extends RecyclerView.ViewHolder {
        TextView nomVille;
        RelativeLayout container;
        public RelativeLayout relativeLayout;
        public TextView placename;
        public TextView Vicinity;
        public TextView indicator;
        ShimmerFrameLayout shimmerFrameLayout;
        public VilleViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout=itemView.findViewById(R.id.shimmerlayout);
            container=itemView.findViewById(R.id.container);
            nomVille=itemView.findViewById(R.id.NomVille);
            relativeLayout=itemView.findViewById(R.id.relativelayout);
            placename=itemView.findViewById(R.id.placeName);
            Vicinity=itemView.findViewById(R.id.Vicinity);
            indicator=itemView.findViewById(R.id.indicateMaps);

        }
    }
}
