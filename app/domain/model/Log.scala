package domain.model

case class Log(
                schoolCode: String,
                studentCode: String,
                lattitude: String,
                longitude: String,
                doctype: String,
                dateStamp: String,
                timeStamp: String,
                image: Option[String]=None
              )