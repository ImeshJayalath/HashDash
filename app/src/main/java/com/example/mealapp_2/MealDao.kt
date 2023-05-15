package com.example.mealapp_2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Insert
    suspend fun insertAll(vararg meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals( meal: Meal)

    @Query("SELECT * FROM meal_table")
    suspend fun getAll(): List<Meal>

    @Query("\"SELECT * FROM meal_data_table WHERE meal_name LIKE '%' || :name || '%' OR meal_Ingredient1 LIKE '%' || :name || '%' \"")
    suspend fun getMealByIngredient(name: String): List<Meal>

    @Query("SELECT count(*) FROM meal_table")
    suspend fun countAll(): Int
}

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.Toast
//import androidx.room.Room
//import com.example.mobilecw.R
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking