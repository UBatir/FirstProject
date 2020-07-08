package com.example.loansdebts

import android.icu.text.Transliterator
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.loansdebts.data.NotebookDatabase
import com.example.loansdebts.data.dao.ContactDao
import com.example.loansdebts.data.model.Contact
import com.example.loansdebts.ui.CustomDialog
import com.example.loansdebts.ui.DialogRename
import com.example.loansdebts.ui.ListAdapter
import kotlinx.android.synthetic.main.content_main.recyclerView


class MainActivity : AppCompatActivity() {

    private val adapter=ListAdapter(this)
    private lateinit var dao:ContactDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        recyclerView.adapter=adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        dao=NotebookDatabase.getInstance(this).dao()
        adapter.models= dao.getAllContact()
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val dialog=CustomDialog(this,this)
            dialog.show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle=ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_qosiw -> {
                    val dialog = CustomDialog(this, this)
                    dialog.show()
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_tariyx -> {
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_rezerv -> {
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_sazlaw -> {
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_dastur -> {
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    fun addContact(contact:Contact){
        dao.insertContact(contact)
        adapter.models= dao.getAllContact()
    }

    private fun removeContact(contact: Contact){
        dao.deleteContact(contact)
        adapter.models=dao.getAllContact()
    }

    fun renameContact(contact: Contact){
        dao.updateContact(contact)
        adapter.models=dao.getAllContact()
    }


    fun onOptionsButtonClick(view: View,contact: Contact,id:Int){
        val optionsMenu=PopupMenu(this,view)
        val menuInflater=optionsMenu.menuInflater
        menuInflater.inflate(R.menu.menu_item_options,optionsMenu.menu)
        optionsMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.itemRename->{
                    val dialog=DialogRename(id,this)
                    dialog.show()
                }
                R.id.itemDelete -> {
                    val dialog=AlertDialog.Builder(this)
                    dialog.setTitle("Oshiriw")
                    dialog.setMessage("ofnonvjna")
                    dialog.setPositiveButton("Oshiriw"){_,_->
                        removeContact(contact)
                    }
                    dialog.setNegativeButton("Biykarlaw"){_,_->
                    }
                    dialog.show()
                }
            }
            return@setOnMenuItemClickListener true
        }
        optionsMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}