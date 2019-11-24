package com.example.ecommerceappfinalproject.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ecommerceappfinalproject.MainActivity;
import com.example.ecommerceappfinalproject.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "LoginFragments.java";

    private NavController navigation;
    private EditText inputEmail, inputPassword;
    private Button loginButton;
    private FirebaseAuth mAuth;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //set Navigation
        navigation = NavHostFragment.findNavController ( this );
        //set Firebase
        mAuth = FirebaseAuth.getInstance ();

        //inflate the layout
        View root = inflater.inflate ( R.layout.fragment_login, container, false );

        //set Views
        inputEmail = root.findViewById ( R.id.login_email );
        inputPassword = root.findViewById ( R.id.login_password );
        loginButton = root.findViewById ( R.id.login_button );

        //set OnCliclListener
        loginButton.setOnClickListener ( this );
        inputPassword.setOnClickListener ( this );
        loginButton.setOnClickListener ( this );

        return root;
    }

    @Override
    public void onStart() {
        super.onStart ();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser ();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId ();
        switch (id) {
            case R.id.login_button:
                signInWithEmailAndPassword ();
                break;
        }
    }

    public void signInWithEmailAndPassword() {

        String email = inputEmail.getText ().toString ();
        String pswd = inputPassword.getText ().toString ();
        if (TextUtils.isEmpty ( email )) {
            inputEmail.setError ( "Insert your Email" );
            inputEmail.requestFocus ();
        } else if (TextUtils.isEmpty ( pswd )) {
            inputPassword.setError ( "Insert your password" );
            inputPassword.requestFocus ();
        } else {
            mAuth.signInWithEmailAndPassword ( email, pswd )
                    .addOnCompleteListener ( getActivity (), new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful ()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser ();
                                Toast.makeText ( getContext (), "Sign In success.", Toast.LENGTH_SHORT ).show ();
                                Intent intent = new Intent ( getActivity (), MainActivity.class );
                                startActivity ( intent );
                                getActivity ().finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w ( TAG, "signIn:failure", task.getException () );
                                Toast.makeText ( getContext (), "Sign infailed.", Toast.LENGTH_SHORT ).show ();

                            }
                        }
                    } );
        }


    }

}