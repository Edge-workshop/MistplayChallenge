package dev.kingkongcode.mistplaychallenge.models

import org.json.JSONObject

//Class that will contain data and construct Game object from JSONObject for game
class Game(json: JSONObject) {
    var title: String = json.optString("title")
    var img: String = json.optString("img")
}