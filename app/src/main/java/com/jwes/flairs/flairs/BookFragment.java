package com.jwes.flairs.flairs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;



public class BookFragment extends Fragment {

    private BookFragment.OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_book, container, false);
        Button button=(Button)view.findViewById(R.id.pdfb);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               next();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookFragment.OnFragmentInteractionListener) {
            mListener = (BookFragment.OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(context, "Book Fragment", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
public void next()
{
    Intent i = new Intent(getActivity(), pdf.class);
    startActivity(i);
    ((Activity) getActivity()).overridePendingTransition(0,0);
}
}