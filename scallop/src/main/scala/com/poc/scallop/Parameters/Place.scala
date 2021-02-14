package com.poc.scallop.Parameters

import org.rogach.scallop.{ScallopConf, ScallopOption}
import com.poc.scallop.Parameters.ParseUtils._

case class Place(
                      file: String = "",
                      inputBasePath: String = "",
                      outputBasePath: String = "",
                      isLocal: Boolean = false
                     )

class PlaceParser(arguments: Seq[String]) extends ScallopConf(arguments) {
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

  val isLocal: ScallopOption[Boolean] = toggle(
    "isLocal",
    descrYes = "Say Yes",
    descrNo = "Say No"
  )

  verify()
}

object Place {
  def apply(args: Array[String]): Place ={
    val params = new PlaceParser(args)
    Place(
      file = params.file(),
      inputBasePath = params.inputBasePath(),
      outputBasePath = params.outBasePath(),
      isLocal = params.isLocal.toOption.getOrElse(false)
    )
  }
}
