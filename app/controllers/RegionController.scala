package controllers

import dao.DocumentDB
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class RegionController extends Controller {

  def regionData(doctype: String, prefixValue: Option[String] = None) = Action {
    Ok(Json.toJson(DocumentDB.regionData(doctype, prefixValue)))
  }

}
