package com.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);

        SharedPreferences preferences2 = getSharedPreferences("saving", MODE_PRIVATE);
        editTitle.setText(preferences2.getString("title", ""));
        editDesc.setText(preferences2.getString("desc", ""));
        String title = preferences2.getString("title", "");
        String desc = preferences2.getString("desc", "");
        editTitle.setText(title);
        editDesc.setText(desc);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences preferences2 = getSharedPreferences("saving", MODE_PRIVATE);
        preferences2.edit().putString("title", editTitle.getText().toString()).apply();
        preferences2.edit().putString("desc", editDesc.getText().toString()).apply();
        preferences2.edit().apply();
        super.onBackPressed();
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
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();

    }
}
