package org.setu.bookReview.console.models

data class BookReviewModel(var id: Long = 0,
                          var bookTitle: String = "",
                          var review: String = "",
                          var rating: Int = 0,
                          var genre: String = "",
                          var stageOfReading: String = "",)