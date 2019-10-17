package com.taskapp;

import android.widget.CheckBox;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity                                         //TODO: создание таблицы
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true) //TODO:  автоформирование id. Сколько записей было в табл
    private long id; //TODO: добавили id
    private String title;
    private String desc;
    //public boolean isDone;
    //CheckBox isDone;

    public Task() { //TODO:
    }

    public Task(String title, String desc) { //, boolean isDone
        this.title = title;
        this.desc = desc;
        //this.isDone = isDone;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

//    public boolean isDone() {
//        return isDone;
//    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//    public void setDone(boolean done) {
//        isDone = done;
//    }
}
