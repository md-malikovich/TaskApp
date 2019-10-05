package com.taskapp.onboard;

import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taskapp.IOnConnectable;
import com.taskapp.R;

public class BoardFragment extends Fragment implements IOnConnectable {

    TextView textTitle;
    ImageView imageView;
    Button btnStart;
    //IOnConnectable connector;

    public BoardFragment() {
        // Required empty public constructor
        //connector = (IOnConnectable) getView();
        //connector.setIt(this);
    }

//    @Override
//    public void setIt(Fragment fragment) {
//        fragment.getView().setBackgroundColor(Color.BLACK);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        imageView = view.findViewById(R.id.imageView);
        textTitle = view.findViewById(R.id.textTitle);
        btnStart = view.findViewById(R.id.btnStart);

        int pos = getArguments().getInt("pos"); //TODO: getArguments().getInt() for Fragment (getIntent for Activity)
        switch (pos) {
            case 0:
                //getView().setBackgroundResource(R.drawable.ic_launcher_background);
                //imageView.setBackgroundResource(R.drawable.ic_launcher_background);
                imageView.setImageResource(R.drawable.image44);
                btnStart.setVisibility(View.INVISIBLE);
                textTitle.setText("Hello!");
                break;
            case 1:
                imageView.setImageResource(R.drawable.image22);
                btnStart.setVisibility(View.INVISIBLE);
                textTitle.setText("How r u?");
                break;
            case 2:
                imageView.setImageResource(R.drawable.image33);
                textTitle.setText("What r u doing?");

                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Вы меня нажимаете", Toast.LENGTH_SHORT).show(); //TODO:
                    }
                });
                break;
        }
        return view;
    }
}