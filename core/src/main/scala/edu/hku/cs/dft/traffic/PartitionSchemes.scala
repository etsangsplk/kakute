package edu.hku.cs.dft.traffic

import java.io._

/**
  * Created by jianyu on 3/24/17.
  */

/**
  * a [[PartitionSchemes]] store the partition scheme for each data model,
  * this class is for serialization / de-serialization
*/


object PartitionSchemes {
  var schemes: Map[String, PartitionScheme] = Map()

  val defaultPath: String = "/home/jianyu/default.scheme"

  def parseSchemes(path: String): Map[String, PartitionScheme] = {
    try {
      val stream = new ObjectInputStream(new FileInputStream(path))
      schemes = stream.readObject().asInstanceOf[Map[String, PartitionScheme]]
    } catch {
      case _: IOException => println("schemes not found or missing")
    }
    schemes
  }


  // serialize the current schemes or a particular scheme to file

  def serializeSchemes(path: String = defaultPath, scheme: Map[String, PartitionScheme] = schemes): Unit = {
    schemes = scheme
    try {
      val stream = new ObjectOutputStream(new FileOutputStream(path))
      stream.writeObject(schemes)
    } catch {
      case _: IOException => println("schemes could not be write")
    }
  }

  def setSchemes(scheme: Map[String, PartitionScheme]): Unit = schemes = scheme

}
