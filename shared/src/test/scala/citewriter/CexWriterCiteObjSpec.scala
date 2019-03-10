package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class CexWriterCiteObjSpec extends FlatSpec {

  //val cexPath:String = "src/test/resources/minimal.cex"
  val cex:String = """#!cexversion
3.0

#!citelibrary
name#Fragment from Herodotus' Histories, Book VIII on Papyrus Oxyrhynchus 2099, dated to early 2nd century AD.
urn#urn:cite2:cex:fufolio.2018a:POxy2099
license#CC Share Alike.  For details, see more info.


#!citecollections
URN#Description#Labelling property#Ordering property#License
urn:cite2:fufolio:hdtAlign.blackwell:#Translation alignments#urn:cite2:fufolio:hdtAlign.blackwell.label:##Public Domain
urn:cite2:fufolio:iliadAlign.blackwell:#Translation alignments#urn:cite2:fufolio:iliadAlign.blackwell.label:##Public Domain

#!citeproperties
Property#Label#Type#Authority list
urn:cite2:fufolio:hdtAlign.blackwell.urn:#Alignment Record#Cite2Urn#
urn:cite2:fufolio:hdtAlign.blackwell.label:#Label#String#
urn:cite2:fufolio:hdtAlign.blackwell.description:#Description#String#
urn:cite2:fufolio:hdtAlign.blackwell.editor:#Editor#String#
urn:cite2:fufolio:hdtAlign.blackwell.date:#Date#String#

#!citeproperties
Property#Label#Type#Authority list
urn:cite2:fufolio:iliadAlign.blackwell.urn:#Alignment Record#Cite2Urn#
urn:cite2:fufolio:iliadAlign.blackwell.label:#Label#String#
urn:cite2:fufolio:iliadAlign.blackwell.description:#Description#String#
urn:cite2:fufolio:iliadAlign.blackwell.editor:#Editor#String#
urn:cite2:fufolio:iliadAlign.blackwell.date:#Date#String#

#!citedata
urn#label#description#editor#date
urn:cite2:fufolio:hdtAlign.blackwell:1#Hdt. 1#Herodotus Alignment 1#cwb#2/12/2019
urn:cite2:fufolio:hdtAlign.blackwell:2#Hdt. 2#Herodotus Alignment 2#cwb#2/12/2019

#!citedata
urn#label#description#editor#date
urn:cite2:fufolio:iliadAlign.blackwell:3#Iliad 1#Iliad Alignment 1#cwb#2/12/2019
urn:cite2:fufolio:iliadAlign.blackwell:4#Iliad 2#Iliad Alignment 2#cwb#2/12/2019
urn:cite2:fufolio:iliadAlign.blackwell:5#Iliad 3#Iliad Alignment 3#cwb#2/12/2019
urn:cite2:fufolio:iliadAlign.blackwell:6#Iliad 4#Iliad Alignment 4#cwb#2/12/2019

"""

  def loadLibrary(cexString:String = cex, delimOne:String = "#", delimTwo:String = ","):CiteLibrary = {
    val library = CiteLibrary(cexString,"#",",")
    library
  }


  val lib:CiteLibrary = loadLibrary()
  val cr:CiteCollectionRepository = lib.collectionRepository.get

  "A CexWriter" should "serialize a citePropertyDef" in {
  	val urn:Cite2Urn = Cite2Urn("urn:cite2:hmt:msA.v1.rv:")
  	val label:String = "Recto or Verso"
  	val propType:CitePropertyType = ControlledVocabType
  	val vocabList:Vector[String] = Vector[String]("recto","verso")
    val propDef:CitePropertyDef = CitePropertyDef(urn, label, propType, vocabList)

    val expected:String = "urn:cite2:hmt:msA.v1.rv:#Recto or Verso#String#recto,verso"

    val serialized:String = CexWriter.writeCitePropertyDef(propDef)

  	assert ( serialized == expected )
  }

  it should "serialize a cite property value" in {
    val prop:CitePropertyValue = (cr.data ~~ Cite2Urn("urn:cite2:fufolio:iliadAlign.blackwell.description:3")).data(0)
    assert ( CexWriter.writeCitePropertyValue(prop) == "Iliad Alignment 1")
  }

  it should "serialize a cite object" in {
    val urn:Cite2Urn = Cite2Urn("urn:cite2:fufolio:iliadAlign.blackwell:3")
    val obj:CiteObject = (cr ~~ urn)(0)
    val collDef:CiteCollectionDef = cr.catalog.collection(urn.dropSelector).get
    val objCex:String = CexWriter.writeCiteObject(obj, collDef)
    val expected:String = "urn:cite2:fufolio:iliadAlign.blackwell:3#Iliad 1#Iliad Alignment 1#cwb#2/12/2019"
    assert (objCex == expected)
  }

  it should "serialize a Cite Collection Def" in {
    val expected:String = "urn:cite2:fufolio:hdtAlign.blackwell:#Translation alignments#urn:cite2:fufolio:hdtAlign.blackwell.label:##Public Domain"
    val ccd:CiteCollectionDef = cr.catalog.collection(Cite2Urn("urn:cite2:fufolio:hdtAlign.blackwell:")).get
    val serialized:String = CexWriter.writeCiteCollectionDef(ccd)  
    assert ( serialized == expected )
  }

  it should "serialize a block of Cite Collection Definitions" in {
    val expected:String = """#!citecollections
URN#Description#Labelling property#Ordering property#License
urn:cite2:fufolio:hdtAlign.blackwell:#Translation alignments#urn:cite2:fufolio:hdtAlign.blackwell.label:##Public Domain
urn:cite2:fufolio:iliadAlign.blackwell:#Translation alignments#urn:cite2:fufolio:iliadAlign.blackwell.label:##Public Domain"""
    val serialized:String = CexWriter.writeCiteCollectionsBlock(cr) 
  }

  it should "serialized a CiteProperties block" in {
    val expected:String = """#!citeproperties
Property#Label#Type#Authority list
urn:cite2:fufolio:hdtAlign.blackwell.urn:#Alignment Record#Cite2Urn#
urn:cite2:fufolio:hdtAlign.blackwell.label:#Label#String#
urn:cite2:fufolio:hdtAlign.blackwell.description:#Description#String#
urn:cite2:fufolio:hdtAlign.blackwell.editor:#Editor#String#
urn:cite2:fufolio:hdtAlign.blackwell.date:#Date#String#"""
    val ccd:CiteCollectionDef = cr.catalog.collection(Cite2Urn("urn:cite2:fufolio:hdtAlign.blackwell:")).get
    val serialized:String = CexWriter.writeCitePropertiesBlock(ccd)
    assert (serialized == expected )
  }

  it should "write a collection repository to a complete CEX file" in {
    val crCex:String = CexWriter.writeCollectionRepository(cr, true)
    val newLib:CiteLibrary = loadLibrary(crCex)
    val cr2Option:Option[CiteCollectionRepository] = newLib.collectionRepository
    assert (cr2Option != None)
    assert( cr2Option.get.catalog == cr.catalog  )
    assert( cr2Option.get.data.size == cr.data.size  )
  }


}

