package com.example.mealapp_2

import android.content.Intent
import android.os.Bundle
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room


class MainActivity : AppCompatActivity(){

    private lateinit var saveDbButton: Button
    private lateinit var searchMealIngreButton: Button
    private lateinit var searchMealButton: Button
    private lateinit var searchMealWebButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveDbButton = findViewById(R.id.button)
        searchMealIngreButton = findViewById(R.id.button2)
        searchMealButton = findViewById(R.id.button3)
        searchMealWebButton = findViewById(R.id.searchWeb)

        saveDbButton.setOnClickListener{
            saveMealsToDb()
        }
        searchMealIngreButton.setOnClickListener{
            displaySearchIngrePage()
        }
        searchMealButton.setOnClickListener {
            displaySearchMealPage()
        }
        searchMealWebButton.setOnClickListener {
            displaySearchMealWebPage()
        }

        if (savedInstanceState != null) {
            val button1 = savedInstanceState.getBoolean("Btn1IsEnabled")
            val button2 = savedInstanceState.getBoolean("Btn2IsEnabled")
            val button3 = savedInstanceState.getBoolean("Btn3IsEnabled")
            val button4 = savedInstanceState.getBoolean("Btn3IsEnabled")

            saveDbButton.isEnabled = button1
            searchMealIngreButton.isEnabled = button2
            searchMealButton.isEnabled = button3
            searchMealWebButton.isEnabled = button4
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (saveDbButton.isEnabled) {
            outState.putBoolean("Btn1IsEnabled", true)
        } else {
            outState.putBoolean("Btn1IsEnabled", false)
        }
        if (searchMealIngreButton.isEnabled) {
            outState.putBoolean("Btn2IsEnabled", true)
        } else {
            outState.putBoolean("Btn2IsEnabled", false)
        }
        if (searchMealButton.isEnabled) {
            outState.putBoolean("Btn3IsEnabled", true)
        } else {
            outState.putBoolean("Btn3IsEnabled", false)
        }
        if (searchMealWebButton.isEnabled) {
            outState.putBoolean("Btn3IsEnabled", true)
        } else {
            outState.putBoolean("Btn3IsEnabled", false)
        }
    }

    private fun saveMealsToDb() {
        // Creating an instance of the database
        val db = Room.databaseBuilder(this, Database::class.java, "MealDB").build()
        val mealDao = db.mealDao()

        val text = "Meals Added to Database"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)

        runBlocking {
            launch {
                val meal1 = Meal(
                    1,
                    "Sweet and Sour Pork",
                    null,
                    "Pork",
                    "Chinese",
                    "Preparation\r\n1. " +
                            "Crack the egg into a bowl. Separate the egg white and yolk." +
                            "Sweet and Sour Pork\r\n2. Slice the pork tenderloin into ips." +
                            "3. Prepare the marinade using a pinch of salt, one teaspoon of starch, " +
                            "two teaspoons of light soy sauce, and an egg white." +
                            "4. Marinade the pork ips for about 20 minutes.\r\n\r\n" +
                            "5. Put the remaining starch in a bowl. Add some water and vinegar to " +
                            "make a starchy sauce." +
                            "Sweet and Sour Pork\r\nCooking Inuctions\r\n" +
                            "1. Pour the cooking oil into a wok and heat to 190\u00b0C (375\u00b0F). " +
                            "Add the marinated pork ips and fry them until they turn brown. Remove " +
                            "the cooked pork from the wok and place on a plate.\r\n\r\n" +
                            "2. Leave some oil in the wok. Put the tomato sauce and white sugar " +
                            "into the wok, and heat until the oil and sauce are fully combined.\r\n\r\n" +
                            "3. Add some water to the wok and thoroughly heat the sweet and sour " +
                            "sauce before adding the pork ips to it.\r\n\r\n4. Pour in the starchy " +
                            "sauce. Stir-fry all the ingredients until the pork and sauce are " +
                            "thoroughly mixed together.\r\n\r\n5. Serve on a plate and add some " +
                            "coriander for decoration.",
                    "Sweet",
                    "https://www.youtube.com/watch?v=mdaBIhgEAMo",
                    "200g Pork",
                    "1 Egg",
                    "Water",
                    "Salt",
                    "Sugar",
                    "Soy Sauce",
                    "Starch",
                    "Tomato Puree",
                    "Vinegar",
                    "Coriander",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,


                    "1",
                    "Dash",
                    "1/2 tsp",
                    "1 tsp ",
                    "10g",
                    "10g",
                    "30g",
                    "10g",
                    "Dash",
                    "2 cups chicken broth",
                    "1 cup heavy cream",
                    "Zest of 1 lemon",
                    "Juice of 1/2 lemon",
                    "1/4 cup grated parmesan cheese",
                    "Fresh parsley, chopped, for garnish",
                    null,
                    null,
                    null,
                    null,
                    null,

                    )

                val meal2 = Meal(
                    2,
                    "Chicken Marengo",
                    null,
                    "Chicken",
                    "French",
                    "Heat the oil in a large flameproof casserole dish and stir-fry the" +
                            " mushrooms until they start to soften. Add the chicken legs and cook" +
                            " briefly on each side to colour them a little.Pour in the passata, " +
                            "crumble in the stock cube and stir in the olives. Season with black pepper " +
                            "you shouldn't need salt. Cover and simmer for 40 mins until the chicken " +
                            "is tender. Sprinkle with parsley and serve with pasta and a salad, or mash " +
                            "and green veg, if you like.",

                    null,
                    "https://www.youtube.com/watch?v=U33HYUr-0Fw",
                    "Olive Oil",
                    "Mushrooms",
                    "Chicken Legs",
                    "Passata",
                    "Chicken Stock Cube",
                    "Black Olives",
                    "Parsley",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,

                    "1 tbs",
                    "300g",
                    "4",
                    "500g",
                    "1",
                    "100g ",
                    "Chopped",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,




                    )

                val meal3 = Meal(
                    3,
                    "Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber",
                    null,
                    "Beef",
                    "Vietnamese",
                    "Add'l ingredients: mayonnaise, siracha\r\n\r\n1. Place rice in a " +
                            "fine-mesh sieve and rinse until water runs clear. Add to a small pot with 1 " +
                            "cup water (2 cups for 4 servings) and a pinch of salt. Bring to a boil, " +
                            "then cover and reduce heat to low. Cook until rice is tender, 15 minutes. " +
                            "Keep covered off heat for at least 10 minutes or until ready to serve." +
                            "\r\n\r\n2. Meanwhile, wash and dry all produce. Peel and finely chop " +
                            "garlic. Zest and quarter lime (for 4 servings, zest 1 lime and quarter both). " +
                            "Trim and halve cucumber lengthwise; thinly slice crosswise into half-moons. " +
                            "Halve, peel, and medium dice onion. Trim, peel, and grate carrot." +
                            "\r\n\r\n3. In a medium bowl, combine cucumber, juice from half the lime," +
                            " \u00bc tsp sugar (\u00bd tsp for 4 servings), and a pinch of salt. In a small bowl, " +
                            "combine mayonnaise, a pinch of garlic, a squeeze of lime juice, and as much " +
                            "sriracha as you\u2019d like. Season with salt and pepper." +
                            "\r\n\r\n4. Heat a drizzle of oil in a large pan over medium-high heat." +
                            " Add onion and cook, stirring, until softened, 4-5 minutes. Add beef, remaining garlic, " +
                            "and 2 tsp sugar (4 tsp for 4 servings). Cook, breaking up meat into pieces, " +
                            "until beef is browned and cooked through, 4-5 minutes. Stir in soy sauce. " +
                            "Turn off heat; taste and season with salt and pepper.\r\n\r\n5. " +
                            "Fluff rice with a fork; stir in lime zest and 1 TBSP butter. Divide rice between bowls. " +
                            "Arrange beef, grated carrot, and pickled cucumber on top. Top with a squeeze of lime juice." +
                            " Drizzle with sriracha mayo.",
                    null,
                    null,
                    "Rice",
                    "Onion",
                    "Lime",
                    "Garlic Clove",
                    "Cucumber",
                    "Carrots",
                    "Ground Beef",
                    "Soy Sauce",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,

                    "White",
                    "1",
                    "1",
                    "3",
                    "1",
                    "3 oz ",
                    "1 lb",
                    "2 oz ",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,


                    )

                val meal4 = Meal(
                    4,
                    "Leblebi Soup",
                    null,
                    "Vegetarian",
                    "Tunisian",
                    "Heat the oil in a large pot. Add the onion and cook until translucent." +
                            "\r\nDrain the soaked chickpeas and add them to the pot together with " +
                            "the vegetable stock. Bring to the boil, then reduce the heat and cover. " +
                            "Simmer for 30 minutes.\r\nIn the meantime toast the cumin in a small " +
                            "ungreased frying pan, then grind them in a mortar. Add the garlic and salt " +
                            "and pound to a fine paste.\r\nAdd the paste and the harissa to the soup " +
                            "and simmer until the chickpeas are tender, about 30 minutes.\r\nSeason to " +
                            "taste with salt, pepper and lemon juice and serve hot.",
                    "Soup",
                    "https://www.youtube.com/watch?v=BgRifcCwinY",
                    "Olive Oil",
                    "Onion",
                    "Chickpeas",
                    "Vegetable Stock",
                    "Cumin",
                    "Garlic",
                    "Salt",
                    "Harissa Spice",
                    "Pepper",
                    "Lime",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,

                    "2 tbs",
                    "1 medium finely diced",
                    "250g",
                    "1.5L",
                    "1 tsp ",
                    "5 cloves",
                    "1/2 tsp",
                    "1 tsp ",
                    "Pinch",
                    "1/2 ",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,

                    )



                // Populating Database
                mealDao.insertMeals(meal1)
                mealDao.insertMeals(meal2)
                mealDao.insertMeals(meal3)
                mealDao.insertMeals(meal4)
                toast.show()

                // Displaying DB table data with activity
                dbTablePage()
            }
        }
    }

    private fun dbTablePage() {
        val intent = Intent(this, DisplayDB::class.java)
        startActivity(intent)
    }

    fun displaySearchIngrePage() {
        val intent = Intent(this, SearchIngredient::class.java)
        startActivity(intent)
    }

    fun displaySearchMealPage() {
        val intent = Intent(this, SearchMeal::class.java)
        startActivity(intent)
    }

    fun displaySearchMealWebPage() {
        val intent = Intent(this, SearchWeb::class.java)
        startActivity(intent)
    }
}