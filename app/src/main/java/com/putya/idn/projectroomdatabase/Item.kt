package com.putya.idn.projectroomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey @ColumnInfo(name = "item")
    val item: String
//    val age: Int
)