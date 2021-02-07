package com.scopt4poc
import scopt._
import com.scopt4poc.params.{CommonParams, InputParameters, PlaceParams, VisitParams}

object Scopt4Poc {
  def main(args: Array[String]) = {
    var aFile = ""

    //Get File Type
    args.sliding(2, 2).toList.collect {
      case Array("--File", argFile: String) => aFile = argFile
    }

    //Parse parameters from args
    POCParser.parseParameters(aFile, args)

    val params = POCParser.parseParameters (aFile, args)
    println(params.file)
  }
}

object POCParser {
  val init = InputParameters(null)

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
      case _ => throw new IllegalArgumentException(s"Unknown file is passed")
    }

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