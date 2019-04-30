package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class HtmlWriterSpec extends FlatSpec {

val cex:String = """#!cexversion
3.0

#!citelibrary
name#Fragment from Herodotus' Histories, Book VIII on Papyrus Oxyrhynchus 2099, dated to early 2nd century AD.
urn#urn:cite2:cex:fufolio.2018a:POxy2099
license#CC Share Alike.  For details, see more info.

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:#book/section/token#Herodotus#Histories#Tokenized Greek, Godley, ed.##true#grc

#!ctsdata
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0#Ἀθηναίων
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.1#δὲ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.2#νέας

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
"""

 def loadLibrary(cexString:String = cex):CiteLibrary = {
    val library = CiteLibrary(cexString,"#",",")
    library
  }

  val lib:CiteLibrary = loadLibrary()

  def showMe(v:Any):Unit = {
    v match {
      case _:Iterable[Any] => println(s"""\n----\n${v.asInstanceOf[Iterable[Any]].mkString("\n")}\n----\n""")
      case _ => println(s"\n-----\n${v}\n----\n")
    }
  }

  /* OHCO2 Stuff */

  "A HtmlWriter" should "serialize a citable node" in {
  	val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  	val psg:String = "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	val cn:CitableNode = CitableNode(urn, psg)
  	val serialized:String = HtmlWriter.writeCitableNode(cn)
  	val expected:String = """<span class="ohco2_citableNodeContainer" id="urn:cts:greekLit:tlg0012.tlg001.msA:1.1"><span class="ohco2_citableNodeText">Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος</span><span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0012.tlg001.msA:1.1</span></span>"""
    //showMe(serialized)
  	assert ( serialized == expected )
  }

  it should "serialize a catalog entry" in {
    val ce: edu.holycross.shot.ohco2.CatalogEntry = {
      lib.textRepository.get.catalog.texts.head
    }
    val urn = ce.urn
    val citationScheme = ce.citationScheme
    val groupName = ce.groupName
    val workTitle = ce.workTitle
    val versionLabel = ce.versionLabel.getOrElse("")
    val exemplarLabel = "Some exemplar"
    val online = ce.online
    val lang = ce.lang
  
    val serialized:String = HtmlWriter.writeCtsCatalogEntry(ce)  
    println(s"\n\n${serialized}\n\n")
    val expected:String = """<div class="ohco2_catalogEntry"> <span class="ohco2_catalogEntry_lang">grc</span> <span class="ohco2_catalogEntry_groupName">Herodotus</span> <span class="ohco2_catalogEntry_workTitle">Histories</span> <span class="ohco2_catalogEntry_versionLabel">Tokenized Greek, Godley, ed.</span> <span class="ohco2_catalogEntry_online">online</span> <span class="ohco2_catalogEntry_citationScheme">book/section/token</span> <span class="cite_urn ctsUrn">urn:cts:greekLit:tlg0016.tlg001.grc_tokens:</span> </div>"""
    //showMe(serialized)
    assert(serialized == expected)
  }

  /* CiteObject */

  it should "write a CitePropertyDef" in {
    val expected:String = """<span class="citeobj_propertyDef"><span class="cite_urn cite2Urn">urn:cite2:hmt:msB.v1.rv:</span><span class="citeobj_propertyDef_label">Recto or Verso</span><span class="citeobj_propertyDef_type">String</span><span class="citeobj_propertyDef_vocab">recto, verso</span></span>"""
    val propDef = CitePropertyDef(Cite2Urn("urn:cite2:hmt:msB.v1.rv:"),"Recto or Verso",ControlledVocabType,Vector("recto", "verso"))
    val serialized:String = HtmlWriter.writeCitePropertyDef(propDef)
    assert( expected == serialized )
  }

  it should "write a CitePropertyValue with just a CitePropertyValue as a parameter" in {
    val expected:String = """<span class="citeobj_propertyValue"><span class="cite_urn cite2Urn">urn:cite2:hmt:msB.v1.rv:12r</span><span class="citeobj_propertyValue_value">recto</span></span>"""
    val propVal = CitePropertyValue(Cite2Urn("urn:cite2:hmt:msB.v1.rv:12r"),"recto")
    val serialized:String = HtmlWriter.writeCitePropertyValue(propVal)
    assert( expected == serialized )
  }

  it should "write a CitePropertyValue from a full CitePropertyImplementation parameter" in {

    val cr:CiteCollectionRepository = lib.collectionRepository.get
    val co:CiteObject = cr.citableObject(Cite2Urn("urn:cite2:fufolio:hdtAlign.blackwell:1"))

    val prop:CitePropertyImplementation = co.propertyList.head

    val serialized:String = HtmlWriter.writeCitePropertyValue(prop)
    val expected:String = """<span class="citeobj_propertyValue">
<span class="citeobj_propertyDef_label">Description</span>
<span class="citeobj_propertyDef_type">String</span>
<span class="citeobj_propertyValue_value">Herodotus Alignment 1</span>
<span class="cite_urn cite2Urn">urn:cite2:fufolio:hdtAlign.blackwell.description:1</span>
</span>"""
    assert( serialized == expected )

  }

  it should "write a CiteObject" in {
    val u:Cite2Urn = Cite2Urn("urn:cite2:fufolio:iliadAlign.blackwell:3")
    val cr:CiteCollectionRepository = lib.collectionRepository.get
    val cd:CiteCollectionDef = cr.catalog.collection(u.dropSelector).get
    val obj:CiteObject = cr.citableObject(u)
    val expected:String = """<span class="citeobj_object">
<span class="citeobj_objLabel">Iliad 1</span>
<span class="cite_urn cite2Urn">urn:cite2:fufolio:iliadAlign.blackwell:3</span>
<span class="citeobj_propertyValue">
<span class="citeobj_propertyDef_label">Description</span>
<span class="citeobj_propertyDef_type">String</span>
<span class="citeobj_propertyValue_value">Iliad Alignment 1</span>
<span class="cite_urn cite2Urn">urn:cite2:fufolio:iliadAlign.blackwell.description:3</span>
</span>
<span class="citeobj_propertyValue">
<span class="citeobj_propertyDef_label">Editor</span>
<span class="citeobj_propertyDef_type">String</span>
<span class="citeobj_propertyValue_value">cwb</span>
<span class="cite_urn cite2Urn">urn:cite2:fufolio:iliadAlign.blackwell.editor:3</span>
</span>
<span class="citeobj_propertyValue">
<span class="citeobj_propertyDef_label">Date</span>
<span class="citeobj_propertyDef_type">String</span>
<span class="citeobj_propertyValue_value">2/12/2019</span>
<span class="cite_urn cite2Urn">urn:cite2:fufolio:iliadAlign.blackwell.date:3</span>
</span>
</span>"""
    val serialized:String = HtmlWriter.writeCiteObject(obj, cd)
    assert (serialized == expected)
  }
  
  it should "write a CiteCollectionDef" in {
    val cr:CiteCollectionRepository = lib.collectionRepository.get
    val u:Cite2Urn = Cite2Urn("urn:cite2:fufolio:iliadAlign.blackwell:")
    val cd:CiteCollectionDef = cr.catalog.collection(u).get
    val serialized:String = HtmlWriter.writeCiteCollectionDef(cd)
    // println(s"\n\n${serialized}\n\n")
  }

}

