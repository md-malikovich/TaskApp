package com.taskapp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taskapp.R;
import com.taskapp.Task;
import com.taskapp.TaskAdapter;
import com.taskapp.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        initList();
        return root;
    }

    private void initList() {
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() { //TODO: вызываем метод у Adapter
            @Override
            public void onItemClick(int position) {
                Task task = list.get(position);
                //Toast.makeText(getContext(),"pos = " + position, Toast.LENGTH_SHORT).show(); //TODO: показ позиции
                Toast.makeText(getContext(),"a) Title = " + task.getTitle() + "; b) Desc = " + task.getDesc(), Toast.LENGTH_SHORT).show(); //TODO: показ Title
            }

            @Override
            public void onItemLongClick(int position) {
                //Task task = list.get(position);
                Toast.makeText(getContext(),"pos = " + position, Toast.LENGTH_SHORT).show(); //TODO: показ позиции при ДОЛГОМ нажатии
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "onActivityResult fragment");
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Task task = (Task) data.getSerializableExtra("task");
            list.add(task);
            adapter.notifyDataSetChanged();
        }
    }
}