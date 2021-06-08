package com.example.globalpharma.presentation.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.globalpharma.presentation.Model.User;
import com.example.globalpharma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.makeramen.roundedimageview.RoundedImageView.TAG;

public class ProfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
  private TextView btn_singOut;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseFirestore mDb;
    private TextView username;
    private TextView email;

    public ProfilFragment() {
        // Required empty public constructor
    }


    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
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

    Button Btn_modifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_profil, container, false);

        //Btn_modifier = (Button)v.findViewById(R.id.button_modifier);
        username=v.findViewById(R.id.usernameTextView);
        email=v.findViewById(R.id.email_button);
         btn_singOut=v.findViewById(R.id.txtLogOut);
         email=v.findViewById(R.id.email);
        GetUser();
           btn_singOut.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   signOut();
               }
           });
      /*  Btn_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);

            }
        });*/
        // Inflate the layout for this fragment
        return v;
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
    }

    public void GetUser()
    {
     //   User userf= new User();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user =document.toObject(User.class);
                             //   userf.setUser_id(FirebaseAuth.getInstance().getUid());
                                if(user.getUser_id().equals(FirebaseAuth.getInstance().getUid()))
                                {
                                    username.setText(user.getName() + " "+ user.getPrenom());
                                   // email.setText(user.getEmail());
                                }

                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }



                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });;
    }

}
