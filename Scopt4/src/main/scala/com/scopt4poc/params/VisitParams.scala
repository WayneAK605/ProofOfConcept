package com.scopt4poc.params
import scopt._

trait VisitParams[R] {
  def CopyHousehold(str: String) : R
}

object VisitParams {
  def parseVisitParams[R <: VisitParams[R]]: OParser[_, R] = {
    val builder = OParser.builder[R]
    import builder._

    val visitOption =
      OParser.sequence(
        opt[String]('h', "HouseholdOutputPath")
          .required()
          .action((h, c) => c.CopyHousehold(h))
          .text("HouseholdOutputPath, example: s3a://bucketname/household")
      )
    visitOption
  }
}