package org.setu.bookReview.console.controllers

import mu.KotlinLogging
import org.setu.bookReview.console.models.BookReviewJSONStore
//import org.setu.placemark.console.models.BookReviewMemStore
import org.setu.bookReview.console.models.BookReviewModel
import org.setu.bookReview.console.views.BookReView

class BookReviewController {

    //    val placemarks = PlacemarkMemStore()
    val bookReviews = BookReviewJSONStore()
    val bookReView = BookReView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Book Review Console App" }
        println("Welcome to Book Review Kotlin App Version 1.0 by Ashini Sagaran")
    }

    fun start ()
    {
        var input: Int

        do {
            input = menu()
            when(input) {
                1 -> addBook()
                2 -> updateBook()
                3 -> rate()
                4 -> review()
                5 -> bookShelves()
                6 -> searchBook()
                7 -> deleteBook()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Book Review Console App" }
    }

    fun menu() :Int { return bookReView.menu() }

    fun addBook(){
        val aBookReview = BookReviewModel()

        if(aBookReview != null) {
            if (bookReView.addBookData(aBookReview)) {
                bookReviews.create(aBookReview)

                logger.info("Book Added : [ $aBookReview ]")
            }
            else
                logger.info("Book Not Added")
        }
        else
            println("Book Not Added...")
    }

    fun bookShelves() { //listing all books
        bookReView.listBooks(bookReviews)
    }

    fun updateBook() {
        bookReView.listBooks(bookReviews)
        val searchId = bookReView.getId()
        val aBookReview = search(searchId)

        if(aBookReview != null) {
            if(bookReView.updateBookData(aBookReview)) {
                bookReviews.update(aBookReview)
                bookReView.showBook(aBookReview)
                logger.info("Book Review Updated : [ $aBookReview ]")
            }
            else
                logger.info("Book Review Not Updated")
        }
        else
            println("Book Review Not Updated...")
    }

//    fun stageOfReading() {
//        var stageOfReading: Int
//
//        do {
//            stageOfReading = menu()
//            when(stageOfReading) {
//                1 -> wantToRead()
//                2 -> currentlyReading()
//                3 -> rate()
//                4 -> bookShelves()
//                else -> println("Invalid Option")
//            }
//            println()
//        } while (stageOfReading != -1)
//        logger.info { "Shutting Down Book Review Console App" }
//    }
//    }

    fun rate() {

        bookReView.listBooks(bookReviews)
        val searchName = bookReView.getName()
        val aBookReview = searchName(searchName)

        if(aBookReview != null) {
            if(bookReView.addRatingForBook(aBookReview)) {
                bookReviews.rate(aBookReview)
                bookReView.showBook(aBookReview)
                logger.info("Your rated Book : [ $aBookReview ]")
            }
            else
                logger.info("Book Not Rated")
        }
        else
            println("Book Not Rated...")

    }

    fun sortRating() {



    }

    fun review() {

        bookReView.listBooks(bookReviews)
        val searchId = bookReView.getId()
        val aBookReview = search(searchId)

        if(aBookReview != null) {
            if(bookReView.addReviewData(aBookReview)) {
                bookReviews.review(aBookReview)
                bookReView.showBook(aBookReview)
                logger.info("Your Book Review : [ $aBookReview ]")
            }
            else
                logger.info("Book Not Reviewed")
        }
        else
            println("Book Not Reviewed...")

    }

    fun deleteBook() {
        bookReView.listBooks(bookReviews)
        var searchId = bookReView.getId()
        val aBookReview = search(searchId)

        if(aBookReview != null) {
            bookReviews.delete(aBookReview)
            println("Book Deleted...")
            bookReView.listBooks(bookReviews)
        }
        else
            println("Book Not Deleted...")
    }

    fun searchBook() { //needed for menu
        val aBookReview = searchName(bookReView.getName())!!
        bookReView.showBook(aBookReview)
    }


    fun searchName(name: String) : BookReviewModel? {
        val foundBookReview = bookReviews.findOneByName(name)
        return foundBookReview
    }

    fun search(id: Long) : BookReviewModel? {
        val foundPlacemark = bookReviews.findOne(id)
        return foundPlacemark
    }

    fun dummyData() {
        bookReviews.create(BookReviewModel(bookTitle = "New York New York", review = "So Good They Named It Twice"))
        bookReviews.create(BookReviewModel(bookTitle= "Ring of Kerry", review = "Some place in the Kingdom"))
        bookReviews.create(BookReviewModel(bookTitle = "Waterford City", review = "You get great Blaas Here!!"))
    }
}