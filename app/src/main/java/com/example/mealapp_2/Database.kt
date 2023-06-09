package com.example.mealapp_2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Meal::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun mealDao(): MealDao
}