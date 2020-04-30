package com.putya.idn.projectroomdatabase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ItemRepo(private val itemDAO: ItemDAO) {
    val allItem: LiveData<List<Item>> = itemDAO.getAlphabetWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: Item) {
        itemDAO.insert(item)
    }
}