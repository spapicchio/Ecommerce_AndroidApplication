package com.example.ecommerceappfinalproject.ui.itemSelectedActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceappfinalproject.ProductDatabase.Conserve;
import com.example.ecommerceappfinalproject.ProductDatabase.Pasta;
import com.example.ecommerceappfinalproject.ProductDatabase.ProductViewModel;
import com.example.ecommerceappfinalproject.ProductDatabase.Special;
import com.example.ecommerceappfinalproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.math.BigDecimal;


public class ItemSelectedAtivity extends AppCompatActivity {

    //Paypal
    private static final int PAYPAL_REQUEST_CODE = 7171;
    private final String PAYPL_CLIENT_ID = "AQfmEviBbK2HotHiyuxJR4BhoPT1cCFBk9IQb56NIy7LfQ0mVPRaI3G3a1Dt_yMOhp_0L7cu8UEs3Amo";
    private final String PAYPAL_CLIENT_SECRET = "EPKeUnaywzFOrU5nLY9twINRSHHBdhK6W6m1jdp_1g1LaDMWr862_bo0ksfMAVcGjvBk7N6F0rAe9aLj";
    private PayPalConfiguration config = new PayPalConfiguration ()
            .environment ( PayPalConfiguration.ENVIRONMENT_SANDBOX )//test mode
            .clientId ( PAYPL_CLIENT_ID );

    private ImageView image;
    private TextView productName, ingredients, provenience, price;
    private Button buttonAddCart;
    private ProductViewModel productViewModel;
    private String type, name;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference, directReferenceUrlFile;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_item_selected_ativity );

        //Toolbar
        toolbar = findViewById ( R.id.activity_item_selected_toolbar );
        setSupportActionBar ( toolbar );
        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );

        //start Paypal service
        Intent intent = new Intent ( this, PayPalService.class );
        intent.putExtra ( PayPalService.EXTRA_PAYPAL_CONFIGURATION, config );
        startService ( intent );

        image = findViewById ( R.id.item_selected_image_activity );
        productName = findViewById ( (R.id.item_selected_product_name_activity) );
        provenience = findViewById ( R.id.item_selected__provenience_activity );
        price = findViewById ( R.id.item_selected_price_activity );
        buttonAddCart = findViewById ( R.id.item_selected_button_add_cart_activity );
        ingredients = findViewById ( R.id.item_selected_ingredients_activity );

        productViewModel = ViewModelProviders.of ( this ).get ( ProductViewModel.class );

        buttonAddCart.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                processPayment ();
            }
        } );

        if (getIntent ().hasExtra ( "type" )) {
            type = getIntent ().getExtras ().getString ( "type" );
            name = getIntent ().getExtras ().getString ( "name" );
            switch (type) {
                case "conserve":
                    productViewModel.selectConserveByName ( name ).observe ( this, new Observer<Conserve> () {
                        @Override
                        public void onChanged(Conserve conserve) {

                            changeConserve ( conserve );
                            setImage ( conserve.getImageUrl () );
                        }
                    } );
                    break;
                case "pasta":
                    productViewModel.selectPastaByName ( name ).observe ( this, new Observer<Pasta> () {
                        @Override
                        public void onChanged(Pasta pasta) {
                            changePasta ( pasta );
                            setImage ( pasta.getImageUrl () );
                        }
                    } );
                    break;
                case "special":
                    productViewModel.selectSpecialByName ( name ).observe ( this, new Observer<Special> () {
                        @Override
                        public void onChanged(Special special) {
                            changeSpecial ( special );
                            setImage ( special.getImageUrl () );
                        }
                    } );
                    break;
            }

        }
    }


    private void changeConserve(Conserve conserve) {
        productName.setText ( conserve.getName () );
        ingredients.setText ( conserve.getIngredients () );
        provenience.setText ( Float.toString ( conserve.getWeight () ) );
        price.setText ( Float.toString ( conserve.getPrice () ) );
        toolbar.setTitle ( conserve.getName () );


    }

    private void changePasta(Pasta pasta) {
        productName.setText ( pasta.getName () );
        ingredients.setText ( pasta.getIngredients () );
        provenience.setText ( pasta.getProvenance () );
        price.setText ( Float.toString ( pasta.getPrice () ) );
        toolbar.setTitle ( pasta.getName () );


    }

    private void changeSpecial(Special special) {
        productName.setText ( special.getName () );
        ingredients.setText ( special.getIngredients () );
        provenience.setText ( special.getProvenance () );
        price.setText ( Float.toString ( special.getPrice () ) );
        toolbar.setTitle ( special.getName () );


    }

    private void setImage(String url) {
        firebaseStorage = FirebaseStorage.getInstance ();
        storageReference = firebaseStorage.getReference ();

        if (url != null) {
            directReferenceUrlFile = storageReference.child ( url );
            directReferenceUrlFile.getDownloadUrl ().addOnSuccessListener ( new OnSuccessListener<Uri> () {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get ()
                            .load ( uri )
                            .error ( R.drawable.loading_vector )
                            .fit ()
                            .centerCrop ()
                            .into ( image );

                }
            } );
        }

    }

    @Override
    public void onDestroy() {
        stopService ( new Intent ( this, PayPalService.class ) );
        super.onDestroy ();
    }

    private void processPayment() {

        PayPalPayment payPalPayment = new PayPalPayment ( new BigDecimal ( String.valueOf ( price.getText ().toString () ) ), "EUR", "payment", PayPalPayment.PAYMENT_INTENT_SALE );

        Intent intent = new Intent ( this, PaymentActivity.class );
        intent.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        intent.putExtra ( PayPalService.EXTRA_PAYPAL_CONFIGURATION, config );
        intent.putExtra ( PaymentActivity.EXTRA_PAYMENT, payPalPayment );
        startActivityForResult ( intent, PAYPAL_REQUEST_CODE );


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );


        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra ( PaymentActivity.EXTRA_RESULT_CONFIRMATION );
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject ().toString ( 4 );
                        startActivity ( new Intent ( this, PaymentDetailsActivity.class )
                                .putExtra ( "PaymentDetailsActivity", paymentDetails )
                                .putExtra ( "paymentAmount", price.getText ().toString () ) );
                        finish ();
                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }
                }

            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText ( this, "Cancel", Toast.LENGTH_SHORT ).show ();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText ( this, "Invalid", Toast.LENGTH_SHORT ).show ();

    }
}
