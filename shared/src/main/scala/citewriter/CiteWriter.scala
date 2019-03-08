package edu.furman.classics.citewriter

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

import scala.scalajs.js
import scala.scalajs.js.annotation._

/** Providing serializations for Cite Architecture Data **/
@JSExportAll trait CiteWriter {

  def writeUrn(u:Urn):String
  def writeCtsUrnPassage(u:CtsUrn):String

  def writeCitableNode(cn:CitableNode):String
  def writeCitableNodeText(cn:CitableNode):String
  def writeCtsCatalogEntry(ce:CatalogEntry):String


  def writeCiteObjectPropertyDef(opd:CitePropertyDef):String
  def writeCiteObject(co:CiteObject):String
  def writeCitePropertyValue(pv:CitePropertyValue):String

}
