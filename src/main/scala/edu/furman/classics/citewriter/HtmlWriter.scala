package edu.furman.classics.citewriter

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

/** Providing serializations for Homer Multitext Data **/
object HtmlWriter extends CiteWriter {

  def writeCitableNode(cn:CitableNode):String = {
    val passage:String = writeCtsUrnPassage(cn.urn)	
    val urn:String = writeUrn(cn.urn)  
    val text:String = writeCitableNodeText(cn)
    s"""<span class="hmt_ohco2_citableNodeContainer" id="${cn.urn}">${passage}${text}${urn}</span>"""
  }

  def writeCitableNodeText(cn:CitableNode):String = {
    s"""<span class="hmt_ohco2_citableNodeText">${cn.text}</span>"""
  }

  def writeCtsUrnPassage(u:CtsUrn):String = {
    if (u.passageComponent.size > 0) {
      s"""<span class="hmt_ohco1_passageComponent">${u.passageComponent}</span>"""
    } else {
      ""    
    }
  }

  def writeUrn(u:Urn):String = {
  	s"""<span class="hmt_ohco2_citableNodeUrn">${u}</span>"""
  }

 def writeCiteCollectionDef(cd: edu.holycross.shot.citeobj.CiteCollectionDef): String = ""
 def writeCiteObject(co: edu.holycross.shot.citeobj.CiteObject): String = ""
 def writeCiteObjectProperty(op: edu.holycross.shot.citeobj.CitePropertyDef): String = ""
 def writeCtsCatalogEntry(ce: edu.holycross.shot.ohco2.CatalogEntry): String = ""

}
