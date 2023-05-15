package com.example.mealapp_2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Insert
    suspend fun insertAll( meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals( meal: Meal)

    @Query("SELECT * FROM meal_table")
    suspend fun getAll(): List<Meal>

    @Query("SELECT * FROM meal_table WHERE name LIKE '%' || :name || '%' OR Ingredient1 LIKE '%' || :name || '%' " +
            "OR Ingredient2 LIKE '%' || :name || '%' OR Ingredient3 LIKE '%' || :name || '%' OR Ingredient4 LIKE '%' || :name || '%'" +
            "OR Ingredient5 LIKE '%' || :name || '%' OR Ingredient6 LIKE '%' || :name || '%'" +
            "OR Ingredient7 LIKE '%' || :name || '%' OR Ingredient8 LIKE '%' || :name || '%'" +
            "OR Ingredient9 LIKE '%' || :name || '%' OR Ingredient10 LIKE '%' || :name || '%'" +
            "OR Ingredient11 LIKE '%' || :name || '%' OR Ingredient12 LIKE '%' || :name || '%'" +
            "OR Ingredient13 LIKE '%' || :name || '%' OR Ingredient14 LIKE '%' || :name || '%'" +
            "OR Ingredient15 LIKE '%' || :name || '%' OR Ingredient16 LIKE '%' || :name || '%'" +
            "OR Ingredient17 LIKE '%' || :name || '%' OR Ingredient18 LIKE '%' || :name || '%'" +
            "OR Ingredient19 LIKE '%' || :name || '%' OR Ingredient20 LIKE '%' || :name || '%'")
    suspend fun getMealByIngredient(name: String): List<Meal>


    @Query("SELECT count(*) FROM meal_table")
    suspend fun countAll(): Int
}

//@Dao
//interface MealDao {
//    @Insert
//    suspend fun insertAll( meal: Meal)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMeals( meal: Meal)
//
//    @Query("SELECT * FROM meal_table")
//    suspend fun getAll(): List<Meal>
//
//    @Query("SELECT * FROM meal_table WHERE name LIKE '%' || :name || '%' OR Ingredient1 LIKE '%' || :name || '%' ")
//    suspend fun getMealByIngredient(name: String): List<Meal>
//
//
//    @Query("SELECT count(*) FROM meal_table")
//    suspend fun countAll(): Int
//}
//@Dao
//interface MealDao {
//    @Insert
//    suspend fun insertAll(vararg meal: Meal)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMeals( meal: Meal)
//
//    @Query("SELECT * FROM meal_table")
//    suspend fun getAll(): List<Meal>
//
//    @Query("\"SELECT * FROM meal_data_table WHERE meal_name LIKE '%' || :name || '%' OR Ingredient1 LIKE '%' || :name || '%' \"")
//    suspend fun getMealByIngredient(name: String): List<Meal>
//
//    @Query("SELECT count(*) FROM meal_table")
//    suspend fun countAll(): Int
//}

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.Toast
//import androidx.room.Room
//import com.example.mobilecw.R
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking