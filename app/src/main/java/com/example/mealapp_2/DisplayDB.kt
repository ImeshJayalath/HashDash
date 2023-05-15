package com.example.mealapp_2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DisplayDB : AppCompatActivity() {

    lateinit var pageTitle: TextView
    lateinit var dataOfDb: TextView
    private val dbData: ArrayList<String> = ArrayList()
    private var result = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.displaydb)
        pageTitle = findViewById(R.id.displayDataTv)
        dataOfDb = findViewById(R.id.dataTv)
        displaymealist()
    }
    private fun displaymealist(){
        val db = Room.databaseBuilder(this, Database::class.java, "MealDB").build()
        val mealDao = db.mealDao()
        runBlocking {
            launch {
                val meals: List<Meal> = mealDao.getAll()
                for (meal in meals) {
                    dbData.add("\n${meal.id}\n ${meal.meal}\n ${meal.drinkAlternate}\n ${meal.category}\n ${meal.area}\n " +
                            "${meal.instructions}\n ${meal.tags}\n ${meal.youtube}\n ${meal.ingredient1}\n " +
                            "${meal.ingredient2}\n ${meal.ingredient3}\n ${meal.ingredient4}\n ${meal.ingredient5}\n " +
                            "${meal.ingredient6}\n ${meal.ingredient7}\n ${meal.ingredient8}\n ${meal.ingredient9}\n "  +
                            "${meal.ingredient10}\n ${meal.ingredient11}\n ${meal.ingredient12}\n ${meal.ingredient13}\n "+
                            "${meal.ingredient14}\n ${meal.ingredient15}\n ${meal.ingredient16}\n ${meal.ingredient17}\n "+
                            "${meal.ingredient18}\n ${meal.ingredient19}\n${meal.ingredient20}\n " +
                            "${meal.measure1}\n ${meal.measure2}\n ${meal.measure3}\n ${meal.measure4}\n "+
                            "${meal.measure5}\n ${meal.measure6}\n ${meal.measure7}\n " +
                            "${meal.measure8}\n ${meal.measure9}\n ${meal.measure10}\n ${meal.measure11}\n "+
                            "${meal.measure12}\n ${meal.measure13}\n ${meal.measure14}\n " +
                            "${meal.measure15}\n ${meal.measure16}\n ${meal.measure17}\n ${meal.measure18}\n "+
                            "${meal.measure19}\n ${meal.measure20}\n")
                }
                for (m in dbData) {
                    result += m + "\n"
                }
                dataOfDb.text = result
            }
        }
    }
}