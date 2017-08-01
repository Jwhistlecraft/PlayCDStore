package models

import play.api.data._
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

@MongoEntity("CDs")
public class CD extends MongoModel {
  index:Option[Int];
    title: String;
    genre:String;
    artist:String;
    starRating:Int;
    price: Int;
}

//case class SelectCD(title:String)

object CD {

  val createCDForm = Form(
    mapping(
      "index" -> optional(number),
      "title" -> nonEmptyText,
      "genre" -> nonEmptyText,
      "artist" -> nonEmptyText,
      "starRating" -> number(min = 0, max = 5),
      "price" -> number(min = 0, max = 1000)
    )
    (CD.apply)(CD.unapply)
  )

  val cds: ArrayBuffer[CD] = ArrayBuffer[CD]()

}



// We create a case class and define the fields it will have

// We then create a companion object to accompany it, where we store our Form and a list of CDs

// remember to use the imports when defining forms, import play.api.data._ and import play.api.data.Forms._
// we then define a value  to which we assign a Form, inside the parentheses of the Form
// we then use mapping to map the name to a constrain
// in this particular case name will need some string passed to it, and an empty one wouldn't work
// for the price we say that it has to be of numerical value, where the minimum value is going to be 0
// and the maximum value will be 100
// we then follow the closing parentheses of mapping by having the apply unapply methods
// which are needed for the data to be mapped to the model fields

// this is where we define a collection of CDs so that we could display some of them on the page
// keep in mind if you were to stop this application the added CD's would be wiped and it
// would one again start with these three





