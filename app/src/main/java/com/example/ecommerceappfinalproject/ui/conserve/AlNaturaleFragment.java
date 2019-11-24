package com.example.ecommerceappfinalproject.ui.conserve;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecommerceappfinalproject.ProductDatabase.Conserve;
import com.example.ecommerceappfinalproject.ProductDatabase.ProductViewModel;
import com.example.ecommerceappfinalproject.R;
import com.example.ecommerceappfinalproject.RecyclerView.ProductRecyclerViewAdapter;
import com.example.ecommerceappfinalproject.RecyclerView.RecyclerViewItem;
import com.example.ecommerceappfinalproject.ui.itemSelectedActivity.ItemSelectedAtivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AlNaturaleFragment extends Fragment {

    /*======== Attributes ========*/

    //viewModel

    private ProductViewModel productViewModel;

    //Recycler View
    private ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<> ();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductRecyclerViewAdapter mAdapter;

    //firebase store
    private StorageReference mStorageRef;

    /*======== Methods ========*/

    public static AlNaturaleFragment newInstance() {
        return new AlNaturaleFragment ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        productViewModel = ViewModelProviders.of ( this ).get ( ProductViewModel.class );
        return inflater.inflate ( R.layout.fragment_al_naturale, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );

        //set database
        mStorageRef = FirebaseStorage.getInstance ().getReference ();

        //set RecyclerView
        mRecyclerView = view.findViewById ( R.id.al_naturale_recycler_view );
        mRecyclerView.setHasFixedSize ( true );
        mLayoutManager = new LinearLayoutManager ( getActivity () );
        mAdapter = new ProductRecyclerViewAdapter ( getActivity () );
        mRecyclerView.setLayoutManager ( mLayoutManager );
        mRecyclerView.setAdapter ( mAdapter );

        productViewModel.selectTypeConserve ( "alNaturale" ).observe ( this, new Observer<List<Conserve>> () {
            @Override
            public void onChanged(List<Conserve> conserves) {
                mAdapter.setItems ( createProducts ( conserves ) );
                mAdapter.notifyDataSetChanged ();
            }
        } );

        mAdapter.setOnItemClickListener ( new ProductRecyclerViewAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(RecyclerViewItem item) {
                Intent intent = new Intent ( getActivity (), ItemSelectedAtivity.class );
                intent.putExtra ( "type", "conserve" );
                intent.putExtra ( "name", item.getmText1 () );
                Toast.makeText ( getActivity (), item.getmText1 (), Toast.LENGTH_SHORT ).show ();
                getActivity ().startActivity ( intent );
            }
        } );
    }


    private ArrayList<RecyclerViewItem> createProducts(List<Conserve> conserves) {
        ArrayList<RecyclerViewItem> products = new ArrayList<> ();
        for (int i = 0; i < conserves.size (); i++)
            products.add ( new RecyclerViewItem ( conserves.get ( i ).getImageUrl (), conserves.get ( i ).getName (), conserves.get ( i ).getIngredients (), conserves.get ( i ).getId () ) );
        return products;
    }

}
