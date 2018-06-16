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
        Json.obj("value" -> "27511150609", "label" -> "Pimpalgaon ni-1"),
        Json.obj("value" -> "27511150619", "label" -> "Pimpalgaon ni-2"),
        Json.obj("value" -> "27511150612", "label" -> "Kalhal"),
        Json.obj("value" -> "27511150614", "label" -> "Bhangi"),
        Json.obj("value" -> "27511150617", "label" -> "Khupasarwadi"),
        Json.obj("value" -> "27511150618", "label" -> "Pangari"),
        Json.obj("value" -> "27511150616", "label" -> "Dhangarwadi"),
        Json.obj("value" -> "27511150613", "label" -> "vahegaon"),
        Json.obj("value" -> "27511150825", "label" -> "Gopalchawdi-5"),
        Json.obj("value" -> "27511150827", "label" -> "Gopalchawdi-6"),
        Json.obj("value" -> "27511150826", "label" -> "Gundegaon"),
        Json.obj("value" -> "27511150820", "label" -> "Balirampur-16"),
        Json.obj("value" -> "27511150813", "label" -> "Balirampur -9"),
        Json.obj("value" -> "27511150819", "label" -> "Balirampur-15"),
        Json.obj("value" -> "27511150805", "label" -> "Balirampur -1"),
        Json.obj("value" -> "27511150808", "label" -> "Balirampur-4"),
        Json.obj("value" -> "27511150811", "label" -> "Balirampur-7"),
        Json.obj("value" -> "27511150816", "label" -> "Balirampur-12"),
        Json.obj("value" -> "27511150818", "label" -> "Balirampur-14"),
        Json.obj("value" -> "27511150806", "label" -> "Balirampur-2"),
        Json.obj("value" -> "27511150810", "label" -> "Balirampur- 6"),
        Json.obj("value" -> "27511150821", "label" -> "Gopalchawadi -1"),
        Json.obj("value" -> "27511150802", "label" -> "Babhulgaon-2"),
        Json.obj("value" -> "27511150801", "label" -> "Babhulgaon-1"),
        Json.obj("value" -> "27511150824", "label" -> "Gopalchawadi -4"),
        Json.obj("value" -> "27511150829", "label" -> "Hemala Tanda-1"),
        Json.obj("value" -> "27511150804", "label" -> "Babhulgaon j Tanda"),
        Json.obj("value" -> "27511150803", "label" -> "Babhulgaon -3"),
        Json.obj("value" -> "27511150822", "label" -> "Gopalchawadi-2"),
        Json.obj("value" -> "27511150823", "label" -> "Gopalchawadi-3")
      )
    )
  }

  def studentImage(schoolCode: String, studentCode: String, date: String, time: String) = Action {
    Ok(DocumentDB.studentImage(schoolCode, studentCode, date, time))
  }

  implicit val amrWrite = new Writes[List[AMRData]] {
    override def writes(o: List[AMRData]): JsValue = Json.obj(
      "data" -> o
    )
  }


}
