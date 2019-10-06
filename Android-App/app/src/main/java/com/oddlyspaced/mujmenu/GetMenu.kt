package com.oddlyspaced.mujmenu

import android.util.Log
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

// gets menu from json link and returns array list of menu object
class GetMenu {

    private val url : String = "https://raw.githubusercontent.com/MFOSSociety/MMMenu/master/scraper/menu.json"
    public var menuArray : ArrayList<MenuItem> = ArrayList()


    fun getMenu() {
        menuArray = ArrayList()
        doAsync {
            val apiResponse : String = URL(url).readText()
            menuArray = createArray(apiResponse)
        }
    }


    private fun createArray(json_string : String) : ArrayList<MenuItem> {
        val json_obj = JSONObject(json_string)
        val menu_obj : JSONArray = json_obj.getJSONArray("menu")
        val menu_array : ArrayList<MenuItem> = ArrayList()
        for (i in 0 until menu_obj.length()) {
            val item = menu_obj.getJSONObject(i)
            menu_array.add(MenuItem(item.getString("time"), formatMenu(item.getString("food")).trim()))
        }
        return menu_array
    }

    private fun formatMenu(str : String) : String {
        val st = StringTokenizer(str, "#")
        var op = ""
        for (item in st) {
            op = "• ${item.toString().toTitleCase()}\n$op"
        }
        return op
    }

    private fun String.toTitleCase() : String {
        val st = StringTokenizer(this)
        var op = ""
        for (item in st) {
            val s = item.toString()
            op = op + " " +  s[0].toUpperCase() + s.toLowerCase().substring(1)
        }
        return op.trim()
    }

}