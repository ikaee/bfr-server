package domain.repositories

import commonPredef.Validate
import domain.model.Log

trait PilotMigrationRepo {

  def getLog(beneficiaryCode: String, awc: String, date: String, time: String): Validate[Log]
  def getImageLog(beneficiaryCode: String, awc: String, date: String, time: String): Validate[Log]
  def beneficiaryExists(beneficiaryCode: String, awc: String): Validate[String]
  def createLogFromExisting(log: Log, actualBeneficiaryCode: String): Validate[String]
  def removeLog(log: Log): Validate[String]

}
