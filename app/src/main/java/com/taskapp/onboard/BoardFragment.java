package com.taskapp.onboard;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taskapp.MainActivity;
import com.taskapp.R;

import static android.content.Context.MODE_PRIVATE;

public class BoardFragment extends Fragment {

    TextView textTitle;
    ImageView imageView;
    Button btnStart;

    public BoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        imageView = view.findViewById(R.id.imageView);
        textTitle = view.findViewById(R.id.textTitle);
        btnStart = view.findViewById(R.id.btnStart);

        int pos = getArguments().getInt("pos"); //TODO: getArguments().getInt() for Fragment (getIntent for Activity)
        switch (pos) {
            case 0:
                view.setBackgroundResource(R.drawable.image11);
                imageView.setImageResource(R.drawable.bg5);
                textTitle.setText("Hello!");
                break;
            case 1:
                view.setBackgroundResource(R.drawable.bg2);
                imageView.setImageResource(R.drawable.bg10);
                textTitle.setText("How r u?");
                break;
            case 2:
                view.setBackgroundResource(R.drawable.bgwhite);
                imageView.setImageResource(R.drawable.bg9);
                btnStart.setVisibility(View.VISIBLE);
                textTitle.setText("What r u doing?");
                break;
        }
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Вы меня нажимаете", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getActivity().getSharedPreferences("settings", MODE_PRIVATE);
                preferences.edit().putBoolean("isShown", true).apply();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}