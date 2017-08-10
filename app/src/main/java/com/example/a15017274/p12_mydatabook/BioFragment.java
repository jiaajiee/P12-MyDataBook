package com.example.a15017274.p12_mydatabook;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {

    Button btnEdit;
    EditText et;
    TextView tv;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference bio;


    public BioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.biofragment, container, false);

        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        et = (EditText) view.findViewById(R.id.et);
        tv = (TextView) view.findViewById(R.id.textView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        bio = firebaseDatabase.getReference("bio");

        bio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bio = dataSnapshot.getValue(String.class);
                tv.setText(bio);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", "Database error occurred", databaseError.toException());
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout passPhrase =
                        (LinearLayout) inflater.inflate(R.layout.passphrase, null);
                et = (EditText) passPhrase
                        .findViewById(R.id.et);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Bio")
                        .setView(passPhrase)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String text = et.getText().toString();
                                bio.setValue(text);
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }

}
