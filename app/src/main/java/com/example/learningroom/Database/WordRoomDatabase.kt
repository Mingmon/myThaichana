package com.example.learningroom.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()

                    // Delete all content here.
                    wordDao.deleteAll()

                    // Add sample words.
                    var word = Word("Hello")
                    wordDao.insert(word)
                    word = Word("World!")
                    wordDao.insert(word)

                    // TODO: Add your own words!
                    word = Word("TODO!")
                    wordDao.insert(word)

                }
            }
        }

    }

    companion object {

        private var INSTANCE: WordRoomDatabase? = null


        fun getDatabase(
                context: Context
            ): WordRoomDatabase {


//            (
//                    context: Context,
//            scope: CoroutineScope
//            ): WordRoomDatabase {

            synchronized(this) {

                var instance = INSTANCE

//            if (tempInstance != null){
//                return tempInstance
//            }

            if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    )/*.addCallback(
                        WordDatabaseCallback(
                            scope
                        )
                    )
                        .build()*/
                        .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance

                }
                return instance
            }
        }
    }



}


