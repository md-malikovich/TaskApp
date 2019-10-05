package com.taskapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taskapp.interfaces.OnItemClickListener;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> list;
    private OnItemClickListener onItemClickListener; //TODO: объявляем интерфейс глобально

    public TaskAdapter(List<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) { //TODO: метод вызывается при нажатии на любой из эелементов (itemView)
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private TextView textDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);

            itemView.setOnClickListener(new View.OnClickListener() { //TODO: добавляем слушателя на элемент VH
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition()); //TODO: вызываем метод интерфеса и в параметрах указываем position
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() { //TODO: OnLongClickListener
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    //return false;
                    return true; //TODO: в случае если нам нужно использовать Long
                }
            });
        }

        public void bind(Task task) {
            textTitle.setText(task.getTitle());
            textDesc.setText(task.getDesc());

        }
    }
}
