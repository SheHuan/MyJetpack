package com.shehuan.myjetpack.room

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import android.content.Context

@Database(entities = [Book::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookDb : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: BookDb? = null

        fun getInstance(context: Context): BookDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): BookDb {
            return Room.databaseBuilder(context, BookDb::class.java, "book-db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build()
        }


        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tb_book ADD COLUMN author TEXT NOT NULL DEFAULT ''")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tb_book RENAME TO book")
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE book ADD COLUMN publish_date INTEGER")
            }
        }
    }

}