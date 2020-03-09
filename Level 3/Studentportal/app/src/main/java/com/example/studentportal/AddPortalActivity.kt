package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews(){
        btnAddPortal.setOnClickListener{onAddClick()}
    }

    private fun onAddClick(){
        if(etName.text.toString().isNotBlank() && etUri.text.toString().isNotBlank()){
            val portal = Portal(etName.text.toString(), Uri.parse(etUri.text.toString()))
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this,"The name and uri can't be empty!"
            , Toast.LENGTH_SHORT).show()
        }
    }
}
