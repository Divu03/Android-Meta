package com.littlelemon.littlelemonmenueditor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MenuDAO {
    @Query("SELECT * FROM menuitem")
    fun getAllMenuItems():LiveData<List<MenuItem>>

    @Insert
    fun saveAllMenuItems(menuItem: MenuItem)

    @Delete
    fun deleteAllItems(menuItem: MenuItem)
}