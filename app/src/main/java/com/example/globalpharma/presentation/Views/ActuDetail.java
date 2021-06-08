package com.example.globalpharma.presentation.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.globalpharma.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActuDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActuDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tvTitle,tvSource,tvTime;
    ImageView imageView;
    WebView webView;
    ImageView btn_back;

    public ActuDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActuDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static ActuDetail newInstance(String param1, String param2) {
        ActuDetail fragment = new ActuDetail();
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
    public void Back()
    {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment fragment=new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_actu_detail, container, false);
        tvTitle=v.findViewById(R.id.tvId);
        tvSource=v.findViewById(R.id.tvSource);
        tvTime=v.findViewById(R.id.tvDate);
       // tvDesc=v.findViewById(R.id.tvDesc);
        webView=v.findViewById(R.id.webView1);
        imageView=v.findViewById(R.id.image);
         btn_back=v.findViewById(R.id.btn_retour_actu);
        Bundle args = getArguments();
        String title=args.getString("title");
        String source=args.getString("source");
        String time=args.getString("time");
        String imageUrl=args.getString("imageUrl");
        String url=args.getString("url");


        tvTitle.setText(title);
        tvSource.setText(source);
        tvTime.setText(time);
        Picasso.get().load(imageUrl).into(imageView);
        webView.getSettings().setDomStorageEnabled(true);
        Picasso.get().load(imageUrl).into(imageView);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        Back();
        return v;
    }
}
