package com.android.appinesstask.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {


    @TypeConverter
    fun fromImagePath(imagePath: ImagePath): String {
        val gson = Gson()
        return gson.toJson(imagePath)
    }

    @TypeConverter
    fun toImagePath(imagePathString: String): ImagePath {
        val gson = Gson()
        val type = object : TypeToken<ImagePath>() {}.type
        return gson.fromJson(imagePathString, type)
    }

    @TypeConverter
    fun fromList(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}