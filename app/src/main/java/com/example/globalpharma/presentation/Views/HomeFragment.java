package com.example.globalpharma.presentation.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalpharma.R;
import com.example.globalpharma.data.Api_Parameter.Articles;
import com.example.globalpharma.data.Api_Parameter.Headlines;
import com.example.globalpharma.presentation.Controller.Adapter.ActuAdapter;
import com.example.globalpharma.presentation.Controller.Adapter.TaskAdapter;
import com.example.globalpharma.presentation.Controller.MainController;
import com.example.globalpharma.presentation.Model.Task;
import com.example.globalpharma.presentation.Model.actuSante;
import com.example.globalpharma.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String API_KEY="feaf15d95d1e4331b4dd36d0c8c7131c";

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView ActuRecyclerview;
    ActuAdapter mActuAdapter;
    List<actuSante> mData;
    MainController controller;
    List<Articles>  articles= new ArrayList<>();
      private String Categori="health";
    RecyclerView TaskRecyclerview;
    TaskAdapter mTaskAdapter;
    List<Task> mDataTask;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ActuRecyclerview = (RecyclerView)view.findViewById(R.id.actu_rv);
        TaskRecyclerview = (RecyclerView)view.findViewById(R.id.tache_rv);

        ActuRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
       // final String country="fr";
       // retrieveJson(country,Categori,API_KEY);
      //  initRecyclerActu();
        controller = new MainController(
                this,
                Client.getGson(),
                Client.getSharedPreferencesInstances(getContext())
        );
        controller.onStart();
        initRecyclerTask();
        return view;
    }
    public  void retrieveJson(String country,String categori,String apiKey)
    {
        Call<Headlines> call= Client.getInstance().getApi().getHeadlines(country,categori,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles()!=null){
                    articles.clear();
                    articles=response.body().getArticles();
                    mActuAdapter = new ActuAdapter(getActivity(), articles,getFragmentManager());
                    ActuRecyclerview.setAdapter(mActuAdapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

                Toast.makeText(getContext(),"There is An Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initRecyclerTask(){
        mDataTask = new ArrayList<Task>();


        mDataTask.add(new Task("Pharmacies de gardes","Pour la semaine du 25 Juin au 07 Juin",R.drawable.ic_rx));
        mDataTask.add(new Task("Pharmacies à proximité","",R.drawable.ic_rx));
        mDataTask.add(new Task("Pharmacies de gardes","Pour la semaine du 25 Juin au 07 Juin",R.drawable.ic_rx));


        mTaskAdapter = new TaskAdapter(getActivity(), mDataTask,getFragmentManager());
        TaskRecyclerview.setAdapter(mTaskAdapter);
        TaskRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), HORIZONTAL, false));

    }


    public void showList(List<Articles> articlesList) {

        mActuAdapter = new ActuAdapter(getActivity(),articlesList,getFragmentManager());
        ActuRecyclerview.setAdapter(mActuAdapter);
    }

    public void showError() {
        Toast.makeText(getContext(),"API Error", Toast.LENGTH_SHORT).show();
    }
}
