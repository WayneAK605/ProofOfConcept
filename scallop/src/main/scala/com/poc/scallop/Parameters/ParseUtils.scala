package com.poc.scallop.Parameters

object ParseUtils {
  def addTrailingSlash(str: String): String = {
    if (str.endsWith("/") | str.isEmpty) str else str + "/"
  }

  def getSingleParamValue(array: Array[String], key: String): String = {
    array.grouped(2)
      .map(param => param(0) -> param(1))
      .toMap
      .getOrElse(key, throw new IllegalArgumentException(s"Not a valid parameter --${key}"))
  }
}
