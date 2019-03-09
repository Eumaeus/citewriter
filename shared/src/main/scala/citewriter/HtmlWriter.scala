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
@JSExportTopLevel("HtmlWriter")
object HtmlWriter extends CiteWriter {

  def writeCitableNode(cn:CitableNode):String = {
    val passage:String = writeCtsUrnPassage(cn.urn)	
    val urn:String = writeUrn(cn.urn)  
    val text:String = writeCitableNodeText(cn)
    s"""<span class="ohco2_citableNodeContainer" id="${cn.urn}">${passage}${text}${urn}</span>"""
  }

  def writeCitableNodeText(cn:CitableNode):String = {
    s"""<span class="ohco2_citableNodeText">${cn.text}</span>"""
  }

  def writeCtsUrnPassage(u:CtsUrn):String = {
    if (u.passageComponent.size > 0) {
      s"""<span class="ohco1_passageComponent">${u.passageComponent}</span>"""
    } else {
      ""    
    }
  }

  def writeUrn(u:Urn):String = {
    u match {
      case CtsUrn(_) => s"""<span class="ohco2_ctsUrn">${u}</span>"""
      case _ => s"""<span class="cite_cite2Urn">${u}</span>"""
    }
  } 

  def writeCtsCatalogEntry(ce: edu.holycross.shot.ohco2.CatalogEntry): String = {
    val urn = ce.urn
    val citationScheme = ce.citationScheme
    val groupName = ce.groupName
    val workTitle = ce.workTitle
    val versionLabel = ce.versionLabel.getOrElse("None.")
    val exemplarLabel = ce.exemplarLabel.getOrElse("None.")
    val online = ce.online
    val lang = ce.lang
    """<table class="ohco2_catalogEntry"> <th> <td>value</td> </th> <tr> <td>urn</td> <td>${urn}</td> </tr> <tr> <td>citation scheme</td> <td>${citationScheme}</td> </tr> <tr> <td>groupName</td> <td>${groupName}</td> </tr> <tr> <td>workTitle</td> <td>${workTitle}</td> </tr> <tr> <td>versionLabel</td> <td>${versionLabel}</td> </tr> <tr> <td>exemplarLable</td> <td>${exemplarLable}</td> </tr> <tr> <td>online</td> <td>${online}</td> </tr> <tr> <td>lang</td> <td>${lang}</td> </tr> </table>"""

 }

  def writeCitePropertyDef(opd:CitePropertyDef):String = {
    ""
  }

  def writeCiteObject(co:CiteObject):String = {
    ""
  }
  
  def writeCitePropertyValue(pv:CitePropertyValue):String = {
    "" 
  }

  def writeCiteTriple(cr:CiteTriple):String = {
    ""
  }

  def writeCiteRelationSet(cr: CiteRelationSet): String = {
    ""
  }

  def writeCiteCollectionDef(ccd:CiteCollectionDef) = {
    ""
  }


}
