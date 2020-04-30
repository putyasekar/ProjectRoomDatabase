package com.putya.idn.projectroomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.putya.idn.projectroomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val newItemInputRequestCode = 1
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNUSED_VARIABLE")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val recyclerView = binding.rvMain
        val adapterItem = ItemAdapter()

        recyclerView.adapter = adapterItem
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        itemViewModel.allItems.observe(this, Observer { item ->
            item?.let {
                adapterItem.setItem(it)
            }
        })

        val fab = binding.fbAdd
        fab.setOnClickListener {
            val intentToNewActivity = Intent(this, InputWordActivity::class.java)
            startActivityForResult(intentToNewActivity, newItemInputRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newItemInputRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let { data ->
                val item = Item(data.getStringExtra(InputWordActivity.EXTRA_REPLAY))
                itemViewModel.insert(item)
                Unit
            }
        } else {
            Toast.makeText(applicationContext, getString(R.string.empty), Toast.LENGTH_SHORT).show()
        }
    }
}
