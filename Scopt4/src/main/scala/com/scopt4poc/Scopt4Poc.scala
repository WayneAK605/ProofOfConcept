package com.scopt4poc


import com.scopt4poc.params.{CommonParams, InputParameters, PlaceParams, VisitParams}
import scopt._

object Scopt4Poc {
  def main(args: Array[String]) = {
    var aFile = ""

    args.sliding(2, 2).toList.collect {
      case Array("--File", argFile: String) => aFile = argFile
    }


    val parser = aFile match {
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
      }


    val init = InputParameters(null)

    lazy val params = parseParameters (args)

    println(params.file)

    def parseParameters(
               args: Array[String],
               effectHandler: Option[OEffectSetup] = None): InputParameters = {
      val (params, effects) = OParser.runParser(parser, args, init)

      // scopt advises to override terminate (we shouldn't exit(1) a spark application)
      OParser.runEffects(
        effects,
        effectHandler.getOrElse(new DefaultOEffectSetup {
          override def terminate(exitState: Either[String, Unit]): Unit = () //
        }))

      params.getOrElse {
        throw new IllegalArgumentException(
          "Spark job submission has failed: " + args.mkString(" "))
      }
    }
  }
}
