package org.setu.bookReview.console.controllers

import mu.KotlinLogging
import org.setu.bookReview.console.models.BookReviewJSONStore
import org.setu.bookReview.console.models.BookReviewModel
import org.setu.bookReview.console.views.BookReView

class BookReviewController {

    val bookReviews = BookReviewJSONStore()
    val bookReView = BookReView()
    val logger = KotlinLogging.logger {}

    init {
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
                5 -> sortRating(bookReviews)
                6 -> bookShelves()
                7 -> stageOfReading()
//                8 -> genre()
                9 -> searchSpecificBook()
                10 -> deleteBook()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
    }

    fun menu() :Int { return bookReView.menu() }

    fun addBook(){
        val aBookReview = BookReviewModel()

        if(aBookReview != null) {
            if (bookReView.addBookData(aBookReview)) {
                bookReviews.create(aBookReview)
                bookReView.viewBook(aBookReview)
            }
        }
        else
            println("Book Not Added...")
    }

    fun bookShelves() { //listing all books
        bookReView.listBooks(bookReviews)
    }

    fun updateBook() {
        bookReView.listBooks(bookReviews)
        val searchName = bookReView.getName()
        val aBookReview = searchName(searchName)

        if(aBookReview != null) {
            if(bookReView.updateBookData(aBookReview)) {
                bookReviews.update(aBookReview)
                bookReView.viewBook(aBookReview)
            }
        }
        else
            println("Book Review Not Updated...")
    }


    fun rate() {

        bookReView.listBooks(bookReviews)
        val searchName = bookReView.getName()
        val aBookReview = searchName(searchName)

        if(aBookReview != null) {
            if(bookReView.addRatingForBook(aBookReview)) {
                bookReviews.rate(aBookReview)
                bookReView.viewRating(aBookReview)
            }
        }
        else
            println("Book Not Rated...")

    }


    fun sortRating(bookReviews : BookReviewJSONStore) {

            println("Top Rated Books:")
            println()
            var sortRate = bookReviews.sortRating()
            //print the list in a loop
            for(topRated in sortRate){
                println("Book Title: ${topRated.bookTitle}")
                println("Rating: ${topRated.rating}")
                println()
            }
            println(sortRate)


    }

    fun review() {

        bookReView.listBooks(bookReviews)
        val searchName = bookReView.getName()
        val aBookReview = searchName(searchName)

        if(aBookReview != null) {
            if(bookReView.addReviewData(aBookReview)) {
                bookReviews.review(aBookReview)
                bookReView.viewReview(aBookReview)
            }
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

    fun stageOfReading() {
        val theStage = bookReView.askStageOfReading()
        val aBookReview = searchStage(theStage)!!
        bookReView.printStage(aBookReview, "The following is all the books at the requested stage: ${theStage}")
    }

//    fun genre() {
//        val aBookReview = searchGenre(bookReView.getName())!!
//        bookReView.printTableBook(aBookReview)
//    }

    fun searchStage(stage: String): List<BookReviewModel>{
       val foundStage = bookReviews.findStage(stage)
        return foundStage
    }

    fun searchSpecificBook() {
        val aBookReview = searchSpecificName(bookReView.getName())!!
        bookReView.printTableBook(aBookReview)
    }


    fun searchName(name: String) : BookReviewModel? {
        val foundBookReview = bookReviews.findOneByName(name)
        return foundBookReview
    }

    fun searchSpecificName(name: String) : List<BookReviewModel> {
        val foundBookReview = bookReviews.findUsingSpecificName(name)
        return foundBookReview
    }

    fun search(id: Long) : BookReviewModel? {
        val foundBookReview = bookReviews.findOne(id)
        return foundBookReview
    }

    fun dummyData() {
        bookReviews.create(BookReviewModel(bookTitle = "New York New York", review = "So Good They Named It Twice"))
        bookReviews.create(BookReviewModel(bookTitle= "Ring of Kerry", review = "Some place in the Kingdom"))
        bookReviews.create(BookReviewModel(bookTitle = "Waterford City", review = "You get great Blaas Here!!"))
    }
}