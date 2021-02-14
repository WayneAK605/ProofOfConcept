package com.poc.scallop

import com.poc.scallop.Parameters.{ParseUtils, Place, Visit}

object Scallop {
  def main(args: Array[String]) = {
    val fileType = ParseUtils.getSingleParamValue(args, "--file")
    val params = {
      fileType match {
        case "place" => Place (args)
        case "visit" => Visit (args)
        case _ => throw new IllegalArgumentException(s"Not valid File Type --${fileType}")
      }
    }

    println(params)
  }
}
