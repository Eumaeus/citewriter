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

  def writeCitePropertiesBlock(ccd:CiteCollectionDef, delim1:String, delim2:String):String = {
    val headerVec:Vector[String] = Vector(
        "#!citeproperties",
        "Property#Label#Type#Authority list"
    )
    val propVec:Vector[String] = ccd.propertyDefs.map(pd => {
      writeCitePropertyDef(pd, delim1, delim2)
    })
    (headerVec ++ propVec).mkString("\n")
  }

  def writeCitePropertiesBlock(ccd:CiteCollectionDef):String = {
    writeCitePropertiesBlock(ccd, defaultDelim, defaultSecondDelim)
  }

  def writeCiteObject(co:CiteObject, cd:CiteCollectionDef, delim1:String, delim2:String):String = {
      val props:Vector[CitePropertyImplementation] = co.propertyList
      val propStr:Vector[String] = props.map(p => {
          val orderPropOpt:Option[Cite2Urn] = cd.orderingProperty
          orderPropOpt match {
            case Some(op) => {
              if (op == p.urn.dropSelector){
                p.propertyValue.toString.toFloat.toInt.toString
              } else {
                p.propertyValue.toString
              }
            }
            case None => {
                p.propertyValue.toString
            }
          }
      })
      val urnAndLabel:Vector[String] = {
          Vector( co.urn.toString, co.label)
      } 
      val allVec:Vector[String] = urnAndLabel ++ propStr
      allVec.mkString(delim1)
  }

  def writeCiteObject(co:CiteObject, cd:CiteCollectionDef):String = {
     writeCiteObject(co, cd, defaultDelim, defaultSecondDelim) 
  }

  def writeCitePropertyValue(pv:CitePropertyValue):String = {
    pv.propertyValue.toString
  }

  def writeCiteCollectionDef(cd:CiteCollectionDef, delim1:String):String = {
    val urn:String = cd.urn.toString
    val collectionLabel:String = cd.collectionLabel
    val labelProperty:String = cd.labelProperty.toString
    val orderingProperty:String = {
      cd.orderingProperty match {
        case Some(op) => op.toString
        case None => ""
      }
    }
    val license:String = cd.license
    val stringVec:Vector[String] = Vector(urn, collectionLabel, labelProperty,orderingProperty,license)
    stringVec.mkString(delim1)
  }

  def writeCiteCollectionDef(ccd:CiteCollectionDef):String = {
    writeCiteCollectionDef(ccd, defaultDelim)
  }

  def writeCiteCollectionsBlock(cr:CiteCollectionRepository, delim1:String ):String = {
    val vecDefs:Vector[CiteCollectionDef] = cr.catalog.collections
    val vecStrings:Vector[String] = vecDefs.map( ccd => {
      writeCiteCollectionDef(ccd, delim1 )
    })
    val headerVec:Vector[String] = Vector(
        "#!citecollections",
        "URN#Description#Labelling property#Ordering property#License"
    )
    (headerVec ++ vecStrings).mkString("\n")
  }

  def writeCiteCollectionsBlock(cr:CiteCollectionRepository):String = {
    writeCiteCollectionsBlock(cr, defaultDelim)
  }

  def writeCollectionRepository(cr:CiteCollectionRepository, standalone:Boolean = false, delim1:String = defaultDelim, delim2:String = defaultSecondDelim):String = {
    val cexMetadata:String = {
      if (standalone) writeCexMetadata() else ""
    }
    val crCat:String = writeCiteCollectionsBlock(cr)
    val crProps:String = {
      cr.catalog.collections.map( coll => {
        writeCitePropertiesBlock(coll, delim1, delim2)
      }).mkString("\n\n")
    }
    val citeDataHeader:String = "#!citedata"
    val citeData:String = {
       cr.catalog.collections.map( coll => {
          val citeDataPropHeader:String = {
              cr.catalog.collection(coll.urn).get.propertyDefs.map( _.urn.property).mkString(delim1)   
          }
          val dataBlock:Vector[String] = cr.objectsForCollection(coll.urn).map(obj => {
            writeCiteObject(obj, coll, delim1, delim2)
          })
          (Vector(citeDataHeader) ++ Vector(citeDataPropHeader) ++ dataBlock).mkString("\n")
      }).mkString("\n\n")
    }

    val vec:Vector[String] = Vector(cexMetadata, crCat, crProps, citeData)
    val crCex:String = vec.mkString("\n\n")
    crCex
  }

  /* CiteRelation */

  def writeCiteTriple(cr:CiteTriple):String = {
    writeCiteTriple(cr, defaultDelim)
  }

  def writeCiteTriple(cr:CiteTriple, delim1:String):String = {
    s"${cr.urn1}${delim1}${cr.relation}${delim1}${cr.urn2}"
  } 

  def writeCiteRelationSet(cr:CiteRelationSet):String = {
    writeCiteRelationSet(cr, defaultDelim)
  }

  def writeCiteRelationSet(cr:CiteRelationSet, delim1:String):String = {
    val header:String = "\n#!relations"
    val relVec:Vector[String] = cr.relations.map( t => writeCiteTriple(t, delim1) ).toVector
    val cexVec:Vector[String] = Vector(header) ++ relVec
    cexVec.mkString("\n")
  }

  def writeCiteRelationBlock(cr:CiteRelationSet, standalone:Boolean = false, delim1:String = defaultDelim):String = {
    val header:String = {
     if (standalone) writeCexMetadata(delimiter = delim1) else ""
    }
    val relCex:String = writeCiteRelationSet(cr,delim1)
    header + "\n\n" + relCex
  }

  /* Data Models */

  def writeDataModel(dm:DataModel, delim1:String = defaultDelim) = {
    val collection:Cite2Urn = dm.collection
    val model:Cite2Urn = dm.model
    val label:String = dm.label
    val description:String = dm.description
    s"${collection}${delim1}${model}${delim1}${label}${delim1}${description}"
  }

  def writeDataModelBlock(dmv:Vector[DataModel], standalone:Boolean = false, delim1:String = defaultDelim) = {
    val header:String = {
     if (standalone) writeCexMetadata(delimiter = delim1) else ""
    }
    val dmHeader:String = "#!datamodels\nCollection#Model#Label#Description"
    val dmCex:String = dmv.map( dm => {
      writeDataModel(dm, delim1)
    }).mkString("\n")
    val dmBlockCex:String = header + "\n" + dmHeader + "\n" + dmCex
    dmBlockCex
  }

  /* a whole CiteLibrary */

  def writeCiteLibrary(
    tr:Option[TextRepository] = None, 
    cr:Option[CiteCollectionRepository] = None,
    rs:Option[CiteRelationSet] = None,
    dm:Option[Vector[DataModel]] = None,
    standalone:Boolean = true,
    delim1:String = defaultDelim,
    delim2:String = defaultSecondDelim
  ):String = {
    
    val header:String = {
     if (standalone) writeCexMetadata(delimiter = delim1) else ""
    }

    val trCex:String = tr match {
      case Some(repo) => writeTextRepository(repo, false, delim1)
      case None => ""
    }
    val crCex:String = cr match {
      case Some(repo) => writeCollectionRepository(repo, false, delim1, delim2)
      case None => ""
    }
    val rsCex:String = rs match {
      case Some(repo) => writeCiteRelationBlock(repo, false, delim1)
      case None => ""
    }
    val dmCex:String = dm match {
      case Some(repo) => writeDataModelBlock(repo, false, delim1)
      case None => ""
    }

    val cexVec:Vector[String] = Vector(header, trCex, crCex, rsCex, dmCex)
    cexVec.mkString("\n")

  }



}
