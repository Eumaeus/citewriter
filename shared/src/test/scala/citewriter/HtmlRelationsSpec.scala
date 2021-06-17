package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class HtmlRelationsSpec extends FlatSpec {

val cex:String = """#!cexversion
3.0

#!citelibrary
name#Fragment from Herodotus' Histories, Book VIII on Papyrus Oxyrhynchus 2099, dated to early 2nd century AD.
urn#urn:cite2:cex:fufolio.2018a:POxy2099
license#CC Share Alike.  For details, see more info.

#!datamodels
Collection#Model#Label#Description
urn:cite2:fufolio:hdtAlign.blackwell:#urn:cite2:cite:datamodels.v1:alignment#Text Alignment Model#The CITE model for text alignment. See documentation at <https://eumaeus.github.io/citealign/>.
urn:cite2:fufolio:iliadAlign.blackwell:#urn:cite2:cite:datamodels.v1:alignment#Text Alignment Model#The CITE model for text alignment. See documentation at <https://eumaeus.github.io/citealign/>.

#!citecollections
URN#Description#Labelling property#Ordering property#License
urn:cite2:cite:datamodels.v1:#CITE data models#urn:cite2:cite:datamodels.v1.label:##Public domain
urn:cite2:cite:verbs.v1:#Collection of verbal relations#urn:cite2:cite:verbs.v1.label:##Public Domain
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
// Hdt.
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.3-8.22.10
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.6-8.22.7
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.12

#!citeproperties
Property#Label#Type#Authority list
urn:cite2:cite:verbs.v1.urn:#URN#Cite2Urn#
urn:cite2:cite:verbs.v1.label:#label#String#
urn:cite2:cite:verbs.v1.description:#description#String#

#!citedata
urn#label#description
urn:cite2:cite:verbs.v1:commentsOn#comments on#subject[Urn] comments on object[Urn]
urn:cite2:cite:verbs.v1:illustrates#illustrates#subject[Urn] comments on object[Urn]
urn:cite2:cite:verbs.v1:hasOnIt#has on it#subject[Urn] comments on object[Urn]
urn:cite2:cite:verbs.v1:aligns#aligns#subject[CiteUrn] is an alignment that includes passage[CtsUrn]

#!citeproperties
Property#Label#Type#Authority list
urn:cite2:cite:datamodels.v1.urn:#Data model#Cite2Urn#
urn:cite2:cite:datamodels.v1.label:#Label#String#
urn:cite2:cite:datamodels.v1.description:#Description#String#

#!citedata
urn#label#description
urn:cite2:cite:datamodels.v1:alignment#text alignment model#Aligning passages of OHCO2 texts. See <https://eumaeus.github.io/citealign/>.

"""

 def loadLibrary(cexString:String = cex):CiteLibrary = {
    val library = CiteLibrary(cexString)
    library
  }

  val lib:CiteLibrary = loadLibrary()

  def showMe(v:Any):Unit = {
    v match {
      case _:Iterable[Any] => println(s"""\n----\n${v.asInstanceOf[Iterable[Any]].mkString("\n")}\n----\n""")
      case _ => println(s"\n-----\n${v}\n----\n")
    }
  }


/*
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.3-8.22.10
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.6-8.22.7
*/

  "A HtmlWriter" should "write a CiteTriple" in {

    val s:Urn = Cite2Urn("urn:cite2:fufolio:hdtAlign.blackwell:1")
    val v:Cite2Urn = Cite2Urn("urn:cite2:cite:verbs.v1:aligns")
    val o:Urn = CtsUrn("urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5")
    val ct:CiteTriple = CiteTriple(s,v,o)
    val serialized:String = HtmlWriter.writeCiteTriple(ct)
    val expected:String = """<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn" data-cite2urn="urn:cite2:fufolio:hdtAlign.blackwell:1">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn" data-cite2urn="urn:cite2:cite:verbs.v1:aligns">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn" data-ctsurn="urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5">urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5</span></span>
</span>"""
    //showMe(serialized)
    assert( serialized == expected )
  }

  it should "write a CiteTriple with labeling on the verb if possible" in {
    val s:Urn = Cite2Urn("urn:cite2:fufolio:hdtAlign.blackwell:1")
    val v:Cite2Urn = Cite2Urn("urn:cite2:cite:verbs.v1:aligns")
    val o:Urn = CtsUrn("urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5")
    val ct:CiteTriple = CiteTriple(s,v,o)
    val serialized:String = HtmlWriter.writeCiteTriple(ct, lib.collectionRepository)
    val expected:String = """<span class="citerelations_citeTriple">
<span class="citerelations_subject" data-cite2urn="urn:cite2:fufolio:hdtAlign.blackwell:1"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn" data-cite2urn="urn:cite2:cite:verbs.v1:aligns">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn" data-ctsurn="urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5">urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5</span></span>
</span>"""
    //showMe(serialized)
    //assert( serialized == expected ) 
  }

  it should "write a CiteRelationSet" in {
    val cr = lib.collectionRepository
    val crs = lib.relationSet.get
    val serialized = HtmlWriter.writeCiteRelationSet(crs)
    //showMe(serialized)
    val expected = """<div class="citerelations_relationSet">
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.12</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.6-8.22.7</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.3-8.22.10</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5</span></span>
</span>
</div>"""
  //assert(serialized == expected)
  }

 it should "write a CiteRelationSet with relations labeled" in {
    val cr = lib.collectionRepository
    val crs = lib.relationSet.get
    val serialized = HtmlWriter.writeCiteRelationSet(crs, cr)
    //showMe(serialized)
    val expected = """<div class="citerelations_relationSet">
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.12</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:2</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.6-8.22.7</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.3-8.22.10</span></span>
</span>
<span class="citerelations_citeTriple">
<span class="citerelations_subject"><span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell:1</span></span>
<span class="citerelations_relation"><span class="citerelations_relationLabel">aligns</span><span class="citerelations_relationUrn"><span class="cite_urn cite2Urn">urn:cite2:cite:verbs.v1:aligns</span></span></span>
<span class="citerelations_object"><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5</span></span>
</span>
</div>"""
  //assert(serialized == expected)
  }
}

