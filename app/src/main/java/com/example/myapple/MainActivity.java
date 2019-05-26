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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText txt1,txt2;
    Button btnreg;
    private FirebaseAuth myAuth;
    ProgressDialog progressDialoglog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialoglog=new ProgressDialog(this);
        myAuth=FirebaseAuth.getInstance();
        txt1=findViewById(R.id.etmail);
        txt2=findViewById(R.id.etpass);

        btnreg=findViewById(R.id.breg);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    protected void register(){
        String mail,assword;
        mail=txt1.getText().toString();
        assword=txt2.getText().toString();
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this, "No email specified", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(assword)){
            Toast.makeText(this, "No password specified", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            progressDialoglog.setMessage("Registering");
            progressDialoglog.show();

            myAuth.createUserWithEmailAndPassword(mail, assword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Successful registry", Toast.LENGTH_SHORT).show();
                                progressDialoglog.cancel();
                                startActivity(new Intent(getApplicationContext(),Userz.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                                progressDialoglog.cancel();
                            }

                        }
                    });


        }
    }


}
