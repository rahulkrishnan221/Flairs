package com.jwes.flairs.flairs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerf extends AppCompatActivity {

    private EditText txtEmailAddress;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;
    private Button registerf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerf);
        txtEmailAddress = (EditText) findViewById(R.id.editTextEmailf);
        txtPassword = (EditText) findViewById(R.id.editTextPasswordf);


        firebaseAuth = FirebaseAuth.getInstance();
        registerf=(Button)findViewById(R.id.buttonRegisterf);
        registerf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }
    public void validate()
    {
        if(txtEmailAddress.getText().length()<1)

            Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
        else if(txtPassword.getText().length()<1)
            Toast.makeText(this, "Please enter the valid password", Toast.LENGTH_SHORT).show();

        else
            register();

    }




    public void register() {

        final ProgressDialog progressDialog = ProgressDialog.show(registerf.this, "Please wait...", "Processing...", true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(registerf.this, "Registration successful", Toast.LENGTH_LONG).show();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(registerf.this, "Email sent please verify", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            Intent i = new Intent(registerf.this, loginf.class);
                            startActivity(i);
                        }
                        else
                        {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(registerf.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(registerf.this, option.class));
        finish();

    }

}

