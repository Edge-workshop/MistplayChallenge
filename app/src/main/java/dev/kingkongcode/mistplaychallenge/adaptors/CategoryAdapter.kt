package dev.kingkongcode.mistplaychallenge.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kingkongcode.mistplaychallenge.R
import dev.kingkongcode.mistplaychallenge.models.GamesCategories
/**
 * This Class adapter is the one that show in the RecycleView (Vertical) all the games categories from the fake JSONObject.
 * It have as arguments the context and the list<GamesCategories>
 * */
class CategoryAdapter(private val mContext: Context, private val dataSet: List<GamesCategories>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(), View.OnClickListener {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvCategoryTitle: TextView = itemView.findViewById(R.id.tvCategoryTitle)
        private var rvGamesList: RecyclerView = itemView.findViewById(R.id.rvGamesList)
        private lateinit var linearLayoutManager: LinearLayoutManager
        private lateinit var categoryAdapter: GameAdapter

        fun bind(category: GamesCategories, position: Int) {
            //Show the game category title
            tvCategoryTitle.text = category.listTitle
            //Setting the 2nd RecycleView and is Orientation to be Horizontal
            linearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            rvGamesList.layoutManager = linearLayoutManager
            //Passing all the games from that category through a List to the GameAdapter class
            categoryAdapter = GameAdapter(mContext, category.games)
            //Adding the adapter to the RecycleView
            rvGamesList.adapter = categoryAdapter
        }
    }

    override fun onClick(p0: View?) {}
    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //View of the layout that will be inflated
        val layout =  R.layout.category_cell
        val view = LayoutInflater.from(mContext).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = dataSet[position]
        holder.bind(category, position)
    }
}