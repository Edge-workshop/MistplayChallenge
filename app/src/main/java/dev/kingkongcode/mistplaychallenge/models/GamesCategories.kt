package dev.kingkongcode.mistplaychallenge.models

import dev.kingkongcode.mistplaychallenge.util.Constants
import org.json.JSONObject

//Class that will contain data and construct GamesCategories object from JSONObject for category
class GamesCategories {
    var listTitle: String = Constants.instance.emptyString
    var games: ArrayList<Game> = arrayListOf()

    constructor(json: JSONObject) {
        this.listTitle = json.optString("list_title")
        json.optJSONArray("games")?.let {
            for (index in 0 until it.length()) {
                val game = it.optJSONObject(index)
                this.games.add(Game(game))
            }
        }
    }
}