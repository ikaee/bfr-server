package controllers

import dao.DocumentDB
import model.MMRData
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

class MMRController extends Controller {

  def getMMR(schoolCode: String, date: String) = Action {
    Ok(Json.toJson(MMRData(schoolCode, date)))
  }


  def studentImage(schoolCode: String, studentCode: String, date: String, time: String) = Action {
    Ok(DocumentDB.studentImage(schoolCode, studentCode, date, time))
  }

  implicit val mmrWrite = new Writes[List[MMRData]] {
    override def writes(o: List[MMRData]): JsValue = Json.obj(
      "data" -> o
    )
  }

}
