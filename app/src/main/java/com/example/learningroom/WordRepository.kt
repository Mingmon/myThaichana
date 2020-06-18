package com.example.learningroom

import androidx.lifecycle.LiveData
import com.example.learningroom.Database.Word
import com.example.learningroom.Database.WordDao

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    val notcheckout: LiveData<List<Word>> = wordDao.notcheckoutdata()

    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

    suspend fun update(word: String){
        wordDao.update(word)
    }




}