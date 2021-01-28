package dev.kingkongcode.mistplaychallenge.models

import org.json.JSONObject

//Class that will contain data and construct GamesCategories object from JSONObject for category
class GamesCategories(json: JSONObject) {
    var listTitle:String = json.optString("list_title")
    var games:ArrayList<Game> = arrayListOf()

    init {
        json.optJSONArray("games")?.let {
            for (index in 0 until it.length()) {
                val game = it.optJSONObject(index)
                this.games.add(Game(game))
            }
        }
    }
}