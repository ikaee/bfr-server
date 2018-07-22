package model

import play.api.libs.json.Json

case class Region(name: String, code: String)
object Region{
  implicit val regionFormat = Json.format[Region]
}
