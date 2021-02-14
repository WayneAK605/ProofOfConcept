package com.poc.scallop.Parameters

import com.poc.scallop.Parameters.ParseUtils.addTrailingSlash
import org.rogach.scallop.{ScallopConf, ScallopOption}

case class Visit(
                   file: String = "",
                   inputBasePath: String = "",
                   outputBasePath: String = "",
                   householdBasePath: String = ""
                 )

class visitParser(arguments: Seq[String]) extends ScallopConf(arguments) {
  banner (
    """
      |Scallop Parameters Parsing POC
      |file = visit:
      |Parameters:
      |--inputBasePath
      |--outputBasePath
      |--householdBasePath
      |""".stripMargin
  )

  val file: ScallopOption[String] = opt[String](
    "file",
    descr = "PlaceIQ File",
    required = true
  )

  val inputBasePath: ScallopOption[String] = opt[String] (
    "inputBasePath",
    descr = "Base Path of Input",
    required = true
  ).map(addTrailingSlash)

  val outBasePath: ScallopOption[String] = opt[String] (
    "outputBasePath",
    descr = "Base Path of Output",
    required = true
  ).map(addTrailingSlash)

  val householdBasePath: ScallopOption[String] = opt[String] (
    "householdBasePath",
    descr = "Base Path of Household",
    required = true
  ).map(addTrailingSlash)

  verify()
}

object Visit {
  def apply(args: Array[String]): Visit ={
    val params = new visitParser(args)

    Visit(
      file = params.file(),
      inputBasePath = params.inputBasePath(),
      outputBasePath = params.outBasePath(),
      householdBasePath = params.householdBasePath()
    )
  }
}
