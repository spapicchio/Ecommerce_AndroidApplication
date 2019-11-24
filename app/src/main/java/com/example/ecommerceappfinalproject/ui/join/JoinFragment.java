package com.example.ecommerceappfinalproject.ui.join;

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
import androidx.navigation.fragment.NavHostFragment;

import com.example.ecommerceappfinalproject.MainActivity;
import com.example.ecommerceappfinalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class JoinFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "JoinFragment.java";


    private NavController navigation;
    private EditText inputEmail, inputPassword;
    private Button joinButton;
    private FirebaseAuth mAuth;
    private ImageView google, facebook, twitter;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //set Navigation
        navigation = NavHostFragment.findNavController (this);

        //inflate layout
        View root = inflater.inflate(R.layout.fragment_join, container, false);

        //set Firebase
        mAuth = FirebaseAuth.getInstance();

        //set Views
        inputEmail = root.findViewById(R.id.join_email);
        inputPassword = root.findViewById(R.id.join_pswd);
        joinButton = root.findViewById(R.id.join_button);

        joinButton.setOnClickListener(this);
        facebook.setOnClickListener (this);
        twitter.setOnClickListener (this);
        google.setOnClickListener (this);

        return root;
    }



    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.join_button:
                createUserWithEmailAndPassword();
                break;

        }
    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();

    }



    public void createUserWithEmailAndPassword(){

        String email = inputEmail.getText().toString();
        String pswd = inputPassword.getText().toString();
        if (TextUtils.isEmpty(email))
        {
            inputEmail.setError("Insert your Email");
            inputEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(pswd))
        {
            inputPassword.setError("Insert your password");
            inputPassword.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, pswd)
                    .addOnCompleteListener( getActivity (), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getContext(), "Account created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent ( getActivity (), MainActivity.class );
                                startActivity ( intent );
                                getActivity ().finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

}
