package domain.repositories.interpreter

import com.microsoft.azure.documentdb.{Document, DocumentClient}
import commonPredef.Validate
import dao.DBConfigFactory
import dao.DBConfigFactory.{collectionId, databaseId, documentClient}
import dao.DocumentDB.client
import domain.model.Log
import domain.repositories.PilotMigrationRepo
import play.api.libs.json.Json
import scalaz.{-\/, \/-}

import scala.collection.JavaConverters._

class PilotMigrationRepoInterpreter extends PilotMigrationRepo {
  override def getLog(beneficiaryCode: String, awc: String, date: String, time: String): Validate[Log] =
    queryDatabase(
      client,
      "SELECT * FROM coll where " +
        "coll.doctype in (\"thr\", \"attendance\", \"hot-cooked\") and " +
        "coll.schoolcode = \"" + awc + "\" and " +
        "coll.studentcode = \"" + beneficiaryCode + "\" and " +
        "coll.datestamp = \"" + date + "\" and " +
        "coll.timestamp = \"" + time + "\"").toList match {
      case Nil => -\/(s"log is not available for give AWC: ${awc}, beneficiary: ${beneficiaryCode}")
      case xs => \/-(Json.parse(xs.head.toJson).as[Log])
    }

  override def getImageLog(beneficiaryCode: String, awc: String, date: String, time: String): Validate[Log] =
    queryDatabase(
      client,
      "SELECT * FROM coll where " +
        "coll.doctype = \"image\" and " +
        "coll.schoolcode = \"" + awc + "\" and " +
        "coll.studentcode = \"" + beneficiaryCode + "\" and " +
        "coll.datestamp = \"" + date + "\" and " +
        "coll.timestamp = \"" + time + "\"").toList match {
      case Nil => -\/(s"Image log is not available for give AWC: ${awc}, beneficiary: ${beneficiaryCode}")
      case xs => \/-(Json.parse(xs.head.toJson).as[Log])
    }

  override def beneficiaryExists(beneficiaryCode: String, awc: String): Validate[String] = {
    queryDatabase(
      client,
      "SELECT * FROM coll where " +
        "coll.doctype = \"registration\" and coll.schoolcode = \"" + awc + "\" and " +
        "coll.studentcode = \"" + beneficiaryCode + "\"").nonEmpty match {
      case true => \/-(s"Master data present for awc: $awc and beneficiary: ${beneficiaryCode}")
      case false => -\/(s"Master data not available for awc: $awc and beneficiary: ${beneficiaryCode}")
    }
  }

  override def createLogFromExisting(log: Log, actualBeneficiaryCode: String): Validate[String] = {
    val newLog = Json.toJson(log.copy(studentCode = actualBeneficiaryCode))

    DBConfigFactory.documentClient.createDocument(s"dbs/$databaseId/colls/$collectionId",
      new Document(newLog.toString()), null, false)

    \/-(s"record is inserted for beneficiary: ${log.studentCode} by actual beneficiary: $actualBeneficiaryCode ")
  }

  private def queryDatabase(client: DocumentClient, query: String) = {
    client.queryDocuments(s"dbs/$databaseId/colls/$collectionId",
      query, null)
      .getQueryIterable.asScala
  }

  override def removeLog(log: Log): Validate[String] = {
    val documents = client.queryDocuments(
      "dbs/" + databaseId + "/colls/" + collectionId,
      "SELECT * FROM tyrion where tyrion.schoolcode=\"" + log.schoolCode +"\" and " +
        "tyrion.studentcode = \"" + log.studentCode +"\" and " +
        "tyrion.datestamp = \"" + log.dateStamp + "\" and " +
        "tyrion.timestamp = \"" + log.timeStamp + "\"",
      null).getQueryIterable().asScala.toList

    println(documents)

    documents.foreach(d => {
      documentClient.deleteDocument(d.getSelfLink(), null)
    })

    \/-(s"record is removed for awc: ${log.schoolCode} and beneficiary: ${log.studentCode}")
  }
}
