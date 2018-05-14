package controllers


import dao.DocumentDB
import model.THRData
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

class THRController extends Controller {

  def getTHR(schoolCode: String, date: String) = Action {
    Ok(Json.toJson(THRData(schoolCode, date)))
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
