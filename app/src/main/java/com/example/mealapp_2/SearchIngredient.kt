package com.example.mealapp_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchIngredient : AppCompatActivity() {


    private var searchBox: EditText? = null
    private var searchBtn: Button? = null
    private var resultsArea: TextView? = null
    private var saveResultToDbBtn: Button? = null
    private var urlString: String = ""
    private var dbData: String = ""

    private var Id: Int = 0
    private var Ids = mutableListOf<Int>()
    private var Name: String = ""
    private var Names = mutableListOf<String>()
    private var DrinkAlternate: String = "null"
    private var DrinkAlternates = mutableListOf<String>()
    private var Category: String = ""
    private var Categories = mutableListOf<String>()
    private var Area: String = ""
    private var Areas = mutableListOf<String>()
    private var Instruction: String = ""
    private var Instructions = mutableListOf<String>()
    private var Tag: String = ""
    private var Tags = mutableListOf<String>()
    private var Youtube: String = ""
    private var Youtubes = mutableListOf<String>()
    private var Ingredient1: String?= null
    private var Ingredient1s = mutableListOf<String>()
    private var Ingredient2: String?= null
    private var Ingredient2s = mutableListOf<String>()
    private var Ingredient3: String?= null
    private var Ingredient3s = mutableListOf<String>()
    private var Ingredient4: String?= null
    private var Ingredient4s = mutableListOf<String>()
    private var Ingredient5: String?= null
    private var Ingredient5s = mutableListOf<String>()
    private var Ingredient6: String?= null
    private var Ingredient6s = mutableListOf<String>()
    private var Ingredient7: String?= null
    private var Ingredient7s = mutableListOf<String>()
    private var Ingredient8: String?= null
    private var Ingredient8s = mutableListOf<String>()
    private var Ingredient9: String?= null
    private var Ingredient9s = mutableListOf<String>()
    private var Ingredient10: String?= null
    private var Ingredient10s = mutableListOf<String>()
    private var Ingredient11: String?= null
    private var Ingredient11s = mutableListOf<String>()
    private var Ingredient12: String?= null
    private var Ingredient12s = mutableListOf<String>()
    private var Ingredient13: String?= null
    private var Ingredient13s = mutableListOf<String>()
    private var Ingredient14: String?= null
    private var Ingredient14s = mutableListOf<String>()
    private var Ingredient15: String?= null
    private var Ingredient15s = mutableListOf<String>()
    private var Ingredient16: String?= null
    private var Ingredient16s = mutableListOf<String>()
    private var Ingredient17: String?= null
    private var Ingredient17s = mutableListOf<String>()
    private var Ingredient18: String?= null
    private var Ingredient18s = mutableListOf<String>()
    private var Ingredient19: String?= null
    private var Ingredient19s = mutableListOf<String>()
    private var Ingredient20: String?= null
    private var Ingredient20s = mutableListOf<String>()

    private var Measure1: String?= null
    private var Measure1s = mutableListOf<String>()
    private var Measure2: String?= null
    private var Measure2s = mutableListOf<String>()
    private var Measure3: String?= null
    private var Measure3s = mutableListOf<String>()
    private var Measure4: String?= null
    private var Measure4s = mutableListOf<String>()
    private var Measure5: String?= null
    private var Measure5s = mutableListOf<String>()
    private var Measure6: String?= null
    private var Measure6s = mutableListOf<String>()
    private var Measure7: String?= null
    private var Measure7s = mutableListOf<String>()
    private var Measure8: String?= null
    private var Measure8s = mutableListOf<String>()
    private var Measure9: String?= null
    private var Measure9s = mutableListOf<String>()
    private var Measure10: String?= null
    private var Measure10s = mutableListOf<String>()
    private var Measure11: String?= null
    private var Measure11s = mutableListOf<String>()
    private var Measure12: String?= null
    private var Measure12s = mutableListOf<String>()
    private var Measure13: String?= null
    private var Measure13s = mutableListOf<String>()
    private var Measure14: String?= null
    private var Measure14s = mutableListOf<String>()
    private var Measure15: String?= null
    private var Measure15s = mutableListOf<String>()
    private var Measure16: String?= null
    private var Measure16s = mutableListOf<String>()
    private var Measure17: String?= null
    private var Measure17s = mutableListOf<String>()
    private var Measure18: String?= null
    private var Measure18s = mutableListOf<String>()
    private var Measure19: String?= null
    private var Measure19s = mutableListOf<String>()
    private var Measure20: String?= null
    private var Measure20s = mutableListOf<String>()

    private var finalResult: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_ingredient)

        searchBox = findViewById(R.id.searchEt)
        searchBtn = findViewById(R.id.seachBtn)
        resultsArea = findViewById(R.id.dataSearchTv)
        saveResultToDbBtn = findViewById(R.id.saveBtn)

        searchBtn?.setOnClickListener {
            getMeal()
        }

        saveResultToDbBtn?.setOnClickListener {
            saveMeal()
        }


    }


    private fun getMeal() {
        val meal = searchBox!!.text.toString().lowercase().trim()
        if (meal  == "")
            return

        var stb = StringBuilder()
        urlString = "https://www.themealdb.com/api/json/v1/1/search.php?s=$meal"
        val url = URL(urlString)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        runBlocking {
            withContext(Dispatchers.IO){
                var bf = BufferedReader(InputStreamReader(con.inputStream))
                var line: String? = bf.readLine()
                while (line != null) {
                    stb.append(line + "\n")
                    line = bf.readLine()
                }
                dbData = parseJSON(stb)
            }
            resultsArea?.setText(dbData)
        }
    }

    private fun parseJSON(stb: StringBuilder): String {

        val json = JSONObject(stb.toString())
        val mealArray = json.getJSONArray("meals")
        val mealArrayLength = mealArray.length()

        println(json)
        try{
            for(i in 0 until mealArrayLength){
                val mealObject = mealArray.getJSONObject(i)
                Id=  mealObject.getString("idMeal").toInt()
                Ids.add(Id)
                Name = mealObject.getString("strMeal")
                Names.add(Name)
                DrinkAlternate = mealObject.getString("strDrinkAlternate")
                DrinkAlternates.add(DrinkAlternate)
                Category = mealObject.getString("strCategory")
                Categories.add(Category)
                Area = mealObject.getString("strArea")
                Areas.add(Area)
                Instruction = mealObject.getString("strInstructions")
                Instructions.add(Instruction)
                Tag =mealObject.getString("strTags")
                Tags.add(Tag)
                Youtube = mealObject.getString("strYoutube")
                Youtubes.add(Youtube)

                Ingredient1= mealObject.getString("strIngredient1")
                if (Ingredient1.isNullOrEmpty()) {
                    Ingredient1 = "null"
                }
                Ingredient1s.add(Ingredient1 as String)

                Ingredient2 = mealObject.getString("strIngredient2")
                if (Ingredient2.isNullOrEmpty()) {
                    Ingredient2 = "null"
                }
                Ingredient2s.add(Ingredient2 as String)

                Ingredient3= mealObject.getString("strIngredient3")
                if (Ingredient3.isNullOrEmpty()) {
                    Ingredient3 = "null"
                }
                Ingredient3s.add(Ingredient3 as String)

                Ingredient4 = mealObject.getString("strIngredient4")
                if (Ingredient4.isNullOrEmpty()) {
                    Ingredient4 = "null"
                }
                Ingredient4s.add(Ingredient4 as String)

                Ingredient5 = mealObject.getString("strIngredient5")
                if (Ingredient5.isNullOrEmpty()) {
                    Ingredient5 = "null"
                }
                Ingredient5s.add(Ingredient5 as String)

                Ingredient6 = mealObject.getString("strIngredient6")
                if (Ingredient6.isNullOrEmpty()) {
                    Ingredient6 = "null"
                }
                Ingredient6s.add(Ingredient6 as String)

                Ingredient7 = mealObject.getString("strIngredient7")
                if (Ingredient7.isNullOrEmpty()) {
                    Ingredient7 = "null"
                }
                Ingredient7s.add(Ingredient7 as String)

                Ingredient8 = mealObject.getString("strIngredient8")
                if (Ingredient8.isNullOrEmpty()) {
                    Ingredient8 = "null"
                }
                Ingredient8s.add(Ingredient8 as String)

                Ingredient9 = mealObject.getString("strIngredient9")
                if (Ingredient9.isNullOrEmpty()) {
                    Ingredient9 = "null"
                }
                Ingredient9s.add(Ingredient9 as String)

                Ingredient10 = mealObject.getString("strIngredient10")
                if (Ingredient10.isNullOrEmpty()) {
                    Ingredient10 = "null"
                }
                Ingredient10s.add(Ingredient10 as String)

                Ingredient11 = mealObject.getString("strIngredient11")
                if (Ingredient11.isNullOrEmpty()) {
                    Ingredient11 = "null"
                }
                Ingredient11s.add(Ingredient11 as String)

                Ingredient12 = mealObject.getString("strIngredient12")
                if (Ingredient12.isNullOrEmpty()) {
                    Ingredient12 = "null"
                }
                Ingredient12s.add(Ingredient12 as String)

                Ingredient13 = mealObject.getString("strIngredient13")
                if (Ingredient13.isNullOrEmpty()) {
                    Ingredient13 = "null"
                }
                Ingredient13s.add(Ingredient13 as String)

                Ingredient14 = mealObject.getString("strIngredient14")
                if (Ingredient14.isNullOrEmpty()) {
                    Ingredient14 = "null"
                }
                Ingredient14s.add(Ingredient14 as String)

                Ingredient15 = mealObject.getString("strIngredient15")
                if (Ingredient15.isNullOrEmpty()) {
                    Ingredient15 = "null"
                }
                Ingredient15s.add(Ingredient15 as String)

                Ingredient16 = mealObject.getString("strIngredient16")
                if (Ingredient16.isNullOrEmpty()) {
                    Ingredient16 = "null"
                }
                Ingredient16s.add(Ingredient16 as String)

                Ingredient17 = mealObject.getString("strIngredient17")
                if (Ingredient17.isNullOrEmpty()) {
                    Ingredient17 = "null"
                }
                Ingredient17s.add(Ingredient17 as String)

                Ingredient18 = mealObject.getString("strIngredient18")
                if (Ingredient18.isNullOrEmpty()) {
                    Ingredient18 = "null"
                }
                Ingredient18s.add(Ingredient18 as String)

                Ingredient19 = mealObject.getString("strIngredient19")
                if (Ingredient19.isNullOrEmpty()) {
                    Ingredient19 = "null"
                }
                Ingredient19s.add(Ingredient19 as String)

                Ingredient20 = mealObject.getString("strIngredient20")
                if (Ingredient20.isNullOrEmpty()) {
                    Ingredient20 = "null"
                }
                Ingredient20s.add(Ingredient20 as String)


                Measure1 =mealObject.getString("strMeasure1")
                if (Measure1.isNullOrEmpty()) {
                    Measure1 = "null"
                }
                Measure1s.add(Measure1 as String)

                Measure2 =mealObject.getString("strMeasure2")
                if (Measure2.isNullOrEmpty()) {
                    Measure2 = "null"
                }
                Measure2s.add(Measure2 as String)

                Measure3 =mealObject.getString("strMeasure3")
                if (Measure3.isNullOrEmpty()) {
                    Measure3 = "null"
                }
                Measure3s.add(Measure3 as String)

                Measure4 =mealObject.getString("strMeasure4")
                if (Measure4.isNullOrEmpty()) {
                    Measure4 = "null"
                }
                Measure4s.add(Measure4 as String)

                Measure5 =mealObject.getString("strMeasure5")
                if (Measure5.isNullOrEmpty()) {
                    Measure5 = "null"
                }
                Measure5s.add(Measure5 as String)

                Measure6 =mealObject.getString("strMeasure6")
                if (Measure6.isNullOrEmpty()) {
                    Measure6 = "null"
                }
                Measure6s.add(Measure6 as String)

                Measure7 =mealObject.getString("strMeasure7")
                if (Measure7.isNullOrEmpty()) {
                    Measure7 = "null"
                }
                Measure7s.add(Measure7 as String)

                Measure8 =mealObject.getString("strMeasure8")
                if (Measure8.isNullOrEmpty()) {
                    Measure8 = "null"
                }
                Measure8s.add(Measure8 as String)

                Measure9 =mealObject.getString("strMeasure9")
                if (Measure9.isNullOrEmpty()) {
                    Measure9 = "null"
                }
                Measure9s.add(Measure9 as String)

                Measure10 =mealObject.getString("strMeasure10")
                if (Measure10.isNullOrEmpty()) {
                    Measure10 = "null"
                }
                Measure10s.add(Measure10 as String)

                Measure11 =mealObject.getString("strMeasure11")
                if (Measure11.isNullOrEmpty()) {
                    Measure11 = "null"
                }
                Measure11s.add(Measure11 as String)

                Measure12 =mealObject.getString("strMeasure12")
                if (Measure12.isNullOrEmpty()) {
                    Measure12 = "null"
                }
                Measure12s.add(Measure12 as String)

                Measure13 =mealObject.getString("strMeasure13")
                if (Measure13.isNullOrEmpty()) {
                    Measure13 = "null"
                }
                Measure13s.add(Measure13 as String)

                Measure14 =mealObject.getString("strMeasure14")
                if (Measure14.isNullOrEmpty()) {
                    Measure14 = "null"
                }
                Measure14s.add(Measure14 as String)

                Measure15 =mealObject.getString("strMeasure15")
                if (Measure15.isNullOrEmpty()) {
                    Measure15 = "null"
                }
                Measure15s.add(Measure15 as String)

                Measure16 =mealObject.getString("strMeasure16")
                if (Measure16.isNullOrEmpty()) {
                    Measure16 = "null"
                }
                Measure16s.add(Measure16 as String)

                Measure17 =mealObject.getString("strMeasure17")
                if (Measure17.isNullOrEmpty()) {
                    Measure17 = "null"
                }
                Measure17s.add(Measure17 as String)

                Measure18 =mealObject.getString("strMeasure18")
                if (Measure18.isNullOrEmpty()) {
                    Measure18 = "null"
                }
                Measure18s.add(Measure18 as String)

                Measure19 =mealObject.getString("strMeasure19")
                if (Measure19.isNullOrEmpty()) {
                    Measure19 = "null"
                }
                Measure19s.add(Measure19 as String)

                Measure20 =mealObject.getString("strMeasure20")
                if (Measure20.isNullOrEmpty()) {
                    Measure20 = "null"
                }
                Measure20s.add(Measure20 as String)

                finalResult += "\nMeal: " +  Name +
                        "\nDrinkAlternate: " +  DrinkAlternates +
                        "\nCategory: " + Category +
                        "\nArea : " + Area +
                        "\nInstructions: " + Instruction +
                        "\nTag: " + Tag +
                        "\nYoutube: " + Youtube +
                        "\nIngredient1: " + Ingredient1 +
                        "\nIngredient2: " + Ingredient2 +
                        "\nIngredient3: " + Ingredient3 +
                        "\nIngredient4: " + Ingredient4 +
                        "\nIngredient5: " + Ingredient5 +
                        "\nIngredient6: " + Ingredient6 +
                        "\nIngredient7: " + Ingredient7 +
                        "\nIngredient8: " + Ingredient8 +
                        "\nIngredient9: " + Ingredient9 +
                        "\nIngredient10: " + Ingredient10 +
                        "\nIngredient11: " + Ingredient11 +
                        "\nIngredient12: " + Ingredient12 +
                        "\nIngredient13: " + Ingredient13 +
                        "\nIngredient14: " + Ingredient14 +
                        "\nIngredient15: " + Ingredient15 +
                        "\nIngredient16: " + Ingredient16 +
                        "\nIngredient17: " + Ingredient17 +
                        "\nIngredient18: " + Ingredient18 +
                        "\nIngredient19: " + Ingredient19 +
                        "\nIngredient20: " + Ingredient20 +
                        "\nMeasure1: " + Measure1 +
                        "\nMeasure2: " + Measure2 +
                        "\nMeasure3: " + Measure3 +
                        "\nMeasure4: " + Measure4 +
                        "\nMeasure5: " + Measure5 +
                        "\nMeasure6 : " + Measure6 +
                        "\nMeasure7: " + Measure7 +
                        "\nMeasure8: " + Measure8 +
                        "\nMeasure9: " + Measure9 +
                        "\nMeasure10: " + Measure10 +
                        "\nMeasure11: " + Measure11 +
                        "\nMeasure12: " + Measure12 +
                        "\nMeasure13: " + Measure13 +
                        "\nMeasure14: " + Measure14 +
                        "\nMeasure15: " + Measure15 +
                        "\nMeasure16: " + Measure16 +
                        "\nMeasure17: " + Measure17 +
                        "\nMeasure18: " + Measure18 +
                        "\nMeasure19: " + Measure19 +
                        "\nMeasure20: " + Measure20+
                        "\n\n"
                print("this is running ")
            }
        } catch (e: JSONException){
            println("*****")
            e.printStackTrace()
            println("*****")

            finalResult =  "\n|-Search Failed-|\n" +

                    "|- Try again -|"
        }
        return finalResult;
    }
    private fun saveMeal() {

        val db = Room.databaseBuilder(this, Database::class.java, "MealDB").build()
        val mealDao = db.mealDao()

        runBlocking {
            launch{
                val meals: List<Meal> = mealDao.getAll()
                var meaDataArray: ArrayList<Meal> = ArrayList()
                var index = meals.size
                for ( i in 0 until Names.size){
                    index += i
                    meaDataArray.add(Meal(index, Names[i], DrinkAlternates[i], Categories[i], Areas[i],
                        Instructions[i], Tags[i], Youtubes[i], Ingredient1s[i], Ingredient2s[i],
                        Ingredient3s[i], Ingredient4s[i],Ingredient5s[i],Ingredient6s[i],Ingredient7s[i],
                        Ingredient8s[i],Ingredient9s[i],Ingredient10s[i],Ingredient11s[i],Ingredient12s[i],
                        Ingredient13s[i],Ingredient14s[i],Ingredient15s[i],Ingredient16s[i],Ingredient17s[i],
                        Ingredient18s[i],Ingredient19s[i],Ingredient20s[i],Measure1s[i],Measure2s[i],
                        Measure3s[i],Measure4s[i],Measure5s[i],Measure6s[i],Measure7s[i],Measure8s[i],
                        Measure9s[i],Measure10s[i],Measure11s[i],Measure12s[i],Measure13s[i],Measure14s[i],
                        Measure15s[i],Measure16s[i],Measure17s[i],Measure18s[i],Measure19s[i],Measure20s[i]))

                }
                println(meaDataArray.size-1)
                for ( i in 0 until (meaDataArray.size)){
                    mealDao.insertMeals(meaDataArray[i])
                }
                println("\n|-------------|\n")
                for(m in meals){
                    println(m)
                    println()
                }
                println("\n|-------------|\n")
            }
        }
    }
}