package com.oddlyspaced.mujmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.rvMenu)}
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar)}
    private val unable by lazy {findViewById<TextView>(R.id.txUnable)}
    private var getMenu = GetMenu()
    private var counter = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getMenu = GetMenu()
        getMenu.getMenu()
        updateMenu()
    }

    private fun updateMenu() {
        Handler().postDelayed(
            {
                val menu : ArrayList<MenuItem> = getMenu.menuArray
                if (counter > 0) {
                    if (menu.size > 0) {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        val adapter = MenuAdapter(menu, this)
                        recyclerView.adapter = adapter
                    } else {
                        updateMenu()
                        counter--
                    }
                }
                else {
                    unable.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
        }, 200)
    }

}
