package model

import dao.DocumentDB
import play.api.libs.json.{JsValue, Json, Writes}

case class THRData(schoolCode: String,
                   studentcode: String,
                   name: String,
                   surname: String,
                   gender: String,
                   dob: String,
                   timestamp: String,
                   latitude: String,
                   longitude: String
                  )

object THRData {
  def apply(schoolCode: String): List[THRData] = {
    DocumentDB.getTHR(schoolCode)
  }

  implicit val thrDataWriter: Writes[THRData] = new Writes[THRData] {
    override def writes(a: THRData): JsValue = Json.obj(
      "schoolcode" -> a.schoolCode,
      "studentcode" -> a.studentcode,
      "name" -> a.name,
      "surname" -> a.surname,
      "gender" -> a.gender,
      "dob" -> a.dob,
      "timestamp" -> a.timestamp,
      "latitude" -> a.latitude,
      "longitude" -> a.longitude
    )
  }
}