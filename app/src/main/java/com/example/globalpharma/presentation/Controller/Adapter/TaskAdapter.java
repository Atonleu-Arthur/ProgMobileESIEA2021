package com.example.globalpharma.presentation.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalpharma.presentation.Model.Task;
import com.example.globalpharma.R;
import com.example.globalpharma.presentation.Views.GpsFragment;
import com.example.globalpharma.presentation.Views.Pharmacy;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    Context mContext;
    List<Task> mData;
    FragmentManager fragmentManager;

    public TaskAdapter(Context context, List<Task> data,FragmentManager fragmentManager) {
        mContext = context;
        mData = data;
        this.fragmentManager=fragmentManager;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_tache, viewGroup,false);
        return new TaskViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        holder.txt_titre_task.setText(mData.get(position).getTitre());
        holder.txt_description_task.setText(mData.get(position).getDate());
        holder.image_un_task.setImageResource(mData.get(position).getImageUn());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==1)
                {
                    Pharmacy pharmacy= new Pharmacy();
                   /* fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, find_allnight_pharmacy)
                            .commit();*/
                }
                else if(position==2)
                {
                    GpsFragment gpsFragment= new GpsFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, gpsFragment)
                            .commit();
                }
                else if (position==3){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView txt_titre_task, txt_description_task;
        ImageView image_un_task;
         RelativeLayout relativeLayout;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_titre_task = itemView.findViewById(R.id.txt_titre);
            txt_description_task = itemView.findViewById(R.id.txt_description);
            image_un_task = itemView.findViewById(R.id.image_un);
            relativeLayout=itemView.findViewById(R.id.relativeltask);
        }
    }
}
