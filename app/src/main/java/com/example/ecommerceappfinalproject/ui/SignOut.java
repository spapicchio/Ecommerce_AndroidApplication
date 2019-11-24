package com.example.ecommerceappfinalproject.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommerceappfinalproject.MainActivity;
import com.example.ecommerceappfinalproject.R;
import com.google.firebase.auth.FirebaseAuth;


public class SignOut extends Fragment {

    private String mParam1;
    private String mParam2;


    public SignOut() {
        // Required empty public constructor
    }


    public static SignOut newInstance(String param1, String param2) {
        return new SignOut ();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        FirebaseAuth mAuth = FirebaseAuth.getInstance ();
        mAuth.signOut ();
        startActivity ( new Intent ( getActivity (), MainActivity.class ) );
        getActivity ().finish ();
    }


}
