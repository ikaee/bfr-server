package dao

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.{Date, TimeZone}

import com.microsoft.azure.documentdb.{Document, DocumentClient}
import dao.DBConfigFactory._
import model._
import play.api.Logger
import play.api.libs.json._

import scala.collection.JavaConverters._


/**
  * Created by kirankumarbs on 4/6/17.
  */
object DocumentDB {
  implicit def convert(o: Object): String = o.toString()

  def regionData(doctype: String, prefixValue: Option[String]): List[Region] = {
    val baseQuery = "SELECT * FROM coll where coll.doctype = \"" + doctype + "\""

    val query = prefixValue.fold(baseQuery)(pv => baseQuery + "and STARTSWITH(coll.code, \"" + pv + "\")")

    queryDatabase(client, query).toList.map(document => Region(document.get("name"), document.get("code")))
  }


  val simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy")
  simpleDateFormat.setTimeZone(TimeZone.getTimeZone("IST"))

  def studentImage(schoolCode: String, studentCode: String, date: String, time: String): String = {
    val imageData = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"image\" and coll.schoolcode = \"" + schoolCode + "\" and coll.studentcode = \"" + studentCode + "\" and coll.datestamp = \"" + date + "\" and coll.timestamp = \"" + time + "\"").toList match {
      case Nil => ""
      case xs => xs.head.get("image").toString
    }
    imageData
  }

  def getTHR(schoolCode: String, date: String) = {
    val master = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"registration\" and coll.schoolcode = \"" + schoolCode + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Master Data is ===>" + master)

    val present = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"" +
      "\" and coll.schoolcode = \"" + schoolCode + "\" and coll.datestamp = \"" + date + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Present Data is ===>" + present)

    val thrs = present.map(p => master.find(m => m.get("studentcode") == p.get("studentcode")) match {
      case None => None
      case Some(m) =>
        Some(THRData(m.get("schoolcode"), m.get("studentcode"), m.get("name"), m.get("surname"), m.get("gender"), m.get("dob"), s"${p.get("datestamp")}-${p.get("timestamp")}", p.get("lattitude"), p.get("longitude")))
    })

    thrs match {
      case Nil => Nil
      case xs => xs.filter(_.isDefined).map(_.get)
    }

  }


  def getAMR(schoolCode: String, date: String) = {
    val master = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"registration\" and coll.schoolcode = \"" + schoolCode + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Master Data is ===>" + master)

    val present = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"attendance\" and coll.schoolcode = \"" + schoolCode + "\" and coll.datestamp = \"" + date + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Present Data is ===>" + present)
    Logger.info("currentDate ===>" + date)

    implicit def convert(o: Object): String = o.toString()


    val amrs = present.map(p => master.find(m => m.get("studentcode") == p.get("studentcode")) match {
      case None => None
      case Some(m) =>
        Some(AMRData(m.get("schoolcode"), m.get("studentcode"), m.get("name"), m.get("surname"), m.get("gender"), m.get("dob"), s"${p.get("datestamp")}-${p.get("timestamp")}", p.get("lattitude"), p.get("longitude")))
    })

    amrs match {
      case Nil => Nil
      case xs => xs.filter(_.isDefined).map(_.get)
    }

  }

  def getHotCooked(schoolCode: String, date: String) = {
    val master = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"registration\" and coll.schoolcode = \"" + schoolCode + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Master Data is ===>" + master)

    val present = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"hot-cooked\" and coll.schoolcode = \"" + schoolCode + "\" and coll.datestamp = \"" + date + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Present Data is ===>" + present)

    implicit def convert(o: Object): String = o.toString()


    val hotCooked = present.map(p => master.find(m => m.get("studentcode") == p.get("studentcode")) match {
      case None => None
      case Some(m) =>
        Some(HotCookedData(m.get("schoolcode"), m.get("studentcode"), m.get("name"), m.get("surname"), m.get("gender"), m.get("dob"), s"${p.get("datestamp")}-${p.get("timestamp")}", p.get("lattitude"), p.get("longitude")))
    })

    hotCooked match {
      case Nil => Nil
      case xs => xs.filter(_.isDefined).map(_.get)
    }

  }

  def getMMR: (String, String) => List[MMRData] = (schoolCode, date) => {
    val currentDate: String = simpleDateFormat.format(new Date())

    val master = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"registration\" and coll.schoolcode = \"" + schoolCode + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Master Data is ===>" + master)

    val present = queryDatabase(client, "SELECT * FROM coll where coll.doctype = \"master-login\" and coll.schoolcode = \"" + schoolCode + "\" and coll.datestamp = \"" + date + "\"").toList match {
      case Nil => Nil
      case xs => xs
    }
    Logger.info("Present Data is ===>" + present)

    implicit def convert(o: Object): String = o.toString()


    val mmrs = present.map(p => master.find(m => m.get("studentcode") == p.get("studentcode")) match {
      case None => None
      case Some(m) =>
        Some(MMRData(m.get("schoolcode"), m.get("studentcode"), m.get("name"), m.get("surname"), m.get("gender"), m.get("dob"), s"${p.get("datestamp")}-${p.get("timestamp")}", p.get("lattitude"), p.get("longitude")))
    })

    mmrs match {
      case Nil => Nil
      case xs => xs.filter(_.isDefined).map(_.get)
    }


  }

  type DashboardData = Either[List[String], Map[String, String]]

  val client = DBConfigFactory.documentClient

  def dashboardData(filters: Option[Map[String, String]]): DashboardData = {
    val master: List[Document] = totalRegistrations
    val present: List[Document] = totalPresented(filters)
    val attendanceWiseData: Map[String, String] = attendancData(master.length, present.length)
    val genderWiseData: Map[String, String] = genderData(master, present)
    val monthWiseData: Map[String, String] = monthData(present)
    val ageWiseData: Map[String, String] = ageData(master, present)

    Right(attendanceWiseData ++ genderWiseData ++ monthWiseData ++ ageWiseData)

  }

  def monthData(present: List[Document]) = {
    present.groupBy(d => d.get("datestamp").toString.split("-")(1)).map(m => (m._1, m._2.size.toString))
  }

  private def genderData(total: List[Document], present: List[Document]): Map[String, String] = {
    val genderData = total.filter(d => present.exists(d1 => d.get("studentcode") == d1.get("studentcode") && d.get("schoolcode") == d1.get("schoolcode")))
    val result = genderData.groupBy(d => d.get("gender").toString.toLowerCase)
      .map(g => (g._1, if (g._2.size == 0) "0" else ((g._2.size.toDouble / genderData.size) * 100).round.toString))

    result

  }

  private def totalPresented(filters: Option[Map[String, String]]) = {
    val currentDate: String = simpleDateFormat.format(new Date())
    val filterString = filters.fold("")(_.toList.map(fs => "coll." + fs._1 + "=\"" + fs._2 + "\"").mkString(" and "))
    val query = "SELECT * FROM coll where " + filterString;
    queryDatabase(client, query).toList match {
      case Nil => Nil
      case xs => xs
    }

  }

  private def totalRegistrations = {

    val query = "SELECT * FROM coll where coll.doctype = \"registration\""
    queryDatabase(client, query).toList match {
      case Nil => Nil
      case xs => xs
    }

  }

  private def attendancData(total: Int, present: Int) = {

    val percentage = total match {
      case 0 => 0
      case total => (present * 100) / total
    }

    Map("total" -> s"$total", "present" -> s"$present", "percentage" -> s"$percentage")
  }

  private def ageData(total: List[Document], present: List[Document]): Map[String, String] = {
    val presentMasterData: List[Document] =
      total.filter(m => present.exists(p => p.get("studentcode") == m.get("studentcode") && p.get("schoolcode") == m.get("schoolcode")))

    total.filter(m => present.exists(p => p.get("studentcode") == m.get("studentcode") && p.get("schoolcode") == m.get("schoolcode")))
      .map(_.get("dob")).foreach(println)

    def getAgeGroup(dob: String): String = {
      val dobParams = dob.split("-")
      val start = LocalDate.of(dobParams(2).toInt, dobParams(1).toInt, dobParams(0).toInt)
      val end = LocalDate.now()
      val year = Math.abs(ChronoUnit.YEARS.between(start, end))
      year match {
        case y if (y >= 0 && y < 1) => "0-1"
        case y if (y >= 1 && y < 3) => "1-3"
        case y if (y >= 3 && y < 5) => "3-5"
        case y if (y >= 5) => "5+"
        case _ => ""

      }

    }

    presentMasterData.map(d => getAgeGroup(d.get("dob").toString()))
      .groupBy(identity).map(a => (a._1, a._2.size.toString))

  }

  private def queryDatabase(client: DocumentClient, query: String) = {
    client.queryDocuments(s"dbs/$databaseId/colls/$collectionId",
      query, null)
      .getQueryIterable.asScala
  }


  def insertIntoDB(data: JsValue): String = {
    val client = DBConfigFactory.documentClient
    client.createDocument(s"dbs/$databaseId/colls/$collectionId",
      new Document(data.toString()), null, false)
    "record is inserted"
  }

  def insertAttendanceEntry(data: JsValue): String = {
    val client = DBConfigFactory.documentClient
    client.createDocument(s"dbs/$databaseId/colls/$collectionId",
      new Document(data.toString()), null, false)
    "record is inserted"
  }

}
