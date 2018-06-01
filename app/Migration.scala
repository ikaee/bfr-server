import domain.repositories.interpreter.PilotMigrationRepoInterpreter
import domain.services.PilotMigrationService

import scala.io.Source

object Migration extends App {

  val fileName = "pilot-predictions.txt"

  (for {
    prediction <- Source.fromFile(fileName).getLines()
  } yield {
    val p = prediction.split(",")
    val awc = p(0)
    val predicted = p(1)
    val actual = p(2)
    val date = p(3)
    val time = p(4)

    PilotMigrationService.addNewLog(awc, predicted, actual, date, time)(new PilotMigrationRepoInterpreter())
  }
    ).foreach(println)


}
