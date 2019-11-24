package com.example.ecommerceappfinalproject.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceappfinalproject.MainActivity;
import com.example.ecommerceappfinalproject.R;
import com.example.ecommerceappfinalproject.RecyclerView.HomeRecyclerViewAdapter;
import com.example.ecommerceappfinalproject.RecyclerView.RecyclerViewItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private FirebaseAuth mAuth;
    CarouselView carouselView;

    int[] sampleImages = {R.drawable.carousel_image_spaghetti, R.drawable.carousel_image_semola, R.drawable.fichihome};
    //Recycler View
    private ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<> ();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeRecyclerViewAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate ( R.layout.fragment_home, container, false );

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //carousel
        carouselView = root.findViewById ( R.id.carouselView );
        carouselView.setPageCount ( sampleImages.length );
        carouselView.setImageListener ( imageListener );

        //set RecyclerView
        mRecyclerView = root.findViewById ( R.id.home_recycler_view );
        mRecyclerView.setHasFixedSize ( true );
        mLayoutManager = new GridLayoutManager ( getActivity (), 2 );
        mAdapter = new HomeRecyclerViewAdapter ( getActivity () );
        mRecyclerView.setLayoutManager ( mLayoutManager );
        mRecyclerView.setAdapter ( mAdapter );

        mAdapter.setItems ( createProducts () );
        mAdapter.setOnItemClickListener ( new HomeRecyclerViewAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(RecyclerViewItem item) {
                createIntent ( item.getId () );
            }
        } );

        //firebase
        mAuth = FirebaseAuth.getInstance ();


        return root;
    }

    ImageListener imageListener = new ImageListener () {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource ( sampleImages[position] );
        }
    };

    @Override
    public void onStart() {
        super.onStart ();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser ();
        if (currentUser == null)
            Toast.makeText ( getActivity (), "no one", Toast.LENGTH_LONG ).show ();
        else
        {
            Toast.makeText ( getActivity (), currentUser.getEmail (), Toast.LENGTH_LONG ).show ();

        }


    }


    private ArrayList<RecyclerViewItem> createProducts() {
        ArrayList<RecyclerViewItem> products = new ArrayList<> ();
        products.add ( new RecyclerViewItem ( "Conserve/confettureMarmellateHome.jpg", "Confetture e marmellate", null, 1 ) );
        products.add ( new RecyclerViewItem ( "Conserve/SottoOlio/SottoOliHome.jpg", "Sotto Olio", null, 2 ) );
        products.add ( new RecyclerViewItem ( "PastaNormale/Pa.jpg", "Pasta", null, 3 ) );
        products.add ( new RecyclerViewItem ( "Legumi 330/LegumiHome.jpg", "Legumi", null, 4 ) );
        return products;
    }

    private void createIntent(int id) {
        Intent intent;
        switch (id) {
            case 1:
                NavHostFragment.findNavController ( this ).navigate ( R.id.nav_marmellate );
                break;
            case 2:
                NavHostFragment.findNavController ( this ).navigate ( R.id.nav_sotto_olio );
                break;
            case 3:
               NavHostFragment.findNavController ( this ).navigate ( R.id.nav_pasta_integrale );
                break;
            case 4:
                NavHostFragment.findNavController ( this ).navigate ( R.id.nav_legumi );
                break;
        }
    }
}