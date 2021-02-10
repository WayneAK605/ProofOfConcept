package com.scopt4poc
import scopt._
import com.scopt4poc.params.{CommonParams, InputParameters, PlaceParams, VisitParams}

object Scopt4Poc {
  def main(args: Array[String]) = {
    val fileType = getParamValue (args,"--File")

    //Parse parameters from args
    val params = parseParameters (fileType, args)
    println(params.outputBasePath)
  }

  def parseParameters(
                       file: String,
                       args: Array[String],
                       effectHandler: Option[OEffectSetup] = None): InputParameters = {

    val parser = file match {
      case "place" => {
        OParser.sequence(
          PlaceParams.parsePlaceParams[InputParameters],
          CommonParams.parseCommonParams[InputParameters]
        )
      }
      case "visit" => {
        OParser.sequence(
          VisitParams.parseVisitParams[InputParameters],
          CommonParams.parseCommonParams[InputParameters]
        )
      }
      case _ => throw new IllegalArgumentException(s"Unknown --File value is passed")
    }

    OParser.parse(parser, args, InputParameters()) match {
      case Some(params) =>
        params
      case _ =>
        throw new IllegalArgumentException(s"Spark job submission has failed, invalid parameters=${args.mkString(" ")}")
    }
  }

  def getParamValue(a: Array[String], key: String):String = {
    val mapValue = a.sliding(2,2).toList.collect {
      case Array(k, v) => Map(k -> v).map(value => value)
    }
    mapValue.map(_.get(key).getOrElse("")).collect {
      case value => value
    }.mkString("")
  }
}