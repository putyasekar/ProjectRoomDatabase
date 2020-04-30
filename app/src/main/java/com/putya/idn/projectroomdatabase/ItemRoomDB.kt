package com.putya.idn.projectroomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Item::class], version = 1)
abstract class ItemRoomDB : RoomDatabase() {
    abstract fun itemDao(): ItemDAO

    companion object {

        @Volatile
        private var INSTANCE: ItemRoomDB? = null

        fun getDB(context: Context, scope: CoroutineScope): ItemRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ItemRoomDB::class.java, "item_db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ItemDBCallBack(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class ItemDBCallBack(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    accessDatabase(database.itemDao())
                }
            }
        }

        private fun accessDatabase(itemDao: ItemDAO) {

            itemDao.deleteAll()

            var item = Item("Hi Putya Have a Nice Day!")
            itemDao.insert(item)

            item = Item("Halo Putya!")
            itemDao.insert(item)
        }
    }
}