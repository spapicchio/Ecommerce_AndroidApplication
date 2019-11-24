package com.example.ecommerceappfinalproject.ui.pasta;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceappfinalproject.ProductDatabase.Pasta;
import com.example.ecommerceappfinalproject.ProductDatabase.ProductViewModel;
import com.example.ecommerceappfinalproject.R;
import com.example.ecommerceappfinalproject.RecyclerView.ProductRecyclerViewAdapter;
import com.example.ecommerceappfinalproject.RecyclerView.RecyclerViewItem;
import com.example.ecommerceappfinalproject.ui.itemSelectedActivity.ItemSelectedAtivity;

import java.util.ArrayList;
import java.util.List;

public class PastaIntegraleFragment extends Fragment {

    private ProductViewModel productViewModel;

    //Recycler View
    private ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<> ();
    private RecyclerView mRecyclerView;
    private ProductRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PastaIntegraleFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        productViewModel = ViewModelProviders.of ( this ).get ( ProductViewModel.class );
        View root = inflater.inflate ( R.layout.fragment_pasta_integrale, container, false );
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );
        //set Recycler view
        mRecyclerView = view.findViewById ( R.id.pasta_integrale_recycler_view );
        mRecyclerView.setHasFixedSize ( true );
        mLayoutManager = new LinearLayoutManager ( getActivity () );
        mAdapter = new ProductRecyclerViewAdapter ( getActivity () );
        mRecyclerView.setLayoutManager ( mLayoutManager );
        mRecyclerView.setAdapter ( mAdapter );


        productViewModel.selectTypePasta ( true ).observe ( this, new Observer<List<Pasta>> () {
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
                intent.putExtra ( "type", "pasta" );
                intent.putExtra ( "name", item.getmText1 () );
                Toast.makeText ( getActivity (), item.getmText1 (), Toast.LENGTH_SHORT ).show ();
                getActivity ().startActivity ( intent );
            }
        } );
    }


    private ArrayList<RecyclerViewItem> createProducts(List<Pasta> pastas) {
        ArrayList<RecyclerViewItem> products = new ArrayList<> ();
        for (int i = 0; i < pastas.size (); i++)
            products.add ( new RecyclerViewItem ( pastas.get ( i ).getImageUrl (), pastas.get ( i ).getName (), pastas.get ( i ).getIngredients (), pastas.get ( i ).getId () ) );
        return products;
    }
}