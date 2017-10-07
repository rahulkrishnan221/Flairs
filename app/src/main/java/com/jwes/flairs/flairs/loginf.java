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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginf extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;
    private LinearLayout forgot;
    private Button loginf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginf);
        txtEmailLogin = (EditText) findViewById(R.id.emailf);
        txtPwd = (EditText) findViewById(R.id.passwordf);
        firebaseAuth = FirebaseAuth.getInstance();
        forgot=(LinearLayout) findViewById(R.id.forgot);
        loginf=(Button)findViewById(R.id.loginf) ;
        loginf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(loginf.this ,forgot.class);
                startActivity(x);

            }
        });
    }

    public void validate()
    {
        if(txtEmailLogin.getText().length()<1)
            Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
        else if(txtPwd.getText().length()<1)
            Toast.makeText(this, "Please enter the valid password", Toast.LENGTH_SHORT).show();
        else
            login();
    }





    public void login() {
        final ProgressDialog progressDialog = ProgressDialog.show(loginf.this, "Please wait...", "Proccessing...", true);

        (firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPwd.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(loginf.this, "Login successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(loginf.this, MainActivity.class);
                            i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(loginf.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(loginf.this, option.class));
        finish();

    }

}
