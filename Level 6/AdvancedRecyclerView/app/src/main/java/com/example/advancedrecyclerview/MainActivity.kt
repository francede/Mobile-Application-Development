package com.example.advancedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val colors = arrayListOf<ColorItem>()
    private val colorAdapter = ColorAdapter(colors, { colorItem -> onColorClick(colorItem) })
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initViews()
    }

    private fun onColorClick(colorItem: ColorItem) {
        Snackbar.make(rvColors, "This color is: ${colorItem.name}", Snackbar.LENGTH_LONG).show()
    }

    private fun initViews() {
        rvColors.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvColors.adapter = colorAdapter
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider (this).get(MainActivityViewModel::class.java)

        viewModel.colorItems.observe(this, Observer {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        })
    }
}
