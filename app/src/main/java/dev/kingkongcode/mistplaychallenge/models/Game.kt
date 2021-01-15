package dev.kingkongcode.mistplaychallenge.models

import dev.kingkongcode.mistplaychallenge.util.Constants
import org.json.JSONObject

//Class that will contain data and construct Game object from JSONObject for game
class Game {
    var title: String = Constants.instance.emptyString
    var img: String = Constants.instance.emptyString

    constructor(json: JSONObject) {
        this.title = json.optString("title")
        this.img = json.optString("img")
    }
}