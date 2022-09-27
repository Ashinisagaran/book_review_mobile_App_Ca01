//package org.setu.bookReview.console.models
//
//import mu.KotlinLogging
//
//private val logger = KotlinLogging.logger {}
//var lastId = 0L
//
//internal fun getId(): Long {
//    return lastId++
//}
//
//class BookReviewMemStore : BookReviewStore {
//
//    val bookReviews = ArrayList<BookReviewModel>()
//
//    override fun findAll(): List<BookReviewModel> {
//        return bookReviews
//    }
//
//    override fun findOne(id: Long) : BookReviewModel? {
//        var foundBookReview: BookReviewModel? = bookReviews.find { p -> p.id == id }
//        return foundBookReview
//    }
//
//    override fun create(bookReview: BookReviewModel) {
//        bookReview.id = getId()
//        bookReviews.add(bookReview)
//        logAll()
//    }
//
//    override fun update(bookReview: BookReviewModel) {
//        var foundBookReview = findOne(bookReview.id!!)
//        if (foundBookReview != null) {
//            foundBookReview.bookTitle = bookReview.bookTitle
//            foundBookReview.review = bookReview.review
//        }
//    }
//
//    override fun rate(bookReview: BookReviewModel) {
//        var foundBookReview = findOne(bookReview.id!!)
//        if (foundBookReview != null) {
//            foundBookReview.bookTitle = bookReview.bookTitle
//            foundBookReview.rating = bookReview.rating
//        }
//    }
//
//    override fun review(bookReview: BookReviewModel) {
//        var foundBookReview = findOne(bookReview.id!!)
//        if (foundBookReview != null) {
//            foundBookReview.bookTitle = bookReview.bookTitle
//            foundBookReview.review = bookReview.review
//        }
//    }
//
//    override fun sortRating(rating: Int) : List<BookReviewModel> {
//        var sortRating = bookReviews.sortByDescending { p -> p.rating }
//        return sortRating
//    }
//
//    override fun delete(bookReview: BookReviewModel) {
//        bookReviews.remove(bookReview)
//    }
//
//    internal fun logAll() {
//        bookReviews.forEach { logger.info("${it}") }
//    }
//}