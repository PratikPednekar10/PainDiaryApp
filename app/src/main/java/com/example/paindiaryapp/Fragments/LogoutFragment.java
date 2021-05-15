package com.example.paindiaryapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.paindiaryapp.Loginsignup.LoginActivity;
import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.ActivityMainBinding;
import com.example.paindiaryapp.databinding.FragmentLogoutBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {
    private FragmentLogoutBinding binding;



    public LogoutFragment ( ) {
        // Required empty public constructor
    }


    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }



        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the View for this fragment using the binding
            binding = FragmentLogoutBinding.inflate(inflater, container, false);
            View view = binding.getRoot();

        binding.btnLogout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                AlertDialog.Builder alert = new AlertDialog.Builder ( getContext ( ) );

                alert.setMessage ( "Are you sure you want to Exit?" )
                        .setCancelable ( false )
                        .setPositiveButton ( "Yes", new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick ( DialogInterface dialog, int i ) {
                                FirebaseAuth.getInstance ().signOut ();
                                startActivity ( new Intent ( getContext (), LoginActivity.class ) );
                                finish ( );
                            }
                        } )

                        .setNegativeButton ( "No", new DialogInterface.OnClickListener ( ) {
                            @Override
                            public void onClick ( DialogInterface dialog, int i ) {
                                dialog.cancel ( );
                                //Intent intent = new Intent ( getContext (),HomeFragment.class );
                            }
                        } );

                AlertDialog alertDialog = alert.create ( );
                alertDialog.show ( );

            }

            private void finish ( ) {
                finish ();
            }
        });

            return view;
    }
}


