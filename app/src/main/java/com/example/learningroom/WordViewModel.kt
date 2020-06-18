package com.example.learningroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learningroom.Database.Word
import com.example.learningroom.Database.WordRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository

    val allWords: LiveData<List<Word>>

    val notcheckout: LiveData<List<Word>>

    private lateinit var liveUpdate : MutableLiveData<Word>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()

//        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()

        repository = WordRepository(wordsDao)

        allWords = repository.allWords

        notcheckout = repository.notcheckout



    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO ) {
        repository.insert(word)
    }

    fun update(word: String) = viewModelScope.launch(Dispatchers.IO ) {
        repository.update(word)
    }





}