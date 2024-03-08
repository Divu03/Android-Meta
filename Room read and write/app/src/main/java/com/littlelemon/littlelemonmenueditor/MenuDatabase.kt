package com.littlelemon.littlelemonmenueditor

import androidx.room.Database
import androidx.room.RoomDatabase
import com.littlelemon.littlelemonmenueditor.MenuDAO

@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
public abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDAO
}