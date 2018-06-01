package domain.services

import commonPredef.Validate
import domain.repositories.PilotMigrationRepo

object PilotMigrationService {
  type Result[A] = PilotMigrationRepo => Validate[A]

  def addNewLog(
                 awc: String,
                 predictedBeneficiary: String,
                 actualBeneficiary: String,
                 date: String,
                 time: String): Result[String] = repo => for {
    predictedLog <- repo.getLog(predictedBeneficiary, awc, date, time)
    predictedImageLog <- repo.getImageLog(predictedBeneficiary, awc, date, time)
    _ <- repo.beneficiaryExists(actualBeneficiary, awc)
    _ <- repo.createLogFromExisting(predictedLog, actualBeneficiary)
    status <- repo.createLogFromExisting(predictedImageLog, actualBeneficiary)
    removePredictedLog <- repo.removeLog(predictedImageLog)
  } yield s"status: $status,   removed status: $removePredictedLog "

}
