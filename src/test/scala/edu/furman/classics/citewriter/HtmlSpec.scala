package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class HtmlWriterSpec extends FlatSpec {
  "A HtmlWriter" should "serialize a citable node" in {
  	val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  	val psg:String = "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	val cn:CitableNode = CitableNode(urn, psg)
  	val serialized:String = HtmlWriter.writeCitableNode(cn)
  	val expected:String = """<span class="hmt_ohco2_citableNodeContainer" id="urn:cts:greekLit:tlg0012.tlg001.msA:1.1"><span class="hmt_ohco1_passageComponent">1.1</span><span class="hmt_ohco2_citableNodeText">Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος</span><span class="hmt_ohco2_citableNodeUrn">urn:cts:greekLit:tlg0012.tlg001.msA:1.1</span></span>"""
  	println(s"\n\n${expected}\n\n")
  	assert ( serialized == expected )
  }

}

