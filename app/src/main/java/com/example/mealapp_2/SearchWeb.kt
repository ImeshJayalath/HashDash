package com.example.mealapp_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private var searchMealWebBox: EditText? = null
private var searchMealWebBtn: Button? = null
private var resultsMealWebArea: TextView? = null
private var mealName: String = ""
private var urlString: String = ""
private var dbData: String = ""
private var finalResult: String = ""

class SearchWeb : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_meal)


        searchMealWebBox = findViewById(R.id.searchMealWebEt)
        searchMealWebBtn = findViewById(R.id.searchForMealWebBtn)
        resultsMealWebArea = findViewById(R.id.mealWebTv)
        searchMealWebBtn?.setOnClickListener {
            getMeal()
        }
    }
    private fun getMeal() {
        // !! - converts any value to a non-null type and throws an exception if the value is null
        // trim() - removes whitespace from both ends of a string
        val meal = searchMealWebBox!!.text.toString().lowercase().trim()
        if (meal  == "")
            return

        // Setting up the fetching URL for the web service

        var stb = StringBuilder()
        urlString = "https://www.themealdb.com/api/json/v1/1/search.php?s=$meal"
        val url = URL(urlString)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        // Start fetching data in the background - Not in main thread
        runBlocking {
            withContext(Dispatchers.IO){
                // whole JSON data
                var bf = BufferedReader(InputStreamReader(con.inputStream))
                var line: String? = bf.readLine()
                while (line != null) {
                    stb.append(line + "\n")
                    line = bf.readLine()

                }

                dbData = parseJSON(stb)
            }
            resultsMealWebArea?.setText(dbData)
        }
    }

    /**
     * Reading the Json data from the string buffer object and
     * retrieving the necessary information
     * */
    private fun parseJSON(stb: StringBuilder): String {
        // Displaying proper message for empty results

        // Extracting the actual data from the JSON data
        val json = JSONObject(stb.toString())
        val mealArray = json.getJSONArray("meals")
        val mealArrayLength = mealArray.length()
        //val mealObject = mealArray.getJSONObject(0)
        println(json)
        try{
            for(i in 0 until mealArrayLength){
                val mealObject = mealArray.getJSONObject(i)

                mealName = mealObject.getString("strMeal")



                finalResult += "\nMeal: " +  mealName +
                        "\n\n"

                print("this is running ")

            }

        } catch (e: JSONException){
            println("**************************")
            e.printStackTrace()
            println("**************************")

            finalResult =  "\n|------------------ Meal search Failed -------------------|\n" +

                    "|---------------------------- Try again --------------------------|"
        }

        return finalResult;

    }
}