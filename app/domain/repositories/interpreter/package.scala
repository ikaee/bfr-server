package domain.repositories

import domain.model.Log
import play.api.libs.functional.syntax._
import play.api.libs.json._

package object interpreter {
  
  implicit val logReads: Reads[Log] = (
    (JsPath \ "schoolcode").read[String] and
      (JsPath \ "studentcode").read[String] and
      (JsPath \ "lattitude").read[String] and
      (JsPath \ "longitude").read[String] and
      (JsPath \ "doctype").read[String] and
      (JsPath \ "datestamp").read[String] and
      (JsPath \ "timestamp").read[String] and
      (JsPath \ "image").readNullable[String]
  )(Log.apply _)

  implicit val logWrites = new Writes[Log] {
    override def writes(log: Log): JsValue = {
      Json.obj(
        "schoolcode" -> log.schoolCode,
        "studentcode" -> log.studentCode,
        "lattitude" -> log.lattitude,
        "longitude" -> log.longitude,
        "doctype" -> log.doctype,
        "datestamp" -> log.dateStamp,
        "timestamp" -> log.timeStamp
      ) ++ log.image.fold(Json.obj())(image => Json.obj("image" -> image))
    }
  }
}
