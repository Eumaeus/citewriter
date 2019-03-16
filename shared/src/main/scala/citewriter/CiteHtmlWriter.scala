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
@JSExportAll
trait CiteHtmlWriter extends CiteWriter {

/* General */

  def writeUrn(u:Urn):String = {
    u match {
      case CtsUrn(_) => s"""<span class="cite_urn ctsUrn">${u}</span>"""
      case _ => s"""<span class="cite_urn cite2Urn">${u}</span>"""
    }
  } 

  def groupCorpusByText(c:Corpus):Vector[Corpus] = {
    import scala.collection.mutable.LinkedHashMap
    val vcn:Vector[(CtsUrn, CitableNode)] = c.nodes.map(cn => {
      (cn.urn.dropPassage, cn)
    })
    //val v1:Vector[(Int, Int, String)] = Vector((1,1,"one-one"),(1,2,"one-two"),(2,1,"two-one"),(2,2,"two-two"))
    val v2 = vcn.zipWithIndex.groupBy( n => n._1._1)
    val v3 = LinkedHashMap(v2.toSeq sortBy (_._2.head._2): _*)
    val v4 = v3 mapValues (_ map (_._1))
    val v5:Vector[(CtsUrn, scala.collection.immutable.Vector[(CtsUrn, CitableNode)])] = v4.toVector
    val v6:Vector[Corpus] = v5.map( t => {
      val nodes:Vector[CitableNode] = t._2.map(_._2)
      Corpus(nodes)
    })
    v6
  }

  /** 
  *   Group by citation value.
      If there are different depths, or all are 1 level, do nothing.
      If there are two, group by the top level.
      If there are more than two, group by the top two.
  *
  **/
  def groupCorpusByDepth(c:Corpus):Vector[Corpus] = {
    // if the nodes are of different depths, of if they are all 1-level, punt
    val firstDepth:Int = c.nodes.head.urn.citationDepth.head
    val depthCheck:Boolean = {
      val otherDepths:Vector[CitableNode] = c.nodes.filter( n => {
        n.urn.citationDepth.head != firstDepth
      })
      ((otherDepths.size == 0) | (firstDepth == 1))
    }
    if (depthCheck) {
       groupCorpusByDepth(c, firstDepth-1 )
    } else Vector(c)
  }

  def groupCorpusByDepth(c:Corpus, level:Int):Vector[Corpus] = {
    import scala.collection.mutable.LinkedHashMap
    val v1:Vector[(String,CitableNode)] = c.nodes.map( cn => {
      val passage:String = cn.urn.collapsePassageTo(level).passageComponent
      (passage, cn)
    })
    val v2 = v1.zipWithIndex.groupBy( n => n._1._1)
    val v3 = LinkedHashMap(v2.toSeq sortBy (_._2.head._2): _*)
    val v4 = v3 mapValues (_ map (_._1))
    val v5:Vector[(String, scala.collection.immutable.Vector[(String,CitableNode)])] = v4.toVector
    val v6:Vector[Corpus] = v5.map( t => {
      val nodes:Vector[CitableNode] = t._2.map(_._2)
      Corpus(nodes)
    })
    v6
  }


/* OHCO2 */

  def writeCitableNode(cn:CitableNode):String = {
    writeCitableNode(cn, false)
  }

  def writeCitableNode(cn:CitableNode, tokenized:Boolean):String = {
    val passage:String = writeCtsUrnPassage(cn.urn) 
    val urn:String = writeUrn(cn.urn)  
    val text:String = writeCitableNodeText(cn)
    val nodeClass:String = tokenized match {
      case true => {
        "ohco2_citableNodeContainer ohco2_token"
      }
      case false => {
        "ohco2_citableNodeContainer"
      }
    }
    s"""<span class="${nodeClass}" id="${cn.urn}">${passage}${text}${urn}</span>"""
  }

  /**
  *  If 'tokenized' put a marker on citable nodes and divide
  *  by the 2nd-tier of the citatation.
  **/
  def writeCorpus(corpus:Corpus, tokenized:Boolean = false, catalog:Option[edu.holycross.shot.ohco2.Catalog] = None):String = {
    
    // Group by version and citation
    val versionVec:Vector[Corpus] = groupCorpusByText(corpus)
    val versionsHtml:Vector[String] = {
      versionVec.map( vv => {
        val groupedCorp:Vector[Corpus] = groupCorpusByDepth(vv)
        val corpVec:Vector[String] = groupedCorp.map( gc => {
          val shortPassages:Boolean = {
            val longestNode:Int = gc.nodes.map(_.text.size).max
            //println(s"${gc.nodes.head.urn.dropPassage} = longest is ${longestNode}")
            longestNode < 20
          }
          val corpNodes:Vector[String] = gc.nodes.map( writeCitableNode(_, tokenized = tokenized) )
          val open:Vector[String] = {
            if ( (tokenized) | (shortPassages)) {

              Vector(
                s"""<div class="ohco2_passageGroup ohco2_tokenized">""",
                s"""<span class="ohco2_passageComponent">${gc.nodes.head.urn.collapsePassageBy(1).passageComponent}</span>"""
              )
            } else {
              Vector(
                s"""<div class="ohco2_passageGroup">""",
                s"""<span>"""
              )            }
          }
          val close:Vector[String] = { Vector("</span></div>") }
          open ++ corpNodes ++ close
        }).flatten
        val catEntry:Vector[String] = {
          catalog match {
            case Some(cat) => {
              val ce:Vector[CatalogEntry] = cat.entriesForUrn(vv.nodes.head.urn.dropPassage)
              ce.map(writeCtsCatalogEntry(_))
            }
            case None => Vector[String]()
          }
        }
        val head:Vector[String] = Vector("""<div class="ohco2_versionCorpus">""")
        val close:Vector[String] = Vector("</div>")
        head ++ corpVec ++ catEntry ++ close
      }).flatten
    }

    versionsHtml.mkString("\n")

  }

  def writeCitableNodeText(cn:CitableNode):String = {
    s"""<span class="ohco2_citableNodeText">${cn.text}</span>"""
  }

  def writeCtsUrnPassage(u:CtsUrn):String = {
    if (u.passageComponent.size > 0) {
      s"""<span class=" ohco2_passageComponent">${u.passageComponent}</span>"""
    } else {
      ""    
    }
  }

  def writeCtsCatalogEntry(ce: edu.holycross.shot.ohco2.CatalogEntry): String = {
    val urn:String = writeUrn(ce.urn)
    val citationScheme:String = {
      s"""<span class="ohco2_catalogEntry_citationScheme">${ce.citationScheme}</span>"""
    }
    val groupName:String = s"""<span class="ohco2_catalogEntry_groupName">${ce.groupName}</span>"""
    val workTitle:String = s"""<span class="ohco2_catalogEntry_workTitle">${ce.workTitle}</span>"""
    val versionLabel:Option[String] = ce.versionLabel
    val exemplarLabel:Option[String] = ce.exemplarLabel
    val versionString:String = {
        versionLabel match {
          case Some(vl) => {
            exemplarLabel match {
              case Some(el) => {
                  s"""<span class="ohco2_catalogEntry_versionLabel">${vl}: ${el}</span>"""
              }
              case None => {
                  s"""<span class="ohco2_catalogEntry_versionLabel">${vl}</span>"""
              }
            }
          }
          case None => ""
        }
    }
    val online:String = ce.online match {
      case true => """<span class="ohco2_catalogEntry_online">online</span>"""
      case false => ""
    }
    val lang:String = s"""<span class="ohco2_catalogEntry_lang">${ce.lang}</span>"""
    val htmlVec:Vector[String] = Vector(
        """<span class="ohco2_catalogEntry">""",
        lang,
        groupName,
        workTitle,
        versionString,
        online,
        citationScheme,
        urn,
        """</span>"""
    )
    htmlVec.mkString(" ")
 }

/* CITE Object */


  def writeCitePropertyDef(cpd:CitePropertyDef):String = {
    val strVec:Vector[String] = Vector(
      """<span class="citeobj_propertyDef">""",
      writeUrn(cpd.urn),
      s"""<span class="citeobj_propertyDef_label">${cpd.label}</span>""",
      s"""<span class="citeobj_propertyDef_type">${cpd.propertyType.cex}</span>""",
      s"""<span class="citeobj_propertyDef_vocab">${cpd.vocabularyList.mkString(", ")}</span>""",
      """</span>"""
    )
    strVec.mkString
  }

  def writeCiteObject(co:CiteObject, cd:CiteCollectionDef):String = {
    val urn:String = writeUrn(co.urn)
    val label:String = s"""<span class="citeobj_objLabel">${co.label}</span>"""
    val props:Vector[String] = co.propertyList.map( pi => {
      writeCitePropertyValue(pi)
    })
    val strVec:Vector[String] = {
      Vector("""<span class="citeobj_object">""") ++ Vector(label) ++ Vector(urn) ++ props ++ Vector("</span>")
    }
    strVec.mkString("\n")
  }

  def writeCitePropertyValue(pv:CitePropertyValue):String = {
      val strVec:Vector[String] = Vector(
        """<span class="citeobj_propertyValue">""",
        writeUrn(pv.urn),
        s"""<span class="citeobj_propertyValue_value">${pv.propertyValue}</span>""",
        """</span>"""
      )
      strVec.mkString
  }

  def writeCitePropertyValue(pi:CitePropertyImplementation):String = {
    val label:String = s"""<span class="citeobj_propertyDef_label">${pi.propertyDef.label}</span>"""
    val propType:String = s"""<span class="citeobj_propertyDef_type">${pi.propertyDef.propertyType.cex}</span>"""
    val propValue:String = s"""<span class="citeobj_propertyValue_value">${pi.propertyValue.toString}</span>"""
    val propUrn:String = writeUrn(pi.urn)

    val htmlVec:Vector[String] = Vector(
        """<span class="citeobj_propertyValue">""",
        label,
        propType,
        propValue,
        propUrn,
        """</span>"""
    )
    htmlVec.mkString("\n")
  }

  def writeCiteCollectionDef(ccd:CiteCollectionDef) = {
    val urn:String = writeUrn(ccd.urn)
    val label:String = s"""<span class="citeobj_collectionLabel">${ccd.collectionLabel}</span>"""
    val license:String = s"""<span class="citeobj_collLicense">${ccd.license}</span>"""
    val propDefs:Vector[String] = ccd.propertyDefs.map( pd => {
      writeCitePropertyDef(pd)
    })
    val strVec:Vector[String] = {
      Vector("""<span class="citeobj_collectionDef">""") ++ Vector(label) ++ Vector(urn) ++ propDefs ++ Vector(license) ++ Vector("</span>")
    }
    strVec.mkString("\n")
 
  }

  def writeCiteTriple(ct:CiteTriple):String = {
     writeCiteTriple(ct, None)
  }

  def writeCiteTriple(ct:CiteTriple, cro:Option[CiteCollectionRepository]):String = {
      val subj:Urn = ct.urn1
      val obj:Urn = ct.urn2
      val rel:Cite2Urn = ct.relation
      val subjString:String = s"""<span class="citerelations_subject">${writeUrn(subj)}</span>"""
      val objString:String = s"""<span class="citerelations_object">${writeUrn(obj)}</span>"""
      val relString:String = {
        cro match {
          case Some(cr) => {
            try {
              val co:CiteObject = cr.citableObject(rel)
              val label:String = co.label

              s"""<span class="citerelations_relation"><span class="citerelations_relationLabel">${label}</span><span class="citerelations_relationUrn">${writeUrn(rel)}</span></span>"""
            } catch {
              case e:Exception => {
                s"""<span class="citerelations_relation">${writeUrn(rel)}</span>"""  
              }
            }

          }
          case None => {
            s"""<span class="citerelations_relation">${writeUrn(rel)}</span>"""  
          }
        }
      }
      val htmlVec:Vector[String] = Vector(
        """<span class="citerelations_citeTriple">""",
        subjString,
        relString,
        objString,
        """</span>"""
      )
      htmlVec.mkString("\n")

  }


  def writeCiteRelationSet(crs: CiteRelationSet): String = {
    writeCiteRelationSet(crs, None)
  }

  def writeCiteRelationSet(crs: CiteRelationSet, cro:Option[CiteCollectionRepository]): String = {
    val head:String = """<div class="citerelations_relationSet">"""
    val relVec:Vector[String] = crs.relations.toVector.map(writeCiteTriple(_, cro))
    val close:String = """</div>"""
    ( Vector(head) ++ relVec ++ Vector(close) ).mkString("\n")
  }



}
