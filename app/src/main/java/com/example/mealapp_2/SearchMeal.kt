package com.example.mealapp_2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private var searchMealBox: EditText? = null
private var searchMealBtn: Button? = null
private var resultsMealArea: TextView? = null
private var mealTitle: String = ""
private val dbData: ArrayList<String> = ArrayList()

class SearchMeal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_meals)

        searchMealBox = findViewById(R.id.searchMealWebEt)
        searchMealBtn = findViewById(R.id.searchForMealWebBtn)
        resultsMealArea = findViewById(R.id.mealTv)

        searchMealBtn?.setOnClickListener {
            getMeal()
        }
    }

    private fun getMeal() {
        // Access the database
        val db = Room.databaseBuilder(this, Database::class.java, "MealDB").build()
        val mealDao = db.mealDao()

        // Getting the actor name
        val mealOrIng = searchMealBox!!.text.toString().lowercase().trim()
        if (mealOrIng  == "")
            return

        runBlocking {
            launch{
                // Emptying the results box for every search
                mealTitle = ""

                val meals =  mealDao.getMealByIngredient(mealOrIng)

                mealTitle = "|-------- Ingredient Related Meals- -------|\n\n"

                for ( x in meals){
                    mealTitle += "\nMeal: " +  x.meal.toString() +
                            "\nDrinkAlternate: " +  x.drinkAlternate.toString() +
                            "\nCategory: " + x.category.toString() +
                            "\nArea : " + x.area.toString() +
                            "\nInstructions: " + x.instructions.toString() +
                            "\nTag: " + x.tags.toString() +
                            "\nYoutube: " + x.youtube.toString() +
                            "\nIngredient1: " + x.ingredient1.toString() +
                            "\nIngredient2: " + x.ingredient2.toString() +
                            "\nIngredient3: " + x.ingredient3.toString() +
                            "\nIngredient4: " + x.ingredient4.toString() +
                            "\nIngredient5: " + x.ingredient5.toString() +
                            "\nIngredient6: " + x.ingredient6.toString() +
                            "\nIngredient7: " + x.ingredient7.toString() +
                            "\nIngredient8: " + x.ingredient8.toString() +
                            "\nIngredient9: " + x.ingredient9.toString() +
                            "\nIngredient10: " + x.ingredient10.toString() +
                            "\nIngredient11: " + x.ingredient11.toString() +
                            "\nIngredient12: " + x.ingredient12.toString() +
                            "\nIngredient13: " + x.ingredient13.toString() +
                            "\nIngredient14: " + x.ingredient14.toString() +
                            "\nIngredient15: " + x.ingredient15.toString() +
                            "\nIngredient16: " + x.ingredient16.toString() +
                            "\nIngredient17: " + x.ingredient17.toString() +
                            "\nIngredient18: " + x.ingredient18.toString() +
                            "\nIngredient19: " + x.ingredient19.toString() +
                            "\nIngredient20: " + x.ingredient20.toString() +
                            "\nMeasure1: " + x.measure1.toString() +
                            "\nMeasure2: " + x.measure2.toString() +
                            "\nMeasure3: " + x.measure3.toString() +
                            "\nMeasure4: " + x.measure4.toString() +
                            "\nMeasure5: " + x.measure5.toString() +
                            "\nMeasure6: " + x.measure6.toString() +
                            "\nMeasure7: " + x.measure7.toString() +
                            "\nMeasure8: " + x.measure8.toString() +
                            "\nMeasure9: " + x.measure9.toString() +
                            "\nMeasure10: " + x.measure10.toString() +
                            "\nMeasure11: " + x.measure11.toString()+
                            "\nMeasure12: " + x.measure12.toString() +
                            "\nMeasure13: " + x.measure13.toString() +
                            "\nMeasure14: " + x.measure14.toString() +
                            "\nMeasure15: " + x.measure15.toString() +
                            "\nMeasure16: " + x.measure16.toString() +
                            "\nMeasure17: " + x.measure17.toString() +
                            "\nMeasure18: " + x.measure18.toString() +
                            "\nMeasure19: " + x.measure19.toString() +
                            "\nMeasure20: " + x.measure20.toString()
                    mealTitle += "\n\n"
                }

                resultsMealArea?.text = mealTitle

                println("|_______|")
                println(meals)
                println("|_______|")
            }
        }
    }
}