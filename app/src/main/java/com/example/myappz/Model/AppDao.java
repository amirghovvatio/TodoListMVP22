package com.example.myappz.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    long addDays(Days days);
    @Delete
    int deleteDays(Days days);
    @Update
    int updateDays(Days days);
    @Query("Select * From _days")
    List<Days> getAllDays();
    @Query("Select * From _days where dayName like '%' || :q || '%' ")
    List<Days> onSearch(String q);


    @Insert
    long addTasks(Tasks tasks);
    @Delete
    int deleteTasks(Tasks tasks);
    @Update
    int updateTasks(Tasks tasks);
    @Query("Select * From _tasks where dayID like :dayID")
    List<Tasks> getAllTasks(int dayID);
    @Query("Delete From _tasks where dayID like :dayId")
    void clearAllTask(int dayId);
}
