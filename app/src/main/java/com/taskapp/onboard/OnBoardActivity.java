package com.taskapp.onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.material.tabs.TabLayout;
import com.taskapp.MainActivity;
import com.taskapp.R;

public class OnBoardActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnSkip;
    private TabLayout tabLayout; //TODO:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        getSupportActionBar().hide(); //TODO: ActionBar setInvisible

        initViewPager(); //TODO: запускаем метод
    }

    private void initViewPager() { //TODO: метод для инициализации ViewPager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 0)); //TODO:

        tabLayout = findViewById(R.id.tableLayout); //TODO:
        tabLayout.setupWithViewPager(viewPager, true); //TODO: tabLayout.setupWithViewPager(viewPager, true);
        Log.e("ololo", "tabLayout");

        btnSkip = findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
                finish();
                return;
            }
        });
    }
}