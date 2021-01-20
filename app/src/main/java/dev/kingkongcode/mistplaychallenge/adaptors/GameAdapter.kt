package dev.kingkongcode.mistplaychallenge.adaptors

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kingkongcode.mistplaychallenge.R
import dev.kingkongcode.mistplaychallenge.models.Game

/**
 * This Class adapter is the one that show in the RecycleView (Horizontal) all the games from a specific category from the fake JSONObject.
 * It have as arguments the context and the list<Game>
 * */
class GameAdapter(private val dataSet: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>(), View.OnClickListener {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivGameImage: ImageView = itemView.findViewById(R.id.ivGameImage)
        var tvGameTitle: TextView = itemView.findViewById(R.id.tvGameTitle)

        fun bind(game: Game, position: Int) {
            //Showing the game title
            tvGameTitle.text = game.title
            //Showing the game image if there is one available or if not showing company logo
            Glide.with(itemView.context).load(game.img).error(R.drawable.mistplay_logo).into(ivGameImage)
            ivGameImage.setOnClickListener {
                //By clicking on the image app will show a bigger object of the game selected on a Dialog box
                showDialog(game.img, game.title)
            }
        }

        private fun showDialog(mImage: String, mTitle: String) {
            //Initiate the dialog box
            val dialog = Dialog(itemView.context).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                //User can click anywhere outside dialog box to cancel it
                setCancelable(true)
                setContentView(R.layout.game_poster_display)
            }
            //Showing game image or company logo if there is none
            dialog.findViewById<ImageView>(R.id.ivGamePoster)?.let {
                Glide.with(itemView.context).load(mImage).error(R.drawable.mistplay_logo).into(it)
            }
            //Showing game title
            dialog.findViewById<TextView>(R.id.tvGameName).apply { mTitle }
            //A way for the user to click on it to dismiss the dialog
            dialog.findViewById<ConstraintLayout>(R.id.mainLayout).apply {
                setOnClickListener { dialog.dismiss() }
            }

            dialog.show()
        }
    }

    override fun onClick(p0: View?) {}
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //View that will be inflate
        val view = LayoutInflater.from(parent.context).inflate(R.layout.games_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], position)
    }
}