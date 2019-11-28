package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class CexWriterCiteRelationsSpec extends FlatSpec {

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

#!relations
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.3-8.22.10
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.6-8.22.7
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0

"""

  def loadLibrary(cexString:String = cex, delimOne:String = "#", delimTwo:String = ","):CiteLibrary = {
    val library = CiteLibrary(cexString,"#",",")
    library
  }


  val lib:CiteLibrary = loadLibrary()
  val rs:CiteRelationSet = lib.relationSet.get

  "A CexWriter" should "serialize a CiteTriple" in {

    val expected:String = "urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5"

    val urn1:Urn = Cite2Urn("urn:cite2:fufolio:hdtAlign.blackwell:1")
    val relation:Cite2Urn = Cite2Urn("urn:cite2:cite:verbs.v1:aligns")
    val urn2:Urn = CtsUrn("urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5")
    val triple:CiteTriple = CiteTriple(urn1,relation,urn2)

    val serialized:String = CexWriter.writeCiteTriple(triple)

  	assert ( serialized == expected )
  }


  it should "serialize a CiteRelationSet" in {
    val crs:CiteRelationSet = rs
    val rsCex:String = CexWriter.writeCiteRelationBlock(rs, standalone = true)
    val relationsLib:CiteLibrary = CiteLibrary(rsCex,"#",",")
    val newRelSet:CiteRelationSet = relationsLib.relationSet.get
    assert( newRelSet == rs )
  }
  


  it should "serialize a CiteRelationSet with an alternate delimiter" in pending
  /*
  {
    val delim:String = "^"
    val crs:CiteRelationSet = rs
    val rsCex:String = CexWriter.writeCiteRelationBlock(rs, standalone = true, delim1 = delim)
    val relationsLib:CiteLibrary = CiteLibrary(rsCex,delim,",")
    val newRelSet:CiteRelationSet = relationsLib.relationSet.get
    assert( newRelSet == rs )
  }
  */


}

