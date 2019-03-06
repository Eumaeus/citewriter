package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import scala.io.Source
import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class CexWriterSpec extends FlatSpec {

  val cexPath:String = "src/test/resources/minimal.cex"

  def loadLibrary(fp:String = cexPath):CiteLibrary = {
  	val library = CiteLibrary(Source.fromFile(fp).getLines.mkString("\n"),"#",",")
  	library
  }

  lazy val lib:CiteLibrary = loadLibrary()

  "A CexWriter" should "serialize a citable node" in {
  	val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:msA:1.1")
  	val psg:String = "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	val cn:CitableNode = CitableNode(urn, psg)
  	val serialized:String = CexWriter.writeCitableNode(cn)
  	val expected:String = "urn:cts:greekLit:tlg0012.tlg001:msA:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
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
}

