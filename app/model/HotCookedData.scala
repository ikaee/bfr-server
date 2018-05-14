package model

import dao.DocumentDB
import play.api.libs.json.{Json, Writes}

case class HotCookedData(schoolCode: String,
                         studentcode: String,
                         name: String,
                         surname: String,
                         gender: String,
                         dob: String,
                         timestamp: String,
                         latitude: String,
                         longitude: String)

object HotCookedData {
  def apply(schoolCode: String, date: String): List[HotCookedData] = {
    DocumentDB.getHotCooked(schoolCode, date)
  }

  implicit val thrDataWriter: Writes[HotCookedData] = new Writes[HotCookedData] {
    override def writes(a: HotCookedData) = Json.obj(
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