package org.setu.bookReview.console.views

import org.setu.bookReview.console.models.BookReviewJSONStore
import org.setu.bookReview.console.models.BookReviewModel

class BookReView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("Welcome to Book Review App")
        println("MAIN MENU")
        println(" 1. Add Books")
        println(" 2. Update Books")
        println(" 3. Update Rating") //for now just rate
        println(" 4. Update Review")
        println(" 5. Top Rated Books") //returns in descending order of the rate
        println(" 6. Bookshelves")
        println(" 7. Search Books by Stage")
        println(" 8. Search by Genre")
        println(" 9. Search Book")
        println(" 10. Delete Books")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listBooks(bookReviews : BookReviewJSONStore) {
        println("Books:")
        println()
        var allBooks = bookReviews.findAll()
        printTableBook(allBooks)
        println(allBooks)
    }

    fun printTableBook(allBooks : List<BookReviewModel>) {
        println(
            String.format(
                "%10s %25s %25s %60s %25s %10s %25s %10s %25s %10s %25s %60s %20s",
                "|",
                "Book ID",
                "|",
                "Book Title",
                "|",
                "Genre",
                "|",
                "Stage of Reading",
                "|",
                "Rating",
                "|",
                "Review",
                "|"
            )
        )
        println(
            String.format(
                "%s",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
            )
        )
        for (book in allBooks) {
            viewBook(book)
        }
    }

    fun viewBook(book: BookReviewModel) {
        println()
        println(
            String.format(
                "%10s %25s %25s %60s %25s %10s %25s %15s %25s %10s %25s %60s %20s",
                "|",
                "${book.id} ",
                "|",
                "${book.bookTitle}",
                "|",
                "${book.genre}",
                "|",
                "${book.stageOfReading}",
                "|",
                "${book.rating}",
                "|",
                "${book.review}",
                "|"
            )
        )
        println()
    }


    fun printCategory(allBooks : List<BookReviewModel>, header :String){
        println(header)
        printTableBook(allBooks)
    }

    fun addBookData(bookReview : BookReviewModel) : Boolean { //

        println()
        print("Enter the book name : ")
        bookReview.bookTitle = readLine()!!

        print("Enter the genre of the book : ")
        bookReview.genre = readLine()!!

        println("Select the stage of reading you are at : ")
        println(" 1. Want to Read")
        println(" 2. Currently Reading")
        println(" 3. Read")
        println(" 4. Haven't Considered")
        println(" 5. Nope")
        bookReview.stageOfReading = convertStage(readLine()!!)

        var option : String?
        do {
            print("Enter you ratings: ")
            option = readLine()!!
            bookReview.rating = if (option.toIntOrNull() != null && !option.isEmpty())
                option.toInt()
            else
                -9
        } while (bookReview.rating<0 || bookReview.rating>5)

        print("Please leave a review for the book : ")
        bookReview.review = readLine()!!

        return bookReview.bookTitle.isNotEmpty() && bookReview.genre.isNotEmpty() && bookReview.stageOfReading.isNotEmpty()
    }

    fun convertStage(stage:String) =

        when(stage) {
            "1" -> "Want to Read"
            "2" -> "Currently Reading"
            "3" -> "Read"
            "4" -> "Haven't Considered"
            "5" -> "Nope"
            else -> "Nope"
        }


    fun askStageOfReading(): String {
        println("Which stage of reading would you like to see?")
        println(" 1. Want to Read")
        println(" 2. Currently Reading")
        println(" 3. Read")
        println(" 4. Haven't Considered")
        println(" 5. Nope")

        var stageStore = readLine()!!
        return convertStage(stageStore)
    }

    fun askGenre(): String{
        println("Which genre of books would you like to see?")
        var genreStore = readLine()!!
        return genreStore
    }

    fun updateBookData(bookReview : BookReviewModel) : Boolean {

        val tempBookTitle: String?
        val tempGenre: String?
        val tempStageOfReading: String?

        if (bookReview != null) {
            print("Enter the new book's name to be updated: ")
            tempBookTitle = readLine()!!

            print("Enter the genre of the book to be updated: ")
            tempGenre = readLine()!!

            println("Select the stage of reading to be updated : ")
            println(" 1. Want to Read")
            println(" 2. Currently Reading")
            println(" 3. Read")
            println(" 4. Haven't Considered")
            println(" 5. Nope")
            tempStageOfReading = convertStage(readLine()!!)

            if (!tempBookTitle.isNullOrEmpty() && !tempGenre.isNullOrEmpty() && !tempStageOfReading.isNullOrEmpty()) {
                bookReview.bookTitle = tempBookTitle
                bookReview.genre = tempGenre
                bookReview.stageOfReading = tempStageOfReading
                return true
            }
        }
        return false
    }

    fun addRatingForBook(bookReview : BookReviewModel) : Boolean {

        var option : String?
        var tempRating: Int

        do {
            print("Enter you ratings for [ " + bookReview.bookTitle + " ] : ")
            option = readLine()!!
            tempRating = if (option.toIntOrNull() != null && !option.isEmpty())
                option.toInt()
            else
                -9
        } while (tempRating<0 || tempRating>5)

        bookReview.rating = tempRating
        return true
    }


    fun viewRating(bookReview: BookReviewModel){
        println(
            String.format(
                "%10s %60s %25s %10s %25s",
                "|",
                "Book Title",
                "|",
                "Rating",
                "|"
            )
        )
        println(
            String.format(
                "%s",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
            )
        )
        println()
        println(
            String.format(
                "%10s %60s %25s %10s %25s",
                "|",
                "${bookReview.bookTitle}",
                "|",
                "${bookReview.rating}",
                "|"
            )
        )
        println()
    }

    fun addReviewData(bookReview : BookReviewModel) : Boolean {

        println()
        print("Please leave a review for the book : ")
        bookReview.review = readLine()!!

        return bookReview.review.isNotEmpty()
    }

    fun viewReview(bookReview: BookReviewModel){
        println(
            String.format(
                "%10s %60s %25s %10s %25s",
                "|",
                "Book Title",
                "|",
                "Review",
                "|"
            )
        )
        println(
            String.format(
                "%s",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
            )
        )
        println()
        println(
            String.format(
                "%10s %60s %25s %10s %25s",
                "|",
                "${bookReview.bookTitle}",
                "|",
                "${bookReview.review}",
                "|"
            )
        )
        println()
    }

    fun printGenre(allBooks : List<BookReviewModel>) {
        println(
            String.format(
                "%10s  %10s %25s",
                "|",
                "Genre",
                "|"
            )
        )
        println(
            String.format(
                "%s",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
            )
        )
        for (book in allBooks) {
            viewGenre(book)
        }
    }

    fun viewGenre(bookReview: BookReviewModel){
        println()
        println(
            String.format(
                "%10s %10s %25s",
                "|",
                "${bookReview.genre}",
                "|"
            )
        )
        println()
    }

    fun listGenre(bookReviews : BookReviewJSONStore) {
        println("Genres:")
        println()
        var allGenres = bookReviews.findAllGenres()
        printGenre(allGenres)
        println(allGenres)
    }


    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id of the book : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }

    fun getName() : String {
        var strName : String? // String to hold user input
        print("Enter name of the book : ")
        strName = readLine()!!
        return strName
    }
}