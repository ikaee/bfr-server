package controllers

import dao.DocumentDB
import model.HotCookedData
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

class HotCookedController extends Controller {

  def getHotCooked(schoolCode: String, date: String) = Action {
    Ok(Json.toJson(HotCookedData(schoolCode, date)))
  }

  def studentImage(schoolCode: String, studentCode: String, date: String, time: String) = Action {
    Ok(DocumentDB.studentImage(schoolCode, studentCode, date, time))
  }

  implicit val thrWrite = new Writes[List[HotCookedData]] {
    override def writes(o: List[HotCookedData]): JsValue = Json.obj(
      "data" -> o
    )
  }

}
