package edu.furman.classics.citewriter

import edu.holycross.shot.cite._
import org.scalatest._
import edu.holycross.shot.cite._
import edu.holycross.shot.cex._
import edu.holycross.shot.scm._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._

class ExportTest extends FlatSpec {

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
"""

  val repo = CiteCollectionRepository(cex,"#",",")
  val oneRecto = repo.citableObject(Cite2Urn("urn:cite2:hmt:msA.v1:1r"))
  val oneVerso = repo.citableObject(Cite2Urn("urn:cite2:hmt:msA.v1:1v"))

  val rvProperty = Cite2Urn("urn:cite2:hmt:msA.v1.rv:")
  val seqProperty = Cite2Urn("urn:cite2:hmt:msA.v1.sequence:")
  val codexProperty = Cite2Urn("urn:cite2:hmt:msA.v1.codex:")


  "The cexwriter trait"  should "write a citable node" in {
      val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:msA:1.1")
  	val psg:String = "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	val cn:CitableNode = CitableNode(urn, psg)
  	val serialized:String = CexWriter.writeCitableNode(cn)
  	val expected:String = "urn:cts:greekLit:tlg0012.tlg001:msA:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	assert ( serialized == expected )
   
  }


}
