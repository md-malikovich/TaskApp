package com.taskapp.onboard;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taskapp.R;

public class BoardFragment extends Fragment {

    TextView textTitle;
    ImageView imageView;

    public BoardFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        imageView = view.findViewById(R.id.imageView);
        textTitle = view.findViewById(R.id.textTitle);
        int pos = getArguments().getInt("pos"); //TODO: getArguments().getInt() for Fragment (getIntent for Activity)
        switch (pos) {
            case 0:
                imageView.setImageResource(R.drawable.image22);
                //textTitle.setText("Hello!");
                break;
            case 1:
                imageView.setImageResource(R.drawable.image33);
                //textTitle.setText("How r u?");
                break;
            case 2:
                imageView.setImageResource(R.drawable.image44);
                //textTitle.setText("What r u doing?");
                break;
        }
        return view;
    }
}
