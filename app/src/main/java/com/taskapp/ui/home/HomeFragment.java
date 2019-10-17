package com.taskapp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.taskapp.App;
import com.taskapp.FormActivity;
import com.taskapp.MainActivity;
import com.taskapp.R;
import com.taskapp.Task;
import com.taskapp.TaskAdapter;
import com.taskapp.interfaces.OnItemClickListener;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    AlertDialog.Builder alertD;
    Context context;

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> list;
    private int pos;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        initList();
        return root;
    }

    public void initList() {
        list = new ArrayList<>();
        list = App.getInstance().getDatabase().taskDao().getAll(); //TODO: получаем данные из таблицы
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() { //вызываем метод у Adapter
            @Override
            public void onItemClick(int position) { //TODO: 2. При обычном нажатии на один из элементов открывается FormActivity для редактирования
                pos = position;
                Task task = list.get(position);
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("task", task); //list.get(position)
                startActivityForResult(intent, 101);
                //Toast.makeText(getContext(),"a) Title = " + task.getTitle() + "; b) Desc = " + task.getDesc(), Toast.LENGTH_SHORT).show();
            }

            @Override                                                                               //TODO: 1. При долгом нажатии AlertDialog для удаления+
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
                        Toast.makeText(context, "Задача удалена", Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(),"pos = " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                alertD.setNegativeButton(buttonNo, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(context, "Задача не удалена", Toast.LENGTH_LONG).show();
                    }
                });
                alertD.show();
                adapter.notifyDataSetChanged();
//                Task task = list.get(position);
//                App.getInstance().getDatabase().taskDao().delete(task); //TODO: удаляет из БД
//                list.remove(task);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(getContext(),"pos = " + position, Toast.LENGTH_SHORT).show(); //TODO: показ позиции при ДОЛГОМ нажатии
            }
        });
    }

    public void sortList(){
        list.clear();
        list.addAll(App.getInstance().getDatabase().taskDao().getAllSorted());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ololo", "onActivityResult fragment");
        if (resultCode == Activity.RESULT_OK) {
            Task task = (Task) data.getSerializableExtra("task");
            if (requestCode == 100) {
                list.add(task);
            } else if (requestCode == 101) {
                list.set(pos, task);
            }
            adapter.notifyDataSetChanged();
        }
    }
}