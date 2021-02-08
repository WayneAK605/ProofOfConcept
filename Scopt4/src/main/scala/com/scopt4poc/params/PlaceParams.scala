package com.scopt4poc.params
import scopt._

trait PlaceParams[R] {
  def CopyDeltaBasePath(str: String): R
  def CopyCrosswalkPath(str: String): R
}

object PlaceParams {
  def parsePlaceParams[R <: PlaceParams[R]]: OParser[_,R] = {
    val builder = OParser.builder[R]
    import builder._

    val placeOption =
      OParser.sequence(
        opt[String]('d', "DeltaBasePath")
          .required()
          .action((d, c) => c.CopyDeltaBasePath(d))
          .text("deltaBasePath, example: s3a://bucketname/delta/"),

        opt[String]('w', "CrosswalkPath")
          .required()
          .action((w, c) => c.CopyCrosswalkPath(w))
          .text("crosswalkPath, example: s3a://bucketname/crosswalk/")
      )
    placeOption
  }
}
