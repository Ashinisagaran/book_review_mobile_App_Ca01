package org.setu.bookReview.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.setu.bookReview.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "bookReview.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<BookReviewModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BookReviewJSONStore : BookReviewStore {

    var bookReviews = mutableListOf<BookReviewModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BookReviewModel> {
        return bookReviews
    }

    override fun findOneByName(name: String) : BookReviewModel? {
        var foundBookReview: BookReviewModel? = bookReviews.find { p -> p.bookTitle == name }
        return foundBookReview
    }

    override fun findOne(id: Long) : BookReviewModel? {
        var foundBookReview: BookReviewModel? = bookReviews.find { p -> p.id == id }
        return foundBookReview
    }

    override fun filterRating(rating: Int) : List<BookReviewModel> {
        return bookReviews.filter { p -> p.rating >= rating}
    }

    override fun sortRating() : List<BookReviewModel> {
        return bookReviews.sortedByDescending { p -> p.rating }
    }

//    override fun findOneByName(name: String) : BookReviewModel? {
//        var foundBookReview: BookReviewModel? = bookReviews.find { p -> p.bookTitle == name }
//        return foundBookReview
//    }

    override fun create(bookReview: BookReviewModel) {
        bookReview.id = generateRandomId()
        bookReviews.add(bookReview)
        serialize()
    }

    override fun update(bookReview: BookReviewModel) {
        var foundBookReview = findOneByName(bookReview.bookTitle!!)
        if (foundBookReview != null) {
            foundBookReview.bookTitle = bookReview.bookTitle
            foundBookReview.genre = bookReview.genre
            foundBookReview.rating = bookReview.rating
            foundBookReview.review = bookReview.review
        }
        serialize()
    }

    override fun rate(bookReview: BookReviewModel) {
        var foundBookReview = findOneByName(bookReview.bookTitle!!)
        if (foundBookReview != null) {
            foundBookReview.bookTitle = bookReview.bookTitle
            foundBookReview.rating = bookReview.rating
        }
        serialize()
    }

    override fun review(bookReview: BookReviewModel) {
        var foundBookReview = findOneByName(bookReview.bookTitle!!)
        if (foundBookReview != null) {
            foundBookReview.bookTitle = bookReview.bookTitle
            foundBookReview.review = bookReview.review
        }
        serialize()
    }

    override fun delete(bookReview: BookReviewModel) {
        bookReviews.remove(bookReview)
        serialize()
    }

    internal fun logAll() {
        bookReviews.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(bookReviews, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        bookReviews = Gson().fromJson(jsonString, listType)
    }
}