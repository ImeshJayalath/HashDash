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

        displayMealList()

    }

    private fun displayMealList(){
        val db = Room.databaseBuilder(this, Database::class.java, "MealDB").build()
        val mealDao = db.mealDao()

        runBlocking {
            launch {
                val meals: List<Meal> = mealDao.getAll()
                for (meall in meals) {
                    dbData.add("\n${meall.id}\n ${meall.meal}\n ${meall.drinkAlternate}\n ${meall.category}\n ${meall.area}\n " +
                            "${meall.instructions}\n ${meall.tags}\n ${meall.youtube}\n ${meall.ingredient1}\n " +
                            "${meall.ingredient2}\n ${meall.ingredient3}\n ${meall.ingredient4}\n ${meall.ingredient5}\n " +
                            "${meall.ingredient6}\n ${meall.ingredient7}\n ${meall.ingredient8}\n ${meall.ingredient9}\n "  +
                            "${meall.ingredient10}\n ${meall.ingredient11}\n ${meall.ingredient12}\n ${meall.ingredient13}\n "+
                            "${meall.ingredient14}\n ${meall.ingredient15}\n ${meall.ingredient16}\n ${meall.ingredient17}\n "+
                            "${meall.ingredient18}\n ${meall.ingredient19}\n${meall.ingredient20}\n " +
                            "${meall.measure1}\n ${meall.measure2}\n ${meall.measure3}\n ${meall.measure4}\n "+
                            "${meall.measure5}\n ${meall.measure6}\n ${meall.measure7}\n " +
                            "${meall.measure8}\n ${meall.measure9}\n ${meall.measure10}\n ${meall.measure11}\n "+
                            "${meall.measure12}\n ${meall.measure13}\n ${meall.measure14}\n " +
                            "${meall.measure15}\n ${meall.measure16}\n ${meall.measure17}\n ${meall.measure18}\n "+
                            "${meall.measure19}\n ${meall.measure20}\n")
                }

                for (m in dbData) {
                    result += m + "\n"
                }
                dataOfDb.text = result
            }
        }
    }
}