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

    private var mealId: Int = 0
    private var mealIds = mutableListOf<Int>()
    private var mealName: String = ""
    private var mealNames = mutableListOf<String>()
    private var mealDrinkAlternate: String = "null"
    private var mealDrinkAlternates = mutableListOf<String>()
    private var mealCategory: String = ""
    private var mealCategorys = mutableListOf<String>()
    private var mealArea: String = ""
    private var mealAreas = mutableListOf<String>()
    private var mealInstructions: String = ""
    private var mealInstructions1 = mutableListOf<String>()
    private var mealTag: String = ""
    private var mealTags = mutableListOf<String>()
    private var mealYoutube: String = ""
    private var mealYoutubes = mutableListOf<String>()
    private var mealIngredient1: String?= null
    private var mealIngredient1s = mutableListOf<String>()
    private var mealIngredient2: String?= null
    private var mealIngredient2s = mutableListOf<String>()
    private var mealIngredient3: String?= null
    private var mealIngredient3s = mutableListOf<String>()
    private var mealIngredient4: String?= null
    private var mealIngredient4s = mutableListOf<String>()
    private var mealIngredient5: String?= null
    private var mealIngredient5s = mutableListOf<String>()
    private var mealIngredient6: String?= null
    private var mealIngredient6s = mutableListOf<String>()
    private var mealIngredient7: String?= null
    private var mealIngredient7s = mutableListOf<String>()
    private var mealIngredient8: String?= null
    private var mealIngredient8s = mutableListOf<String>()
    private var mealIngredient9: String?= null
    private var mealIngredient9s = mutableListOf<String>()
    private var mealIngredient10: String?= null
    private var mealIngredient10s = mutableListOf<String>()
    private var mealIngredient11: String?= null
    private var mealIngredient11s = mutableListOf<String>()
    private var mealIngredient12: String?= null
    private var mealIngredient12s = mutableListOf<String>()
    private var mealIngredient13: String?= null
    private var mealIngredient13s = mutableListOf<String>()
    private var mealIngredient14: String?= null
    private var mealIngredient14s = mutableListOf<String>()
    private var mealIngredient15: String?= null
    private var mealIngredient15s = mutableListOf<String>()
    private var mealIngredient16: String?= null
    private var mealIngredient16s = mutableListOf<String>()
    private var mealIngredient17: String?= null
    private var mealIngredient17s = mutableListOf<String>()
    private var mealIngredient18: String?= null
    private var mealIngredient18s = mutableListOf<String>()
    private var mealIngredient19: String?= null
    private var mealIngredient19s = mutableListOf<String>()
    private var mealIngredient20: String?= null
    private var mealIngredient20s = mutableListOf<String>()

    private var mealMeasure1: String?= null
    private var mealMeasure1s = mutableListOf<String>()
    private var mealMeasure2: String?= null
    private var mealMeasure2s = mutableListOf<String>()
    private var mealMeasure3: String?= null
    private var mealMeasure3s = mutableListOf<String>()
    private var mealMeasure4: String?= null
    private var mealMeasure4s = mutableListOf<String>()
    private var mealMeasure5: String?= null
    private var mealMeasure5s = mutableListOf<String>()
    private var mealMeasure6: String?= null
    private var mealMeasure6s = mutableListOf<String>()
    private var mealMeasure7: String?= null
    private var mealMeasure7s = mutableListOf<String>()
    private var mealMeasure8: String?= null
    private var mealMeasure8s = mutableListOf<String>()
    private var mealMeasure9: String?= null
    private var mealMeasure9s = mutableListOf<String>()
    private var mealMeasure10: String?= null
    private var mealMeasure10s = mutableListOf<String>()
    private var mealMeasure11: String?= null
    private var mealMeasure11s = mutableListOf<String>()
    private var mealMeasure12: String?= null
    private var mealMeasure12s = mutableListOf<String>()
    private var mealMeasure13: String?= null
    private var mealMeasure13s = mutableListOf<String>()
    private var mealMeasure14: String?= null
    private var mealMeasure14s = mutableListOf<String>()
    private var mealMeasure15: String?= null
    private var mealMeasure15s = mutableListOf<String>()
    private var mealMeasure16: String?= null
    private var mealMeasure16s = mutableListOf<String>()
    private var mealMeasure17: String?= null
    private var mealMeasure17s = mutableListOf<String>()
    private var mealMeasure18: String?= null
    private var mealMeasure18s = mutableListOf<String>()
    private var mealMeasure19: String?= null
    private var mealMeasure19s = mutableListOf<String>()
    private var mealMeasure20: String?= null
    private var mealMeasure20s = mutableListOf<String>()

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
                mealId=  mealObject.getString("idMeal").toInt()
                mealIds.add(mealId)
                mealName = mealObject.getString("strMeal")
                mealNames.add(mealName)
                mealDrinkAlternate = mealObject.getString("strDrinkAlternate")
                mealDrinkAlternates.add(mealDrinkAlternate)
                mealCategory = mealObject.getString("strCategory")
                mealCategorys.add(mealCategory)
                mealArea = mealObject.getString("strArea")
                mealAreas.add(mealArea)
                mealInstructions = mealObject.getString("strInstructions")
                mealInstructions1.add(mealInstructions)
                mealTag =mealObject.getString("strTags")
                mealTags.add(mealTag)
                mealYoutube = mealObject.getString("strYoutube")
                mealYoutubes.add(mealYoutube)

                mealIngredient1= mealObject.getString("strIngredient1")
                if (mealIngredient1.isNullOrEmpty()) {
                    mealIngredient1 = "null"
                }
                mealIngredient1s.add(mealIngredient1 as String)

                mealIngredient2 = mealObject.getString("strIngredient2")
                if (mealIngredient2.isNullOrEmpty()) {
                    mealIngredient2 = "null"
                }
                mealIngredient2s.add(mealIngredient2 as String)

                mealIngredient3= mealObject.getString("strIngredient3")
                if (mealIngredient3.isNullOrEmpty()) {
                    mealIngredient3 = "null"
                }
                mealIngredient3s.add(mealIngredient3 as String)

                mealIngredient4 = mealObject.getString("strIngredient4")
                if (mealIngredient4.isNullOrEmpty()) {
                    mealIngredient4 = "null"
                }
                mealIngredient4s.add(mealIngredient4 as String)

                mealIngredient5 = mealObject.getString("strIngredient5")
                if (mealIngredient5.isNullOrEmpty()) {
                    mealIngredient5 = "null"
                }
                mealIngredient5s.add(mealIngredient5 as String)

                mealIngredient6 = mealObject.getString("strIngredient6")
                if (mealIngredient6.isNullOrEmpty()) {
                    mealIngredient6 = "null"
                }
                mealIngredient6s.add(mealIngredient6 as String)

                mealIngredient7 = mealObject.getString("strIngredient7")
                if (mealIngredient7.isNullOrEmpty()) {
                    mealIngredient7 = "null"
                }
                mealIngredient7s.add(mealIngredient7 as String)

                mealIngredient8 = mealObject.getString("strIngredient8")
                if (mealIngredient8.isNullOrEmpty()) {
                    mealIngredient8 = "null"
                }
                mealIngredient8s.add(mealIngredient8 as String)

                mealIngredient9 = mealObject.getString("strIngredient9")
                if (mealIngredient9.isNullOrEmpty()) {
                    mealIngredient9 = "null"
                }
                mealIngredient9s.add(mealIngredient9 as String)

                mealIngredient10 = mealObject.getString("strIngredient10")
                if (mealIngredient10.isNullOrEmpty()) {
                    mealIngredient10 = "null"
                }
                mealIngredient10s.add(mealIngredient10 as String)

                mealIngredient11 = mealObject.getString("strIngredient11")
                if (mealIngredient11.isNullOrEmpty()) {
                    mealIngredient11 = "null"
                }
                mealIngredient11s.add(mealIngredient11 as String)

                mealIngredient12 = mealObject.getString("strIngredient12")
                if (mealIngredient12.isNullOrEmpty()) {
                    mealIngredient12 = "null"
                }
                mealIngredient12s.add(mealIngredient12 as String)

                mealIngredient13 = mealObject.getString("strIngredient13")
                if (mealIngredient13.isNullOrEmpty()) {
                    mealIngredient13 = "null"
                }
                mealIngredient13s.add(mealIngredient13 as String)

                mealIngredient14 = mealObject.getString("strIngredient14")
                if (mealIngredient14.isNullOrEmpty()) {
                    mealIngredient14 = "null"
                }
                mealIngredient14s.add(mealIngredient14 as String)

                mealIngredient15 = mealObject.getString("strIngredient15")
                if (mealIngredient15.isNullOrEmpty()) {
                    mealIngredient15 = "null"
                }
                mealIngredient15s.add(mealIngredient15 as String)

                mealIngredient16 = mealObject.getString("strIngredient16")
                if (mealIngredient16.isNullOrEmpty()) {
                    mealIngredient16 = "null"
                }
                mealIngredient16s.add(mealIngredient16 as String)

                mealIngredient17 = mealObject.getString("strIngredient17")
                if (mealIngredient17.isNullOrEmpty()) {
                    mealIngredient17 = "null"
                }
                mealIngredient17s.add(mealIngredient17 as String)

                mealIngredient18 = mealObject.getString("strIngredient18")
                if (mealIngredient18.isNullOrEmpty()) {
                    mealIngredient18 = "null"
                }
                mealIngredient18s.add(mealIngredient18 as String)

                mealIngredient19 = mealObject.getString("strIngredient19")
                if (mealIngredient19.isNullOrEmpty()) {
                    mealIngredient19 = "null"
                }
                mealIngredient19s.add(mealIngredient19 as String)

                mealIngredient20 = mealObject.getString("strIngredient20")
                if (mealIngredient20.isNullOrEmpty()) {
                    mealIngredient20 = "null"
                }
                mealIngredient20s.add(mealIngredient20 as String)


                mealMeasure1 =mealObject.getString("strMeasure1")
                if (mealMeasure1.isNullOrEmpty()) {
                    mealMeasure1 = "null"
                }
                mealMeasure1s.add(mealMeasure1 as String)

                mealMeasure2 =mealObject.getString("strMeasure2")
                if (mealMeasure2.isNullOrEmpty()) {
                    mealMeasure2 = "null"
                }
                mealMeasure2s.add(mealMeasure2 as String)

                mealMeasure3 =mealObject.getString("strMeasure3")
                if (mealMeasure3.isNullOrEmpty()) {
                    mealMeasure3 = "null"
                }
                mealMeasure3s.add(mealMeasure3 as String)

                mealMeasure4 =mealObject.getString("strMeasure4")
                if (mealMeasure4.isNullOrEmpty()) {
                    mealMeasure4 = "null"
                }
                mealMeasure4s.add(mealMeasure4 as String)

                mealMeasure5 =mealObject.getString("strMeasure5")
                if (mealMeasure5.isNullOrEmpty()) {
                    mealMeasure5 = "null"
                }
                mealMeasure5s.add(mealMeasure5 as String)

                mealMeasure6 =mealObject.getString("strMeasure6")
                if (mealMeasure6.isNullOrEmpty()) {
                    mealMeasure6 = "null"
                }
                mealMeasure6s.add(mealMeasure6 as String)

                mealMeasure7 =mealObject.getString("strMeasure7")
                if (mealMeasure7.isNullOrEmpty()) {
                    mealMeasure7 = "null"
                }
                mealMeasure7s.add(mealMeasure7 as String)

                mealMeasure8 =mealObject.getString("strMeasure8")
                if (mealMeasure8.isNullOrEmpty()) {
                    mealMeasure8 = "null"
                }
                mealMeasure8s.add(mealMeasure8 as String)

                mealMeasure9 =mealObject.getString("strMeasure9")
                if (mealMeasure9.isNullOrEmpty()) {
                    mealMeasure9 = "null"
                }
                mealMeasure9s.add(mealMeasure9 as String)

                mealMeasure10 =mealObject.getString("strMeasure10")
                if (mealMeasure10.isNullOrEmpty()) {
                    mealMeasure10 = "null"
                }
                mealMeasure10s.add(mealMeasure10 as String)

                mealMeasure11 =mealObject.getString("strMeasure11")
                if (mealMeasure11.isNullOrEmpty()) {
                    mealMeasure11 = "null"
                }
                mealMeasure11s.add(mealMeasure11 as String)

                mealMeasure12 =mealObject.getString("strMeasure12")
                if (mealMeasure12.isNullOrEmpty()) {
                    mealMeasure12 = "null"
                }
                mealMeasure12s.add(mealMeasure12 as String)

                mealMeasure13 =mealObject.getString("strMeasure13")
                if (mealMeasure13.isNullOrEmpty()) {
                    mealMeasure13 = "null"
                }
                mealMeasure13s.add(mealMeasure13 as String)

                mealMeasure14 =mealObject.getString("strMeasure14")
                if (mealMeasure14.isNullOrEmpty()) {
                    mealMeasure14 = "null"
                }
                mealMeasure14s.add(mealMeasure14 as String)

                mealMeasure15 =mealObject.getString("strMeasure15")
                if (mealMeasure15.isNullOrEmpty()) {
                    mealMeasure15 = "null"
                }
                mealMeasure15s.add(mealMeasure15 as String)

                mealMeasure16 =mealObject.getString("strMeasure16")
                if (mealMeasure16.isNullOrEmpty()) {
                    mealMeasure16 = "null"
                }
                mealMeasure16s.add(mealMeasure16 as String)

                mealMeasure17 =mealObject.getString("strMeasure17")
                if (mealMeasure17.isNullOrEmpty()) {
                    mealMeasure17 = "null"
                }
                mealMeasure17s.add(mealMeasure17 as String)

                mealMeasure18 =mealObject.getString("strMeasure18")
                if (mealMeasure18.isNullOrEmpty()) {
                    mealMeasure18 = "null"
                }
                mealMeasure18s.add(mealMeasure18 as String)

                mealMeasure19 =mealObject.getString("strMeasure19")
                if (mealMeasure19.isNullOrEmpty()) {
                    mealMeasure19 = "null"
                }
                mealMeasure19s.add(mealMeasure19 as String)

                mealMeasure20 =mealObject.getString("strMeasure20")
                if (mealMeasure20.isNullOrEmpty()) {
                    mealMeasure20 = "null"
                }
                mealMeasure20s.add(mealMeasure20 as String)

                finalResult += "\nMeal: " +  mealName +
                        "\nDrinkAlternate: " +  mealDrinkAlternates +
                        "\nCategory: " + mealCategory +
                        "\nArea : " + mealArea +
                        "\nInstructions: " + mealInstructions +
                        "\nTag: " + mealTag +
                        "\nYoutube: " + mealYoutube +
                        "\nIngredient1: " + mealIngredient1 +
                        "\nIngredient2: " + mealIngredient2 +
                        "\nIngredient3: " + mealIngredient3 +
                        "\nIngredient4: " + mealIngredient4 +
                        "\nIngredient5: " + mealIngredient5 +
                        "\nIngredient6: " + mealIngredient6 +
                        "\nIngredient7: " + mealIngredient7 +
                        "\nIngredient8: " + mealIngredient8 +
                        "\nIngredient9: " + mealIngredient9 +
                        "\nIngredient10: " + mealIngredient10 +
                        "\nIngredient11: " + mealIngredient11 +
                        "\nIngredient12: " + mealIngredient12 +
                        "\nIngredient13: " + mealIngredient13 +
                        "\nIngredient14: " + mealIngredient14 +
                        "\nIngredient15: " + mealIngredient15 +
                        "\nIngredient16: " + mealIngredient16 +
                        "\nIngredient17: " + mealIngredient17 +
                        "\nIngredient18: " + mealIngredient18 +
                        "\nIngredient19: " + mealIngredient19 +
                        "\nIngredient20: " + mealIngredient20 +
                        "\nMeasure1: " + mealMeasure1 +
                        "\nMeasure2: " + mealMeasure2 +
                        "\nMeasure3: " + mealMeasure3 +
                        "\nMeasure4: " + mealMeasure4 +
                        "\nMeasure5: " + mealMeasure5 +
                        "\nMeasure6 : " + mealMeasure6 +
                        "\nMeasure7: " + mealMeasure7 +
                        "\nMeasure8: " + mealMeasure8 +
                        "\nMeasure9: " + mealMeasure9 +
                        "\nMeasure10: " + mealMeasure10 +
                        "\nMeasure11: " + mealMeasure11 +
                        "\nMeasure12: " + mealMeasure12 +
                        "\nMeasure13: " + mealMeasure13 +
                        "\nMeasure14: " + mealMeasure14 +
                        "\nMeasure15: " + mealMeasure15 +
                        "\nMeasure16: " + mealMeasure16 +
                        "\nMeasure17: " + mealMeasure17 +
                        "\nMeasure18: " + mealMeasure18 +
                        "\nMeasure19: " + mealMeasure19 +
                        "\nMeasure20: " + mealMeasure20+
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
                // Saving the searched movie results in the DataBase
                val meals: List<Meal> = mealDao.getAll()
                var meaDataArray: ArrayList<Meal> = ArrayList()
                var index = meals.size
                for ( i in 0 until mealNames.size){
                    index += i
                    meaDataArray.add(Meal(index, mealNames[i], mealDrinkAlternates[i], mealCategorys[i], mealAreas[i],
                        mealInstructions1[i], mealTags[i], mealYoutubes[i], mealIngredient1s[i], mealIngredient2s[i],
                        mealIngredient3s[i], mealIngredient4s[i],mealIngredient5s[i],mealIngredient6s[i],mealIngredient7s[i],
                        mealIngredient8s[i],mealIngredient9s[i],mealIngredient10s[i],mealIngredient11s[i],mealIngredient12s[i],
                        mealIngredient13s[i],mealIngredient14s[i],mealIngredient15s[i],mealIngredient16s[i],mealIngredient17s[i],
                        mealIngredient18s[i],mealIngredient19s[i],mealIngredient20s[i],mealMeasure1s[i],mealMeasure2s[i],
                        mealMeasure3s[i],mealMeasure4s[i],mealMeasure5s[i],mealMeasure6s[i],mealMeasure7s[i],mealMeasure8s[i],
                        mealMeasure9s[i],mealMeasure10s[i],mealMeasure11s[i],mealMeasure12s[i],mealMeasure13s[i],mealMeasure14s[i],
                        mealMeasure15s[i],mealMeasure16s[i],mealMeasure17s[i],mealMeasure18s[i],mealMeasure19s[i],mealMeasure20s[i]))

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