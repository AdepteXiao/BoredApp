package com.example.test.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "fav"
)
data class FavEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val activity: String,
    val type: String,
    val participants: String,
    val price: String
)

@Entity(
    tableName = "history"
)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val activity: String,
    val type: String,
    val participants: String,
    val price: String,
    val note: String = "How did it go?",
    val location: String? = null
)