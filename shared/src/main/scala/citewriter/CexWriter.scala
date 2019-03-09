package edu.furman.classics.citewriter

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

import scala.scalajs.js
import scala.scalajs.js.annotation._

/** Providing serializations for Homer Multitext Data **/
@JSExportTopLevel("CexWriter")
object CexWriter extends CiteWriter {

  val defaultDelim:String = "#"
  val defaultSecondDelim:String = ","

  val defaultCexMetadata:String = """#!cexversion
3.0

#!citelibrary
name#CEX Library created by CEXWriter
urn#urn:cite2:cex:TEMPCOLL.TEMPVERSION:TEMP_ID
license#Public Domain."""

  /* General */

  def writeCexMetadata():String = {
    defaultCexMetadata
  }

  def writeCexMetadata(name:Option[String] = None, urn:Option[Cite2Urn] = None, license:Option[String] = None, delimiter:String = defaultDelim):String = {
    val cexName:String = name match {
      case Some(n) => n
      case None => "CEX Library created by CEXWriter"
    }
    val cexUrn:Cite2Urn = urn match {
      case Some(u) => u
      case None => Cite2Urn("urn:cite2:cex:TEMPCOLL.TEMPVERSION:TEMP_ID")
    }
    val cexLicense:String = license match {
      case Some(n) => n
      case None => "Public Domain."
    }
    val header:String = s"""#!cexversion\n3.0\n"""
    val h1:String = "#!citelibrary"
    val h2:String = s"name${delimiter}${cexName}"
    val h3:String = s"urn${delimiter}${cexUrn}"
    val h4:String = s"license${delimiter}${cexLicense}"

    val sv:Vector[String] = Vector(header, h1, h2, h3, h4)
    sv.mkString("\n")
  }


  def writeUrn(u:Urn):String = {
    u.toString
  }

  /* OHCO2 */

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

 def writeCtsCatalogEntry(ce: edu.holycross.shot.ohco2.CatalogEntry, delimiter:String = defaultDelim): String = {
   s"""${ce.urn}${delimiter}${ce.citationScheme}${delimiter}${ce.groupName}${delimiter}${ce.workTitle}${delimiter}${ce.versionLabel.getOrElse("")}${delimiter}${ce.exemplarLabel.getOrElse("")}${delimiter}${ce.online}${delimiter}${ce.lang}"""
 }

 def writeCtsCatalogEntry(ce: edu.holycross.shot.ohco2.CatalogEntry): String = {
    writeCtsCatalogEntry(ce, defaultDelim)
 }

 def writeCtsCatalogBlock(vce:Vector[edu.holycross.shot.ohco2.CatalogEntry], delimiter:String = defaultDelim):String = {
    val blockHeader:String = """#!ctscatalog"""
    val header:String = """urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang"""
    val entries:Vector[String] = vce.map(ce => writeCtsCatalogEntry(ce, delimiter))
    (Vector(blockHeader,header) ++ entries).mkString("\n")
  }


def writeCtsDataBlock(vcn:Vector[CitableNode], delimiter:String = defaultDelim) = {
    val blockHeader:String = """#!ctsdata"""
    val data:Vector[String] = vcn.map( cn => writeCitableNode(cn, delimiter) )
    (Vector(blockHeader) ++ data).mkString("\n")
}

def writeTextRepository(tr:TextRepository, standalone:Boolean = false, delimiter:String = defaultDelim) = {
  val cexMetadata:String = {
    if (standalone) writeCexMetadata() else ""
  }
  val o2cat:String = writeCtsCatalogBlock(tr.catalog.texts)
  val o2data:String = writeCtsDataBlock(tr.corpus.nodes)
  val vec:Vector[String] = Vector(cexMetadata, o2cat, o2data)
  vec.mkString("\n\n")
}


 /* CiteObject */

  def writeCitePropertyDef(cpd:CitePropertyDef, delim1:String, delim2:String):String = {
      val urn:String = cpd.urn.toString
      val label:String = cpd.label
      val propType:String = cpd.propertyType.cex
      val vocabList:String = cpd.vocabularyList.mkString(delim2)
      val vec:Vector[String] = Vector(
        urn,
        label,
        propType,
        vocabList
      )
      vec.mkString(delim1)
  }

  def writeCitePropertyDef(cpd:CitePropertyDef):String = {
      writeCitePropertyDef(cpd, defaultDelim, defaultSecondDelim)
  }

  def writeCiteObject(co:CiteObject):String = {
    ""
  }

  def writeCitePropertyValue(pv:CitePropertyValue):String = {
    "" 
  }

  def writeCiteCollectionDef(ccd:CiteCollectionDef):String = {
    ""
  }

  def writeCiteCollectionsBlock(cr:CiteCatalog):String = {
    ""
  }

  /* CiteRelation */

  def writeCiteTriple(cr:CiteTriple):String = {
    ""
  } 
  def writeCiteRelationSet(cr:CiteRelationSet):String = {
    ""
  }


}
