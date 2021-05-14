package com.example.paindiaryapp.Loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paindiaryapp.MainActivity;
import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

        if ( firebaseAuth.getCurrentUser () != null ){
            startActivity ( new Intent ( getApplicationContext (), MainActivity.class ) );
            finish ();
        }

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

                binding.progressBar.setVisibility ( View.VISIBLE );

                //Log.d ( email,password );

                firebaseAuth.signInWithEmailAndPassword ( email, password ).addOnCompleteListener ( new OnCompleteListener < AuthResult > ( ) {
                    @Override
                    public void onComplete ( @NonNull Task < AuthResult > task ) {
                        if ( task.isSuccessful ( ) ) {
                            startActivity ( new Intent ( getApplicationContext ( ), MainActivity.class ) );
                        } else {
                            Toast.makeText ( getApplicationContext (), "Error !" + task.getException ( ).getMessage ( ), Toast.LENGTH_LONG ).show ( );
                            binding.progressBar.setVisibility ( View.GONE );
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


        binding.btnforgetpass.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                EditText resetMail = new EditText ( v.getContext () );
                AlertDialog.Builder passwordReserDialog = new AlertDialog.Builder ( v.getContext () );
                passwordReserDialog.setTitle ( "Reset Password?" );
                passwordReserDialog.setMessage ( "Enter your Email to receive reset link " );
                passwordReserDialog.setView ( resetMail );

                passwordReserDialog.setPositiveButton ( "Yes", new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick ( DialogInterface dialog, int which ) {
                        //Extract the email and send reset link

                        String mail = resetMail.getText ().toString ();
                        firebaseAuth.sendPasswordResetEmail ( mail ).addOnSuccessListener ( new OnSuccessListener < Void > ( ) {
                            @Override
                            public void onSuccess ( Void aVoid ) {
                                Toast.makeText ( LoginActivity.this, "Reset Link sent to your Email",Toast.LENGTH_SHORT ).show ();
                            }
                        } ).addOnFailureListener ( new OnFailureListener ( ) {
                            @Override
                            public void onFailure ( @NonNull Exception e ) {
                                Toast.makeText ( LoginActivity.this,"Error! Reset Link is Not Sent"+ e.getMessage (),Toast.LENGTH_SHORT ).show ();
                            }
                        } );
                    }
                } );

                passwordReserDialog.setNegativeButton ( "No", new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick ( DialogInterface dialog, int which ) {
                        //close the dialog
                    }
                } );

                passwordReserDialog.create ().show ();
            }
        } );

    }
}

