package com.example.ecommerceappfinalproject.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceappfinalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.mViewHolder>{
    private ArrayList<RecyclerViewItem> items = new ArrayList<> ();
    private HomeRecyclerViewAdapter.OnItemClickListener listener;
    private Context mContext;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private StorageReference directReferenceUrlFile;

    public HomeRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //bind the card view with the recycler view
    @NonNull
    @Override
    public HomeRecyclerViewAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.home_item_recycler_view, parent, false );
        HomeRecyclerViewAdapter.mViewHolder evh = new HomeRecyclerViewAdapter.mViewHolder ( v );
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final mViewHolder holder, int position) {
        RecyclerViewItem currentItem = items.get ( position );

        holder.textView.setText ( currentItem.getmText1 () );


        firebaseStorage = FirebaseStorage.getInstance ();
        storageReference = firebaseStorage.getReference ();
        String url = currentItem.getImgeUrl ();
        if (url != null){
            directReferenceUrlFile = storageReference.child ( url );
            directReferenceUrlFile.getDownloadUrl ().addOnSuccessListener ( new OnSuccessListener<Uri> () {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get ()
                            .load ( uri )
                            .error ( R.drawable.loading_vector )
                            .fit ()
                            .centerCrop ()
                            .into (holder.imageView);

                }
            } ).addOnFailureListener ( new OnFailureListener () {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            } );
        }

    }


    @Override
    public int getItemCount() {
        return items.size ();
    }

    public void setItems(ArrayList<RecyclerViewItem> items) {
        this.items = items;
    }

    public int getItemRecyclerViewId(int position) {
        return items.get ( position ).getId ();
    }

    public String getItemName(int position) {
        return items.get ( position ).getmText1 ();
    }


    public class mViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;


        public mViewHolder(@NonNull View itemView) {
            super ( itemView );

            imageView = itemView.findViewById ( R.id.home_item_image );
            textView = itemView.findViewById ( R.id.home_item_name );


            itemView.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition ();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick ( items.get ( position ) );
                }
            } );
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerViewItem item);
    }

    public void setOnItemClickListener(HomeRecyclerViewAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
