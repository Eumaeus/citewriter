package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class CexWriterOhco2Spec extends FlatSpec {

  //val cexPath:String = "src/test/resources/minimal.cex"
  val cex:String = """#!cexversion
3.0

#!citelibrary
name#Fragment from Herodotus' Histories, Book VIII on Papyrus Oxyrhynchus 2099, dated to early 2nd century AD.
urn#urn:cite2:cex:fufolio.2018a:POxy2099
license#CC Share Alike.  For details, see more info.

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:#book/section/token#Herodotus#Histories#Tokenized Greek, Godley, ed.##true#grc
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:#book/section/token#Herodotus#Histories#Tokenized English, trans. Godley##true#eng

#!ctsdata
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0#Ἀθηναίων
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.1#δὲ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.2#νέας
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0#Themistocles
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1#however
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2#selected
"""

  def loadLibrary(cexString:String = cex, delimOne:String = "#", delimTwo:String = ","):CiteLibrary = {
    val library = CiteLibrary(cexString,"#",",")
    library
  }


  val lib:CiteLibrary = loadLibrary()

  "A CexWriter" should "serialize a citable node" in {
  	val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:msA:1.1")
  	val psg:String = "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	val cn:CitableNode = CitableNode(urn, psg)
  	val serialized:String = CexWriter.writeCitableNode(cn)
  	val expected:String = "urn:cts:greekLit:tlg0012.tlg001:msA:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	assert ( serialized == expected )
  }

  it should "serialize a citable node with an alternate delimiter" in {
    val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:msA:1.1")
    val psg:String = "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
    val cn:CitableNode = CitableNode(urn, psg)
    val serialized:String = CexWriter.writeCitableNode(cn, "@")
    val expected:String = "urn:cts:greekLit:tlg0012.tlg001:msA:1.1@Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
    assert ( serialized == expected )
  }



  it should "serialize a cts catalog entry" in {
  	val expected:String = "urn:cts:greekLit:tlg0016.tlg001.grc_tokens:#book/section/token#Herodotus#Histories#Tokenized Greek, Godley, ed.##true#grc"
  	val ce:edu.holycross.shot.ohco2.CatalogEntry = {
  		lib.textRepository.get.catalog.entriesForUrn(CtsUrn("urn:cts:greekLit:tlg0016.tlg001.grc_tokens:"))(0)
  	}
  	val cex:String = CexWriter.writeCtsCatalogEntry(ce)
  	assert ( cex == expected )
  }

  it should "serialize a cts catalog entry with alternate delimiters" in {
  	val expected:String = "urn:cts:greekLit:tlg0016.tlg001.grc_tokens:$book/section/token$Herodotus$Histories$Tokenized Greek, Godley, ed.$$true$grc"
  	val ce:edu.holycross.shot.ohco2.CatalogEntry = {
  		lib.textRepository.get.catalog.entriesForUrn(CtsUrn("urn:cts:greekLit:tlg0016.tlg001.grc_tokens:"))(0)
  	}
  	val cex:String = CexWriter.writeCtsCatalogEntry(ce,"$")
  	assert ( cex == expected )
  }

  it should "serialize a cts catalog block" in {

    val expected:String = """#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:#book/section/token#Herodotus#Histories#Tokenized Greek, Godley, ed.##true#grc
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:#book/section/token#Herodotus#Histories#Tokenized English, trans. Godley##true#eng"""

      val ce1:CatalogEntry = {
        lib.textRepository.get.catalog.entriesForUrn(CtsUrn("urn:cts:greekLit:tlg0016.tlg001.grc_tokens:"))(0)
      } 
      val ce2:CatalogEntry = {
        lib.textRepository.get.catalog.entriesForUrn(CtsUrn("urn:cts:greekLit:tlg0016.tlg001.eng_tokens:"))(0)
      } 
      val vce:Vector[CatalogEntry] = Vector(ce1,ce2)
      val catBlock:String = CexWriter.writeCtsCatalogBlock(vce)
      assert( catBlock == expected )
  }

  it should "serialize a cts catalog block with alternate delimiters" in {

    val expected:String = """#!ctscatalog
urn@citationScheme@groupName@workTitle@versionLabel@exemplarLabel@online@lang
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:@book/section/token@Herodotus@Histories@Tokenized Greek, Godley, ed.@@true@grc
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:@book/section/token@Herodotus@Histories@Tokenized English, trans. Godley@@true@eng"""

      val ce1:CatalogEntry = {
        lib.textRepository.get.catalog.entriesForUrn(CtsUrn("urn:cts:greekLit:tlg0016.tlg001.grc_tokens:"))(0)
      } 
      val ce2:CatalogEntry = {
        lib.textRepository.get.catalog.entriesForUrn(CtsUrn("urn:cts:greekLit:tlg0016.tlg001.eng_tokens:"))(0)
      } 
      val vce:Vector[CatalogEntry] = Vector(ce1,ce2)
      val catBlock:String = CexWriter.writeCtsCatalogBlock(vce,"@")
      assert( catBlock == expected )
  }

  it should "serialize a cts data block" in {
    val vcn:Vector[CitableNode] = lib.textRepository.get.corpus.nodes
    val dataBlock:String = CexWriter.writeCtsDataBlock(vcn)
    val expected:String = """#!ctsdata
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0#Ἀθηναίων
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.1#δὲ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.2#νέας
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0#Themistocles
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1#however
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2#selected"""
    assert ( dataBlock == expected )
  }

  it should "serialize a cts data block with alternate delimiters" in {
    val vcn:Vector[CitableNode] = lib.textRepository.get.corpus.nodes
    val dataBlock:String = CexWriter.writeCtsDataBlock(vcn, "@")
    val expected:String = """#!ctsdata
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0@Ἀθηναίων
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.1@δὲ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.2@νέας
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0@Themistocles
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1@however
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2@selected"""
    assert ( dataBlock == expected )
  }

  it should "be able to write a TextRepository to a valid CEX file" in {
    val newCex:String = CexWriter.writeTextRepository( tr = lib.textRepository.get, standalone = true)
    try {
      val newLib:CiteLibrary = loadLibrary(newCex)
      assert ( newLib.textRepository != None )
    } catch {
      case e:Exception => {
        println(s"\n${e}\n")
        assert ( false )
      }
    }
  }

  it should "be able to write a TextRepository to a valid CEX file, using an alternate delimiter" in {
    val newCex:String = CexWriter.writeTextRepository( tr = lib.textRepository.get, standalone = true, delimiter="@")
    try {
      val newLib:CiteLibrary = loadLibrary(newCex)
      assert ( newLib.textRepository != None )
    } catch {
      case e:Exception => {
        println(s"\n${e}\n")
        assert ( false )
      }
    }
  }

}

