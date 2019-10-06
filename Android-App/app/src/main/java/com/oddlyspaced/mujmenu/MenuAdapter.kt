package com.oddlyspaced.mujmenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MenuAdapter(val list : ArrayList<MenuItem>, val context : Context?) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : MenuItem = list.get(position)
        //var m = item.menu
        //val tokenizer = StringTokenizer(m,"#")
        /*var menu = ""
        for (token  in tokenizer) {
            val s = token.toString()
            s.toLowerCase()
            val tokenMenuItem = StringTokenizer(s)
            var word = ""
            for (t in tokenMenuItem) {
                word = word + t.toString().substring(0, 1).toUpperCase() + t.toString().substring(1)
            }
            menu += "• $word\n"
        }*/

        holder.time.text = item.time
        holder.food.text = item.menu

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val time : TextView = itemView.findViewById(R.id.txMenuTime)
        val food : TextView = itemView.findViewById(R.id.txMenuFood)

    }

}