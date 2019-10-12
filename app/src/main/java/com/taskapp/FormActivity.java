package com.taskapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FormActivity extends AppCompatActivity {

    static public final String NEW_KEY = "task";

    private EditText editTitle;
    private EditText editDesc;
    Task task;
    //boolean isShowed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);

        task = (Task) getIntent().getSerializableExtra(NEW_KEY);
        if (task != null) {
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesc());
            //isShowed = true;
        }

//        SharedPreferences preferences2 = getSharedPreferences("saving", MODE_PRIVATE);
//        editTitle.setText(preferences2.getString("title", ""));
//        editDesc.setText(preferences2.getString("desc", ""));
        //Вариант #2:
        //String title = preferences2.getString("title", "");
        //String desc = preferences2.getString("desc", "");
        //editTitle.setText(title);
        //editDesc.setText(desc);

        FloatingActionButton fab2 = findViewById(R.id.fab_two);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTitle.setText("");
                editDesc.setText("");
            }
        });
    }

    @Override
    public void onBackPressed() {
        SharedPreferences preferences2 = getSharedPreferences("saving", MODE_PRIVATE);
        preferences2.edit().putString("title", editTitle.getText().toString()).apply();
        preferences2.edit().putString("desc", editDesc.getText().toString()).apply();
        preferences2.edit().apply();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_droid) {
            Toast.makeText(this, getString(R.string.android1) + getString(R.string.android2), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString();
        String desc = editDesc.getText().toString();
        if (TextUtils.isEmpty(title)) {
            editTitle.setError("Заполните это поле");
            return;
        }
        if (TextUtils.isEmpty(desc)) {
            editDesc.setError("Заполните это поле");
            return;
        }
        Task task = new Task(title, desc);
        App.getInstance().getDatabase().taskDao().update(task);

        //App.getInstance().getDatabase().taskDao().insert(task);

        Intent intent = new Intent();
        intent.putExtra(NEW_KEY, task);
        setResult(RESULT_OK, intent);
        finish();
    }
}