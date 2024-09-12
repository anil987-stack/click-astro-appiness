package com.android.appinesstask.dao

import com.android.appinesstask.model.ProductModel;
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.appinesstask.model.Converters

@Database(entities = [ProductModel::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
