package com.putya.idn.projectroomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(itemApp: Application) : AndroidViewModel(itemApp) {
    private val repository: ItemRepo

    val allItems: LiveData<List<Item>>

    init {
        val wordsDao = ItemRoomDB.getDB(itemApp, viewModelScope).itemDao()
        repository = ItemRepo(wordsDao)
        allItems = repository.allItem
    }

    fun insert(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}