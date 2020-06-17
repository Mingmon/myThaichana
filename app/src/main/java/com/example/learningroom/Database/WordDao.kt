package com.example.learningroom.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Query("SELECT * from word_table WHERE checkout IS NULL")
    fun notcheckoutdata(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)










}