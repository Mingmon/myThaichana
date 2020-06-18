package com.example.learningroom.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Query("SELECT * from word_table WHERE checkout LIKE 0 ")
    fun notcheckoutdata(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()


    @Query("UPDATE word_table SET checkout = 1 WHERE word = :word")
    suspend fun update(word: String)

    @Delete
    suspend fun delete(word: Word)










}