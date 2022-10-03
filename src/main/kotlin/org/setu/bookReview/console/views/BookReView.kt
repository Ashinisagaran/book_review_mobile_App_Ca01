package org.setu.bookReview.console.views

import org.setu.bookReview.console.models.BookReviewJSONStore
//import org.setu.bookReview.console.models.BookReviewMemStore
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
//        println(" 4. Update Reviews") // only update when they have submitted "currently reading" or "read"
        println(" 6. Bookshelves")
//        println(" 5. All Genre")
        println(" 7. Search Book")
        println(" 8. Delete Books")
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

    fun printTableBook(allBooks : List<BookReviewModel>){
        println(String.format("%10s %25s %25s %60s %25s %10s %25s %10s %25s %10s %25s %60s %20s" , "|" , "Book ID" , "|" , "Book Title" , "|" , "Genre" , "|" , "Stage of Reading" , "|" , "Rating" , "|" , "Review" , "|"))
        println(String.format("%s" , "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"))
        for(book in allBooks){
            println()
            println(String.format("%10s %25s %25s %60s %25s %10s %25s %15s %25s %10s %25s %60s %20s" , "|" , "${book.id} " , "|" , "${book.bookTitle}" , "|" , "${book.genre}" , "|" , "${book.stageOfReading}" , "|" , "${book.rating}" , "|" , "${book.review}" , "|"))
            println()
        }
    }

    fun showBook(bookReview : BookReviewModel) {
        if(bookReview != null)
            println("Book Details [ $bookReview ]")
        else
            println("Book Not Found...")
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
        bookReview.stageOfReading = readLine()!!

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

    fun viewAddedBook(bookReview: BookReviewModel){
        println("Your updated book,  \n Book Title: ${bookReview.bookTitle}, \n Genre: ${bookReview.genre}, \n Stage of Reading: ${bookReview.stageOfReading}, \n Rating: ${bookReview.rating}, \n Review: ${bookReview.review}")
    }

    fun updateBookData(bookReview : BookReviewModel) : Boolean {

        val tempBookTitle: String?
        val tempGenre: String?
        val tempStageOfReading: String?

        if (bookReview != null) {
            print("Enter the book name to be updated: ")
            tempBookTitle = readLine()!!

            print("Enter the genre of the book to be updated: ")
            tempGenre = readLine()!!

            println("Select the stage of reading to be updated : ")
            println(" 1. Want to Read")
            println(" 2. Currently Reading")
            println(" 3. Read")
            println(" 4. Haven't Considered")
            tempStageOfReading = readLine()!!

            if (!tempBookTitle.isNullOrEmpty() && !tempGenre.isNullOrEmpty() && !tempStageOfReading.isNullOrEmpty()) {
                bookReview.bookTitle = tempBookTitle
                bookReview.genre = tempGenre
                bookReview.stageOfReading = tempStageOfReading
                return true
            }
        }
        return false
    }

    fun viewUpdatedBook(bookReview: BookReviewModel){
        println("Your updated book,  \n Book Title: ${bookReview.bookTitle}, \n Genre: ${bookReview.genre}, \n Stage of Reading:${bookReview.stageOfReading}")
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
        println("Your review  \'${bookReview.rating}\' for \'${bookReview.bookTitle}\', has been added")
        println()
        println(" Book Title: ${bookReview.bookTitle}, \n Genre: ${bookReview.genre}, \n Stage of Reading: ${bookReview.stageOfReading}, \n Rating: ${bookReview.rating}")
    }

    fun addReviewData(bookReview : BookReviewModel) : Boolean {

        println()
        print("Please leave a review for the book : ")
        bookReview.review = readLine()!!

        return bookReview.review.isNotEmpty()
    }

    fun viewReview(bookReview: BookReviewModel){
        println("Your review  \'${bookReview.review}\' for \'${bookReview.bookTitle}\', has been added")
        println()
        println(" Book Title: ${bookReview.bookTitle}, \n Genre: ${bookReview.genre}, \n Stage of Reading: ${bookReview.stageOfReading}, \n Rating: ${bookReview.rating}, \n Review: ${bookReview.review}")
    }

    fun viewSearchedBook(bookReview: BookReviewModel){
        println()
        println(" Book ID: ${bookReview.id} \n Book Title: ${bookReview.bookTitle} \n Genre: ${bookReview.genre} \n Stage of Reading: ${bookReview.stageOfReading} \n Rating: ${bookReview.rating} \n Review: ${bookReview.review}")
        println()
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