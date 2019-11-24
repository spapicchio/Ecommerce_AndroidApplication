package com.example.ecommerceappfinalproject.ui.pasta;

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

import com.example.ecommerceappfinalproject.ProductDatabase.Pasta;
import com.example.ecommerceappfinalproject.ProductDatabase.ProductViewModel;
import com.example.ecommerceappfinalproject.R;
import com.example.ecommerceappfinalproject.RecyclerView.ProductRecyclerViewAdapter;
import com.example.ecommerceappfinalproject.RecyclerView.RecyclerViewItem;
import com.example.ecommerceappfinalproject.ui.itemSelectedActivity.ItemSelectedAtivity;

import java.util.ArrayList;
import java.util.List;

public class PastaNormalFragment extends Fragment {

    /*======== Attributes ========*/
    //viewModel
    private ProductViewModel productViewModel;

    //Recycler View
    private ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<> ( );
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductRecyclerViewAdapter mAdapter ;


    /*======== Methods ========*/
    public static PastaNormalFragment newInstance() {
        return new PastaNormalFragment ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        productViewModel = ViewModelProviders.of ( this ).get ( ProductViewModel.class );
        return inflater.inflate ( R.layout.fragment_pasta_normal, container, false );
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );


        //set RecyclerView
        mRecyclerView = view.findViewById ( R.id.pasta_normal_recycler_view );
        mRecyclerView.setHasFixedSize (true);
        mLayoutManager = new LinearLayoutManager ( getActivity () );
        mAdapter = new ProductRecyclerViewAdapter ( getActivity ());
        mRecyclerView.setLayoutManager ( mLayoutManager );
        mRecyclerView.setAdapter ( mAdapter );

        productViewModel.selectTypePasta ( false ).observe ( this, new Observer<List<Pasta>> () {
            @Override
            public void onChanged(List<Pasta> pastas) {
                mAdapter.setItems ( createProducts ( pastas ) );
                mAdapter.notifyDataSetChanged ();
            }
        } );
        mAdapter.setOnItemClickListener ( new ProductRecyclerViewAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(RecyclerViewItem item) {
                Intent intent = new Intent ( getActivity (), ItemSelectedAtivity.class );
                intent.putExtra ("type", "pasta" );
                intent.putExtra ( "name", item.getmText1 () );
                Toast.makeText ( getActivity (), item.getmText1 (), Toast.LENGTH_SHORT ).show ();
                getActivity ().startActivity ( intent );
            }
        } );


    }



    private ArrayList<RecyclerViewItem> createProducts(List<Pasta> pastas) {
        ArrayList<RecyclerViewItem> products = new ArrayList<>();
        for ( int i = 0; i<pastas.size ();i++)
            products.add ( new RecyclerViewItem ( pastas.get ( i ).getImageUrl (), pastas.get ( i ).getName (), pastas.get ( i ).getIngredients (), pastas.get ( i ).getId () ) );
        return products;
    }


}
