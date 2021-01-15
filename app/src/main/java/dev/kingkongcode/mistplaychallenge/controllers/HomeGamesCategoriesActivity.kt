package dev.kingkongcode.mistplaychallenge.controllers

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.kingkongcode.mistplaychallenge.R
import dev.kingkongcode.mistplaychallenge.adaptors.CategoryAdapter
import dev.kingkongcode.mistplaychallenge.databinding.ActivityHomeGamesCategoriesBinding
import dev.kingkongcode.mistplaychallenge.models.GamesCategories
import dev.kingkongcode.mistplaychallenge.util.BaseActivity
import dev.kingkongcode.mistplaychallenge.util.Constants
import org.json.JSONArray

class HomeGamesCategoriesActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeGamesCategoriesBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var categoryAdapter: CategoryAdapter
    private var categoryList = arrayListOf<GamesCategories>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View binding
        binding = ActivityHomeGamesCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing and setting RecycleView (default orientation = Vertical) with adapter
        linearLayoutManager = LinearLayoutManager(this@HomeGamesCategoriesActivity)
        binding.rvGamesCategories.layoutManager = linearLayoutManager
        categoryAdapter = CategoryAdapter(this@HomeGamesCategoriesActivity, categoryList)
        binding.rvGamesCategories.adapter = categoryAdapter
    }

    override fun onResume() {
        super.onResume()

        var tempCategoryList = arrayListOf<GamesCategories>()
        //Fake JSONObject response from Challenge documents
        val jsonArray = JSONArray(Constants.instance.fakeJsonObjResponse)
        for (index in 0 until jsonArray.length()) {
            val jsonObj = jsonArray.getJSONObject(index)
            val category = GamesCategories(jsonObj)
            tempCategoryList.add(category)
        }

        //Put all Category objects in the specific rvGamesCategories adapter list
        categoryList.clear()
        categoryList.addAll(tempCategoryList)
        binding.rvGamesCategories.adapter?.notifyDataSetChanged()

        //Code section for Bottom Navigation menu item
        /** P.s didn't do anything big just link each one of them with their own specific Toast**/
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_game -> {
                    //Created a function to display toast
                    displayToast(getString(R.string.games))
                    true
                }

                R.id.menu_chat -> {
                    //Created a function to display toast
                    displayToast(getString(R.string.chat))
                    true
                }

                R.id.menu_shop -> {
                    //Created a function to display toast
                    displayToast(getString(R.string.shop))
                    true
                }

                R.id.menu_bonus -> {
                    //Created a function to display toast
                    displayToast(getString(R.string.bonus))
                    true
                }

                R.id.menu_profile -> {
                    //Created a function to display toast
                    displayToast(getString(R.string.profile))
                    true
                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {
        /** Decided to create a warning to display for the user if he really wanted to exit the app **/
        // Initialize a new instance
        val builder = AlertDialog.Builder(this@HomeGamesCategoriesActivity)
        // Set the alert dialog title
        builder.setTitle(getString(R.string.exit))
        // Display a message on alert dialog
        builder.setMessage(getString(R.string.exit_app))
        // Set a positive button
        builder.setPositiveButton(getString(R.string.yes)){_, _ ->
            finish()
        }
        // Display a negative button on alert dialog
        builder.setNegativeButton(getString(R.string.no)){dialog,_ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        // Display the alert dialog on app interface
        dialog.show()
    }

    //Function display a toast for the bottom navigation
    private fun displayToast(menuSelected: String) {
        Toast.makeText(this@HomeGamesCategoriesActivity, "Menu "+menuSelected+" "+getString(R.string.menu_not_implemented), Toast.LENGTH_SHORT).show()
    }
}