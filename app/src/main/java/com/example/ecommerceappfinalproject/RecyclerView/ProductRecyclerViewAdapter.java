package com.example.ecommerceappfinalproject.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ecommerceappfinalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.mViewHolder> {

    private static ArrayList<RecyclerViewItem> items = new ArrayList<> ();
    private static OnItemClickListener listener;
    private Context mContext;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private StorageReference directReferenceUrlFile;

    public ProductRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //bind the card view with the recycler view
    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.product_item_recycler_view, parent, false );
        mViewHolder evh = new mViewHolder ( v );
        return evh;
    }

    //gets data from the array list and put them in the view holder
    @Override
    public void onBindViewHolder(@NonNull final mViewHolder holder, int position) {
        RecyclerViewItem currentItem = items.get ( position );

        holder.textView1.setText ( currentItem.getmText1 () );
        holder.textView2.append ( currentItem.getmText2 () );

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
            } ).addOnFailureListener ( new OnFailureListener () {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            } );
        }


    }

    public void downloadFiles(Context context, String fileExtension, String destination){

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


    public static class mViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView1, textView2;


        public mViewHolder(@NonNull View itemView) {
            super ( itemView );

            imageView = itemView.findViewById ( R.id.product_item_image );
            textView1 = itemView.findViewById ( R.id.product_item_name );
            textView2 = itemView.findViewById ( R.id.product_item_description );

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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
