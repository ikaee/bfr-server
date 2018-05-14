package controllers


import dao.DocumentDB
import model.AMRData
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, Controller}

/**
  * Created by kirankumarbs on 7/6/17.
  */
class AmrController extends Controller {

  def getAMR(schoolCode: String, date: String) = Action {
    Ok(Json.toJson(AMRData(schoolCode, date)))
  }

  def getAMRDropDown() = Action {
    Ok(
      Json.arr(
/*        Json.obj("value" -> "27511010507", "label" -> "PIMPALGAO 2"),
        Json.obj("value" -> "27511010509", "label" -> "PIMPALGAO 4"),
        Json.obj("value" -> "27511010209", "label" -> "YELEGOAN (1)"),
        Json.obj("value" -> "27511010211", "label" -> "YELEGOAN (3)"),
        Json.obj("value" -> "27511010406", "label" -> "KONDHA 3"),
        Json.obj("value" -> "27511010407", "label" -> "KONDHA 4"),
        Json.obj("value" -> "27511010109", "label" -> "LAHAN 4"),
        Json.obj("value" -> "27511010111", "label" -> "LAHAN 6"),
        Json.obj("value" -> "27511010305", "label" -> "MALEGAON-3"),
        Json.obj("value" -> "27511010306", "label" -> "MALEGAON-4")*/
        Json.obj("value" -> "27511150601", "label" -> "Vishnupuri-1"),
        Json.obj("value" -> "27511150602", "label" -> "Vishnupuri-2"),
        Json.obj("value" -> "27511150603", "label" -> "Vishnupuri-3"),
        Json.obj("value" -> "27511150604", "label" -> "Vishnupuri-4"),
        Json.obj("value" -> "27511150605", "label" -> "Vishnupuri-5"),
        Json.obj("value" -> "27511150606", "label" -> "Vishnupuri-6"),
        Json.obj("value" -> "27511150607", "label" -> "Vishnupuri-7"),
        Json.obj("value" -> "27511150608", "label" -> "Vishnupuri-8"),
        Json.obj("value" -> "27511150621", "label" -> "Vishnupuri-9"),
        Json.obj("value" -> "27511150610", "label" -> "Markand-1"),
        Json.obj("value" -> "27511150611", "label" -> "markand-2"),
        Json.obj("value" -> "27511150621", "label" -> "Pimpalgaon ni-1"),
        Json.obj("value" -> "27511150621", "label" -> "Pimpalgaon ni-2"),
        Json.obj("value" -> "27511150621", "label" -> "Kalhal"),
        Json.obj("value" -> "27511150621", "label" -> "Bhangi"),
        Json.obj("value" -> "27511150621", "label" -> "Pangari"),
        Json.obj("value" -> "27511150621", "label" -> "Dhangarwadi"),
        Json.obj("value" -> "27511150613", "label" -> "vahegaon")
      )
    )
  }

  def studentImage(schoolCode: String, studentCode: String) = Action {
    Ok(DocumentDB.studentImage(schoolCode, studentCode))
  }

  implicit val amrWrite = new Writes[List[AMRData]] {
    override def writes(o: List[AMRData]): JsValue = Json.obj(
      "data" -> o
    )
  }


}
