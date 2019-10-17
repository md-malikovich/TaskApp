package com.taskapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.taskapp.Task;

import java.util.List;

@Dao
public interface TaskDao {


    @Query("SELECT * FROM task") //TODO: выбрать ВСЕ из таблицы task
    List<Task> getAll();

    @Query("SELECT * FROM task ORDER BY title ASC") //TODO: DESC в обратном порядке
    List<Task> getAllSorted();

//    Task task = new Task();
//    @Query("SELECT title, desc FROM task")
//    List<Task> task.getTitle();
//    List<Task> task.getDesc();

    @Query("DELETE FROM task")
    void deleteAll();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);
}

/*
   @Query("SELECT * FROM employee")
   List<Employee> getAll();

   @Query("SELECT * FROM employee WHERE id = :id")
   Employee getById(long id);
 */