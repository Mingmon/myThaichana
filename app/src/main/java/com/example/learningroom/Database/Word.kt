package com.example.learningroom.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "word_table")

data class Word(

    @PrimaryKey @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "datetime")
    val datetime: String,

    @ColumnInfo(name = "checkout")
    val checkout: String? = null

)

