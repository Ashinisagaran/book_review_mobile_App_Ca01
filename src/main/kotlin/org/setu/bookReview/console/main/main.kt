package org.setu.bookReview.console.main

//import mu.KotlinLogging
import org.setu.bookReview.console.controllers.BookReviewController
//import org.setu.placemark.console.models.PlacemarkMemStore
//import org.setu.placemark.console.models.PlacemarkModel
//import org.setu.placemark.console.views.PlacemarkView

val controller = BookReviewController()

fun main(args: Array<String>) {

    controller.start()
}
