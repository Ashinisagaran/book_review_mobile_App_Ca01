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
//        println(" 4. Update Reviews") // only update when they have submitted "currently reading" or "read"
        println(" 3. Top Rated Books") //for now just rate
        println(" 4. Add Review")
        println(" 5. All Genre")
        println(" 6. Search Book")
        println(" 7. Delete Books")
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
        println("All Books")
        println()
        bookReviews.logAll()
        println()
    }

    fun showBookReview(bookReview : BookReviewModel) {
        if(bookReview != null)
            println("Book Details [ $bookReview ]")
        else
            println("Book Not Found...")
    }

    fun addBookReviewData(bookReview : BookReviewModel) : Boolean {

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

//        print("Please leave a review for the book : ")
//        bookReview.review = readLine()!!

        return bookReview.bookTitle.isNotEmpty() && bookReview.review.isNotEmpty()
    }

    fun updateBookReviewData(bookReview : BookReviewModel) : Boolean {

        val tempBookTitle: String?
        val tempGenre: String?
        val tempStageOfReading: String?
//        val tempReview: String?

        if (bookReview != null) {
            print("Enter the book name : ")
            tempBookTitle = readLine()!!

            print("Enter the genre of the book : ")
            tempGenre = readLine()!!

            println("Select the stage of reading you are at : ")
            println(" 1. Want to Read")
            println(" 2. Currently Reading")
            println(" 3. Read")
            println(" 4. Haven't Considered")
            tempStageOfReading = readLine()!!

//            print("Please leave a review for the book : ")
//            tempReview = readLine()!!

            if (!tempBookTitle.isNullOrEmpty() && !tempGenre.isNullOrEmpty() && !tempStageOfReading.isNullOrEmpty()) {
                bookReview.bookTitle = tempBookTitle
                bookReview.genre = tempGenre
                bookReview.stageOfReading = tempStageOfReading
//                bookReview.review = tempReview
                return true
            }
        }
        return false
    }

    fun addRatingForBookReview(bookReview : BookReviewModel) : Boolean {

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

    fun addReviewData(bookReview : BookReviewModel) : Boolean {

        println()
        print("Please leave a review for the book : ")
        bookReview.review = readLine()!!

        return bookReview.review.isNotEmpty()
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter name of the book : ")
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