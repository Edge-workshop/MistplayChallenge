package dev.kingkongcode.mistplaychallenge.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kingkongcode.mistplaychallenge.R
import dev.kingkongcode.mistplaychallenge.models.GamesCategories
/**
 * This Class adapter is the one that show in the RecycleView (Vertical) all the games categories from the fake JSONObject.
 * It have as arguments the context and the list<GamesCategories>
 * */
class CategoryAdapter(private val dataSet: List<GamesCategories>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(), View.OnClickListener {
    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvCategoryTitle: TextView = itemView.findViewById(R.id.tvCategoryTitle)
        private var rvGamesList: RecyclerView = itemView.findViewById(R.id.rvGamesList)

        fun bind(category: GamesCategories, position: Int) {
            //Show the game category title
            tvCategoryTitle.text = category.listTitle
            //Setting the 2nd RecycleView and is Orientation to be Horizontal
            rvGamesList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            //Adding the adapter to the RecycleView
            rvGamesList.adapter = GameAdapter(category.games)

        }
    }

    override fun onClick(p0: View?) {}
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //View of the layout that will be inflated
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_cell, parent, false)
        val holder = ViewHolder(view)
        //Add a viewPool to increase performance of RecycleView
        holder.itemView.findViewById<RecyclerView>(R.id.rvGamesList).setRecycledViewPool(viewPool)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], position)
    }
}