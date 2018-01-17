package com.jwes.flairs.flairs;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.jwes.flairs.flairs.recycle.preference4;
import static com.jwes.flairs.flairs.recycle.saveit4;
import static com.jwes.flairs.flairs.subject_choice.preference3;

public class pdf extends AppCompatActivity {
PDFView pdfView;
    private FirebaseDatabase database;
    DatabaseReference myRef;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        database=FirebaseDatabase.getInstance();
        SharedPreferences sf4=getSharedPreferences(preference4, Context.MODE_PRIVATE);
        String pdf_link = sf4.getString(saveit4,"");
        myRef=FirebaseDatabase.getInstance().getReference().child(pdf_link);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                value=dataSnapshot.getValue(String.class);
                Toast.makeText(pdf.this, value, Toast.LENGTH_SHORT).show();
                new pdf.Retrievepdfstream().execute(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_pdf);
        pdfView=(PDFView)findViewById(R.id.pdfview);


    }
    class Retrievepdfstream extends AsyncTask<String, Void,byte[]>
    {
ProgressDialog loading;

        @Override
        protected byte[] doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200)
                {
                    inputStream=new BufferedInputStream(urlConnection.getInputStream());
                }


            } catch (Exception e) {
                return null;
            }
            try {
                return IOUtils.toByteArray(inputStream);
            }
            catch (IOException e)
            {
             e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            pdfView.fromBytes(bytes).load();
            loading.dismiss();
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            loading=ProgressDialog.show(pdf.this,"Please wait","Hold on......",true,true);
        }




    }
}
