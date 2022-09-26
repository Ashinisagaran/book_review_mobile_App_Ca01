package org.setu.bookReview.console.models

interface BookReviewStore {
    fun findAll(): List<BookReviewModel>
    fun findOne(id: Long): BookReviewModel?
    fun findOneByName(name: String): BookReviewModel?
    fun create(bookReview: BookReviewModel)
    fun update(bookReview: BookReviewModel)
    fun rate(bookReview: BookReviewModel)
    fun review(bookReview: BookReviewModel)
    fun delete(bookReview: BookReviewModel)
}