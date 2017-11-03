package com.jwes.flairs.flairs;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
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
    class Retrievepdfstream extends AsyncTask<String, Void,InputStream>
    {


        @Override
        protected InputStream doInBackground(String... strings) {
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
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }
}
