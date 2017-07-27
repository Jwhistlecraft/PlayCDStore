package controllers

import javax.inject.Inject

import models.CD
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def listCDs = Action { implicit request =>
    Ok(views.html.listCDs(CD.cds, CD.createCDForm))
  }

  def createCD = Action { implicit request =>
    val formValidationResult = CD.createCDForm.bindFromRequest


    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.listCDs(CD.cds, formWithErrors)) //form with errors
    },{ cd => CD.cds.append(cd)                              //form with no errors
       Redirect(routes.Application.listCDs)
    }
   )
  }

}


// we return a view file which expects two arguments passed to it
// The first argument is the Seq[CD] which contains all the CDs we want to display
// The second argument is the Form[CD], where we pass the form we have created

// we create a value to which we assign the form and bind the values that were submitted and are in the response

// we then fold over the form, where fold is a method that belongs to Form and what it does is
// takes in two functions, where the first one has to handle the form with errors
// and the second one has to handle the successful form submission.
// You could look at fold like this .fold({ Function for error}, { Function for success })
// the first function of folding is the one where the data passed for the form was incorrect,
// therefore our form with the errors on it is bound to the formWithErrors.
// We then return a BadRequest to signify that it wasn't correct and for the BadRequest. we then pass
// the same view file, as the view file requires an argument of Seq[CD] we pass that from the CD object
// as well as the form with errors, where passing the form with errors is going to display the errors on the page

// the second case is the good one were the data is of the correct type therefore the cd will hold the value
// of CD, which we then add to the list of the CDs that we have inside the CD object
// and lastly we return a Redirect response where we take the person to the same page but list all the CDs out
// as we're using the reverse route of .listCDs, therefore after adding a CD and submitting the form
// we will see the CD come up on the page










