package com.example.myapple;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Userz extends MainActivity {
    TextView tvmail,tvlgt;
    Button btnsave;
    private FirebaseAuth myAuth;

    ProgressDialog progressDialog;
    EditText et1,et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userz);

        progressDialog=new ProgressDialog(this);
        myAuth=FirebaseAuth.getInstance();
        FirebaseUser user=myAuth.getCurrentUser();
        tvmail=findViewById(R.id.tvmail);
        btnsave=findViewById(R.id.bsave);
        tvmail.setText(user.getEmail());

        tvlgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Logging out");
                progressDialog.show();
                myAuth.signOut();
                startActivity(new Intent(getApplicationContext(),Home.class));

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Userz.this, "Descriptions saved", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
