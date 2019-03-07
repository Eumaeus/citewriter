package edu.furman.classics.citewriter

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

/** Providing serializations for Homer Multitext Data **/
object CexWriter extends CiteWriter {


  val defaultDelim:String = "#"
  val defaultSecondDelim:String = ","

  def writeCitableNode(cn:CitableNode):String = {
  	writeCitableNode(cn, defaultDelim)
  }

  def writeCitableNode(cn:CitableNode, delim:String = defaultDelim):String = {
    s"${cn.urn}${delim}${cn.text}"
  }

  def writeCitableNodeText(cn:CitableNode):String = {
  	cn.text
  }

  def writeCtsUrnPassage(u:CtsUrn):String = {
	  	u.passageComponent
  }

  def writeUrn(u:Urn):String = {
  	u.toString
  }

 def writeCtsCatalogEntry(ce: edu.holycross.shot.ohco2.CatalogEntry, delimiter:String): String = {
   s"""${ce.urn}${delimiter}${ce.citationScheme}${delimiter}${ce.groupName}${delimiter}${ce.workTitle}${delimiter}${ce.versionLabel.getOrElse("")}${delimiter}${ce.exemplarLabel.getOrElse("")}${delimiter}${ce.online}${delimiter}${ce.lang}"""
 }

 def writeCtsCatalogEntry(ce: edu.holycross.shot.ohco2.CatalogEntry): String = {
    writeCtsCatalogEntry(ce, defaultDelim)
 }

 def writeCiteCollectionDef(cd: edu.holycross.shot.citeobj.CiteCollectionDef):String = {
    ""
 }

 def writeCiteObject(co: edu.holycross.shot.citeobj.CiteObject):String = {
      ""
 }
 def writeCiteObjectProperty(op: edu.holycross.shot.citeobj.CitePropertyDef): String = {
      ""
 }

}
