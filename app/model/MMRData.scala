package model

import dao.DocumentDB
import play.api.libs.json.{Json, Writes}

case class MMRData(schoolCode: String,
                   studentcode: String,
                   name: String,
                   surname: String,
                   gender: String,
                   dob: String,
                   timestamp: String,
                   latitude: String,
                   longitude: String)

object MMRData{
  def apply: (String, String) => List[MMRData] = DocumentDB.getMMR

  implicit val mmrDataWriter: Writes[MMRData] = new Writes[MMRData] {
    override def writes(a: MMRData) = Json.obj(
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

