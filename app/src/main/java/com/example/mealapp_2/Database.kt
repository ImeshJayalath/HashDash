package com.example.mealapp_2

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class MealDataBase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}