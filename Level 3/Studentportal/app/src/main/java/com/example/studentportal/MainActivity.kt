package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val openUriFunction = {p:Portal ->
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ResourcesCompat.getColor(resources,R.color.colorPrimaryDark,null))
        customTabsIntent.launchUrl(this, p.uri)
    }

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals, openUriFunction)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startAddActivity()
        }

        initViews()
    }

    private fun initViews(){
        rvPortals.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        rvPortals.adapter = portalAdapter
    }

    private fun startAddActivity(){
        val intent = Intent(this, AddPortalActivity::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val reminder = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(reminder)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addPortal(name: String, uri: Uri){
        if(name.isNotBlank()) {
            portals.add(Portal(name, uri))
            portalAdapter.notifyDataSetChanged()
        }
    }
}
