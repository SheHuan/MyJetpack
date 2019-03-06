package com.shehuan.myjetpack.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBooks(): List<Book>

    @Query("SELECT name, price FROM book")
    fun getNameAndPrice(): List<NamePrice>

    @Query("SELECT * FROM book")
    fun getAllBooks1(): LiveData<List<Book>>

    @Query("SELECT * FROM book")
    fun getAllBooks2(): Flowable<List<Book>>

    @Query("SELECT * FROM book WHERE name = :name")
    fun getBookByName(name: String): Book

    @Insert
    fun addBooks(vararg books: Book)

    @Insert
    fun addBooks1(vararg books: Book): Maybe<List<Long>>

    @Delete
    fun deleteBook(book: Book): Int

    @Update
    fun updateBook(vararg books: Book)
}