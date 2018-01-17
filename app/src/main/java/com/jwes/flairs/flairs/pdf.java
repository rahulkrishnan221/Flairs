package com.jwes.flairs.flairs;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class pdf extends AppCompatActivity {
PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
       pdfView=(PDFView)findViewById(R.id.pdfview);
        new pdf.Retrievepdfstream().execute("https://firebasestorage.googleapis.com/v0/b/flairs-6fa83.appspot.com/o/video%2FDS%20Record%20Final.pdf?alt=media&token=90462032-bd4c-468b-91d0-c44473852786");
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
