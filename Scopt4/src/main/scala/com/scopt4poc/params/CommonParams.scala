package com.scopt4poc.params
import scopt._

trait CommonParams [R] {
  def CopyInputBasePath(str: String): R
  def CopyOutputBasePath(str: String): R
  def CopyFile(Str: String) : R
}

object CommonParams{
  def parseCommonParams[R <: CommonParams[R]]: OParser[_,R] = {
    val builder = OParser.builder[R]
    import builder._

    val CommonOptions =
      OParser.sequence(
        opt[String]('f', "File")
          .required()
          .action((f, c) => c.CopyFile(f))
          .text("File, example: Place"),

        opt[String]('i', "InputBasePath")
          .required()
          .action((i, c) => c.CopyInputBasePath(i))
          .text("inputBasePath, example: s3a://bucketname/input/"),

        opt[String]('o', "OutputBasePath")
          .required()
          .action((o, c) => c.CopyOutputBasePath(o))
          .text("outputBasePath, example: s3a://bucketname/output/")
      )
    CommonOptions
  }
}