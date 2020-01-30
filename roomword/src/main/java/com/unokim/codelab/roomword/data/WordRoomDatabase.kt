package com.unokim.codelab.roomword.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            instance?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here.
            wordDao.deleteAll()

            // Add sample words.
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }

    companion object {
        @Volatile
        private var instance: WordRoomDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): WordRoomDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context,
                            scope
                        ).also { instance = it }
                }
        }

        private fun buildDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {
            return Room.databaseBuilder(context, WordRoomDatabase::class.java, "word_database")
                .addCallback(
                    WordDatabaseCallback(
                        scope
                    )
                )
                .build()
        }
    }
}