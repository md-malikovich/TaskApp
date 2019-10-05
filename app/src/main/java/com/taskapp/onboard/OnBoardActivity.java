package com.taskapp.onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.taskapp.R;

public class OnBoardActivity extends AppCompatActivity {

    private static final String TAG = OnBoardActivity.class.getName(); //TODO:

    //private MyPagerAdapter fragmentAdapter; //TODO:
    private ViewPager viewPager;
    private TabLayout tabLayout; //TODO:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        initViewPager(); //TODO: запускаем метод
    }

    private void initViewPager() { //TODO: метод для инициализации ViewPager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 0)); //TODO:

        //tabLayout = (TabLayout) findViewById(R.id.tableLayout); //TODO:
        //tabLayout.setupWithViewPager(viewPager); //TODO:
    }

}
