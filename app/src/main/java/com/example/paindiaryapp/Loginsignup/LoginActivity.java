package com.example.paindiaryapp.Loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.paindiaryapp.MainActivity;
import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

   private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        binding = ActivityLoginBinding.inflate ( getLayoutInflater ( ) );

        View view = binding.getRoot ( );


        setContentView ( view );
        // setContentView ( R.layout.activity_login );

        firebaseAuth=FirebaseAuth.getInstance ();

        binding.btnLogin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String email = binding.etusernamel.getEditText ( ).getText ( ).toString ( ).trim ( );
                String password = binding.etpasswordl.getEditText ( ).getText ( ).toString ( ).trim ( );

                if ( TextUtils.isEmpty ( email ) ) {
                    binding.etusernamel.setError ( "Email cannot be empty" );
                    return;
                }

                if ( TextUtils.isEmpty ( password ) ) {
                    binding.etpasswordl.setError ( "Password is required" );
                    return;
                }

                if ( password.length ( ) < 6 ) {
                    binding.etpasswordl.setError ( "Password must be greater than 6 characters" );
                    return;
                }

                //Log.d ( email,password );

                firebaseAuth.signInWithEmailAndPassword ( email, password ).addOnCompleteListener ( new OnCompleteListener < AuthResult > ( ) {
                    @Override
                    public void onComplete ( @NonNull Task < AuthResult > task ) {
                        if ( task.isSuccessful ( ) ) {
                            Toast.makeText ( LoginActivity.this, "Logged in Successfully", Toast.LENGTH_LONG ).show ( );
                            startActivity ( new Intent ( getApplicationContext ( ), MainActivity.class ) );
                        } else {
                            Toast.makeText ( LoginActivity.this, "Error !" + task.getException ( ).getMessage ( ), Toast.LENGTH_LONG ).show ( );
                        }

                    }
                } );

            }
        } );


        binding.btnNewUser.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( LoginActivity.this, SignupActivity.class );
                startActivity ( intent );
            }
        } );

    }
}

