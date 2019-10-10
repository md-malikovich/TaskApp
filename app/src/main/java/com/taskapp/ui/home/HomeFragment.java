package com.taskapp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.taskapp.App;
import com.taskapp.R;
import com.taskapp.Task;
import com.taskapp.TaskAdapter;
import com.taskapp.interfaces.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class HomeFragment extends Fragment {

    AlertDialog.Builder alertD;
    Context context;

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
        list = App.getInstance().getDatabase().taskDao().getAll(); //TODO: получаем данные из таблицы
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() { //вызываем метод у Adapter
            @Override
            public void onItemClick(int position) {


                //Task task = list.get(position);
                //Toast.makeText(getContext(),"pos = " + position, Toast.LENGTH_SHORT).show(); //показ позиции
                //Toast.makeText(getContext(),"a) Title = " + task.getTitle() + "; b) Desc = " + task.getDesc(), Toast.LENGTH_SHORT).show(); //TODO: показ Title
            }

            @Override                                                                               //TODO: 1. При долгом нажатии AlertDialog для удаления
            public void onItemLongClick(final int position) { //показ позиции при ДОЛГОМ нажатии
                context = getActivity();
                String message = "Вы действительно хотите удалить данную задачу?";
                String buttonYes = "Да";
                String buttonNo = "Нет";

                alertD = new AlertDialog.Builder(context);
                alertD.setMessage(message);
                alertD.setPositiveButton(buttonYes, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Task task = list.get(position);
                        App.getInstance().getDatabase().taskDao().delete(task); //удаляет из БД
                        list.remove(task);
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getContext(),"pos = " + position, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Задача удалена", Toast.LENGTH_LONG).show();
                    }
                });
                alertD.setNegativeButton(buttonNo, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(context, "Задача не удалена", Toast.LENGTH_LONG).show();
                    }
                });
                alertD.show();

//                Task task = list.get(position);
//                App.getInstance().getDatabase().taskDao().delete(task); //TODO: удаляет из БД
//                list.remove(task);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(getContext(),"pos = " + position, Toast.LENGTH_SHORT).show(); //TODO: показ позиции при ДОЛГОМ нажатии
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