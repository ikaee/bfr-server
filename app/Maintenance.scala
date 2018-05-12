import dao.DBConfigFactory._
import scala.collection.JavaConverters._

object removeAllAttendance extends App{

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"attendance\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}
object removeAllThr extends App{

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"thr\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}
object removeAllHotCooked extends App{

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"hot-cooked\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}
object removeAllRegistration extends App{

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where  tyrion.doctype=\"registration\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}
object removeAllImage extends App{

  val documents = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where  tyrion.doctype=\"image\" ",
    null).getQueryIterable().asScala.toList

  println(documents)

  documents.foreach(d => {
    documentClient.deleteDocument(d.getSelfLink(), null)
  })

}


object getAllRegistrationData extends App{
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"registration\" ",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}
object getAllMasterListLogData extends App{
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"master\" ",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}
object getAllAMRData extends App{
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"attendance\" AND tyrion.schoolcode=\"27511010507\"",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}
object getImageData extends App{
  val dashboard = documentClient.queryDocuments(
    "dbs/" + databaseId + "/colls/" + collectionId,
    "SELECT * FROM tyrion where tyrion.doctype=\"image\" ",
    null).getQueryIterable().toList()

  println("master data ===>" + dashboard)
}


