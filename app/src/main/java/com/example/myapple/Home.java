package com.example.myapple;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity  {
    EditText txt1,txt2;
    TextView tvtxt;
    Button btnlog;
    private FirebaseAuth myAuth;
    ProgressDialog progressDialoglog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressDialoglog=new ProgressDialog(this);
        myAuth=FirebaseAuth.getInstance();
        txt1=findViewById(R.id.etmail);
        txt2=findViewById(R.id.etpass);
        btnlog=findViewById(R.id.blog);
        tvtxt=findViewById(R.id.tvreg);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });

        tvtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    protected void login(){
        String mail,pass;
        mail=txt1.getText().toString();
        pass=txt2.getText().toString();

        if(TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "No email provided", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "No password provided", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialoglog.setMessage("Loging in...");
            progressDialoglog.show();

            myAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Home.this, "Log in succesful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Userz.class));
                                progressDialoglog.cancel();
                            } else {
                                Toast.makeText(Home.this, "Log in error", Toast.LENGTH_SHORT).show();
                                progressDialoglog.cancel();
                            }

                        }
                    });
        }
    }
}
