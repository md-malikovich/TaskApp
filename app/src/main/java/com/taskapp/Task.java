package com.taskapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity                                         //TODO: создание таблицы
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true) //TODO:  автоформирование id. Сколько записей было в табл
    private long id; //TODO: добавили id
    private String title;
    private String desc;

    public Task() { //TODO:
    }

    public Task(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
