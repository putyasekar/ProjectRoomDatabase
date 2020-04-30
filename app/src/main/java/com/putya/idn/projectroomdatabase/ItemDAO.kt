package com.putya.idn.projectroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDAO {
    @Query("SELECT * FROM item_table ORDER BY item ASC")
    fun getAlphabetWords(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Item)

    @Query("DELETE FROM item_table")
    fun deleteAll()
}