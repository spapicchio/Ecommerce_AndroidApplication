package com.example.ecommerceappfinalproject.ui.itemSelectedActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ecommerceappfinalproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetailsActivity extends AppCompatActivity {

    TextView txtId, txtAmount, txtStatus;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_payment_details );

        //Toolbar
        toolbar = findViewById(R.id.activity_payment_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle ( "Payment info" );

        txtId = findViewById ( R.id.activity_payment_details_id );
        txtAmount = findViewById ( R.id.activity_payment_details_amount );
        txtStatus = findViewById ( R.id.activity_payment_details_status );

        Intent intent= getIntent ();

        try{
            JSONObject jsonObject = new JSONObject ( intent.getStringExtra ( "PaymentDetailsActivity" ) );
            showDetails(jsonObject.getJSONObject ( "response" ), intent.getStringExtra ( "paymentAmount" ));
        }catch (JSONException e){
            e.printStackTrace ();
        }
    }

    private void showDetails(JSONObject response,String paymentAmount){
        try{
            txtId.append ( response.getString ( "id" ) );
            txtAmount.append ("$"+paymentAmount);
            txtStatus.append ( response.getString ( "state" ) );
        }catch(JSONException e){
            e.printStackTrace ();
        }

    }

}
