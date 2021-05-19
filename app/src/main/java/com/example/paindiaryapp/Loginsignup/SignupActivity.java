package com.example.paindiaryapp.Loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.paindiaryapp.MainActivity;
import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        binding = ActivitySignupBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance ();





        binding.btnSignup.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String fullname = binding.etFullname.getEditText ().getText ().toString ().trim ();
                String email = binding.etEmailaddress.getEditText ().getText ().toString ().trim ();
                String password = binding.etPassword.getEditText ().getText ().toString ().trim ();
                String confpassword = binding.etConfPassword.getEditText ().getText ().toString ().trim ();

                if( TextUtils.isEmpty ( fullname ) ){
                    binding.etFullname.setError ( "Name cannot be empty" );
                    return;
                }

                if( TextUtils.isEmpty ( email ) ){
                    binding.etEmailaddress.setError ( "Email cannot be empty" );
                    return;
                }

                if( TextUtils.isEmpty ( password ) ){
                    binding.etPassword.setError ( "Password is required" );
                    return;
                }

                if ( password.length ()< 6 ){
                    binding.etPassword.setError ( "Password must be greater than 6 characters" );
                    return;
                }

                if (TextUtils.isEmpty ( confpassword )  ){
                    binding.etConfPassword.setError ( "Confirm password is required" );
                    return;
                }

                if ( !password.equals ( confpassword ) ){
                    binding.etConfPassword.setError ( "Password doesnot match. Please re-enter" );
                }

                firebaseAuth.createUserWithEmailAndPassword ( email,password ).addOnCompleteListener ( new OnCompleteListener < AuthResult > ( ) {
                    @Override
                    public void onComplete ( @NonNull Task < AuthResult > task ) {
                        if(task.isSuccessful ()){
                            FirebaseAuth.getInstance ().signOut ();
                            Toast.makeText ( getApplicationContext (), "User Created", Toast.LENGTH_LONG ).show ();
                            startActivity ( new Intent (  SignupActivity.this, LoginActivity.class ) );
                        }else {
                            Toast.makeText ( getApplicationContext (), "Error!" + task.getException ().getMessage (), Toast.LENGTH_LONG ).show ();
                            binding.progressBarS.setVisibility ( View.GONE );

                        }
                    }
                } );


            }


        } );



        binding.btnAlreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            } });

    }
}