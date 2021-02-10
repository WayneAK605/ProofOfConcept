package com.scopt4poc.params

//import org.apache.spark.sql.SaveMode

final case class InputParameters(
                                  date: String = "", // YYYYMMDD
                                  file: String = "", // chain
                                  inputBasePath: String = "",
                                  deltaBasePath: String = "",
                                  outputBasePath: String = "",
                                  householdOutputPath: String = "",
                                  crosswalkPath: String = "",
                                  reMatch: Boolean = false,
                                  isRunningLocal: Boolean = false,
                                  saveMode: String = "Overwrite"
                                ) extends CommonParams[InputParameters]  with PlaceParams [InputParameters] with VisitParams[InputParameters] {
  override def CopyCrosswalkPath(str: String): InputParameters = copy(crosswalkPath = str)
  override def CopyDeltaBasePath(str: String): InputParameters = copy(deltaBasePath = str)
  override def CopyInputBasePath(str: String): InputParameters = copy(inputBasePath = str)
  override def CopyOutputBasePath(str: String): InputParameters = copy(outputBasePath = str)
  override def CopyFile(str: String): InputParameters = copy(file = str)
  override def CopyHousehold(str: String): InputParameters = copy(householdOutputPath = str)
}

object InputParameters {

}
