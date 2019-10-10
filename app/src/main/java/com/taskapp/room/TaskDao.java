package com.taskapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.taskapp.Task;

import java.util.List;

@Dao //TODO:
public interface TaskDao {

    @Query("SELECT * FROM task") //TODO: выбрать ВСЕ из таблицы task
    List<Task> getAll();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);
}

/*
   @Query("SELECT * FROM employee")
   List<Employee> getAll();

   @Query("SELECT * FROM employee WHERE id = :id")
   Employee getById(long id);

   @Insert
   void insert(Employee employee);

   @Update
   void update(Employee employee);

   @Delete
   void delete(Employee employee);
 */