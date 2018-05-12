package controllers


import dao.DocumentDB
import model.THRData
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

class THRController extends Controller {

  def getTHR(schoolCode: String) = Action {equals()
    Ok(Json.toJson(THRData(schoolCode)))
  }

  def getTHRDropDown() = Action {
    Ok(
      Json.arr(
        Json.obj("value" -> "27511150601", "label" -> "Vishnupuri-1"),
        Json.obj("value" -> "27511150602", "label" -> "Vishnupuri-2"),
        Json.obj("value" -> "27511150603", "label" -> "Vishnupuri-3"),
        Json.obj("value" -> "27511150604", "label" -> "Vishnupuri-4"),
        Json.obj("value" -> "27511150605", "label" -> "Vishnupuri-5"),
        Json.obj("value" -> "27511150606", "label" -> "Vishnupuri-6"),
        Json.obj("value" -> "27511150607", "label" -> "Vishnupuri-7"),
        Json.obj("value" -> "27511150608", "label" -> "Vishnupuri-8"),
        Json.obj("value" -> "27511150621", "label" -> "Vishnupuri-9"),
        Json.obj("value" -> "27511150613", "label" -> "vahegaon")
      )
    )
  }

  def studentImage(schoolCode: String, studentCode: String) = Action {
    Ok(DocumentDB.studentImage(schoolCode, studentCode))
  }

  implicit val thrWrite = new Writes[List[THRData]] {
    override def writes(o: List[THRData]): JsValue = Json.obj(
      "data" -> o
    )
  }

}
