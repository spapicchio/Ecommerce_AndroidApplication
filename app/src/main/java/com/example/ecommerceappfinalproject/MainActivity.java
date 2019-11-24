package com.example.ecommerceappfinalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        setContentView ( R.layout.activity_main );


        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        mAuth = FirebaseAuth.getInstance ();
        FirebaseUser user = mAuth.getCurrentUser ();


        DrawerLayout drawer = findViewById ( R.id.drawer_layout );
        navigationView = findViewById ( R.id.nav_view );
        if(user != null)
        {
            hideOption ( R.id.nav_login, navigationView.getMenu () );
            hideOption ( R.id.nav_join, navigationView.getMenu () );
        }
        else
            hideOption ( R.id.nav_sign_out, navigationView.getMenu ());


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder (
                R.id.nav_home, R.id.nav_login,
                R.id.nav_pasta_normale, R.id.nav_pasta_integrale,
                R.id.nav_al_naturale, R.id.nav_birre, R.id.nav_join,
                R.id.nav_legumi, R.id.nav_olio, R.id.nav_al_naturale, R.id.nav_sotto_olio )
                .setDrawerLayout ( drawer )
                .build ();
        NavController navController = Navigation.findNavController ( this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController ( this, navController, mAppBarConfiguration );
        NavigationUI.setupWithNavController ( navigationView, navController );

        updateNavHeader ();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController ( this, R.id.nav_host_fragment );
        return NavigationUI.navigateUp ( navController, mAppBarConfiguration )
                || super.onSupportNavigateUp ();
    }

    public void updateNavHeader() {
        View headerView = navigationView.getHeaderView ( 0 );
        CircleImageView imageView = headerView.findViewById ( R.id.user_profile_image );
        TextView name = headerView.findViewById ( R.id.user_profile_name );
        FirebaseUser user = FirebaseAuth.getInstance ().getCurrentUser ();
        if (user != null){
            name.setText ( user.getEmail () );
            Picasso.get ()
                    .load ( user.getPhotoUrl () )
                    .error ( R.drawable.loading_vector )
                    .fit ()
                    .centerCrop ()
                    .into ( imageView );
        }

    }

    private void hideOption(int id, Menu menu)
    {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }


    private void showOption(int id, Menu menu)
    {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }


}
