package com.shehuan.myjetpack.room

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shehuan.myjetpack.R
import java.util.*
import androidx.lifecycle.Observer
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {
    companion object {
        fun start(context: AppCompatActivity) {
            val intent = Intent(context, RoomActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        test()
        subsetsColumnsTest()
        useLiveData()
        useRxJava()
    }

    @SuppressLint("CheckResult")
    fun addBook(view: View) {
        if (TextUtils.isEmpty(nameEt.text) || TextUtils.isEmpty(priceEt.text) || TextUtils.isEmpty(authorEt.text)) {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show()
            return
        }

        val name = nameEt.text.toString()
        val price = priceEt.text.toString().toFloat()
        val author = authorEt.text.toString()

        BookDb.getInstance(this).bookDao()
            .addBooks1(Book(0, name, price, author, Date()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                it?.run {
                    Log.e("Insert By RxJava", it.toString())
                }
            }

        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show()
        nameEt.setText("")
        priceEt.setText("")
        authorEt.setText("")
    }

    fun updateBook(view: View) {
        if (TextUtils.isEmpty(nameEt.text) || TextUtils.isEmpty(priceEt.text)) {
            Toast.makeText(this, "请输入书名、价格", Toast.LENGTH_SHORT).show()
            return
        }
        var book = BookDb.getInstance(this).bookDao().getBookByName(nameEt.text.toString())
        book.price = priceEt.text.toString().toFloat()
        BookDb.getInstance(this).bookDao().updateBook(book)
        book = BookDb.getInstance(this).bookDao().getBookByName(nameEt.text.toString())
        Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show()
        Log.e("Update Book", book.toString())

    }

    fun deleteBook(view: View) {
        if (TextUtils.isEmpty(nameEt.text)) {
            Toast.makeText(this, "请输入书名", Toast.LENGTH_SHORT).show()
            return
        }

        val book = BookDb.getInstance(this).bookDao().getBookByName(nameEt.text.toString())
        val row = BookDb.getInstance(this).bookDao().deleteBook(book)
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
        nameEt.setText("")
        Log.e("Delete Book", row.toString())
    }

    private fun test() {
        val books: List<Book> = BookDb.getInstance(this).bookDao().getAllBooks()
        for (book in books) {
            Log.e("Book", book.toString())
        }
    }

    private fun subsetsColumnsTest() {
        val books: List<NamePrice> = BookDb.getInstance(this).bookDao().getNameAndPrice()
        for (book in books) {
            Log.e("NamePrice", book.toString())
        }
    }

    private fun useLiveData() {
        val books: LiveData<List<Book>> = BookDb.getInstance(this).bookDao().getAllBooks1()
        books.observe(this, Observer<List<Book>> {
            it?.run {
                for (book in this) {
                    Log.e("Query By LiveDate", book.toString())
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun useRxJava() {
        val books: Flowable<List<Book>> = BookDb.getInstance(this).bookDao().getAllBooks2()
        books.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                it?.run {
                    for (book in this) {
                        Log.e("Query By RxJava", book.toString())
                    }
                }
            }
    }
}
