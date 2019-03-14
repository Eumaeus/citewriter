package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class HtmlWriterSpec extends FlatSpec {

  /* OHCO2 Stuff */

  "A HtmlWriter" should "serialize a citable node" in {
  	val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  	val psg:String = "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος"
  	val cn:CitableNode = CitableNode(urn, psg)
  	val serialized:String = HtmlWriter.writeCitableNode(cn)
  	val expected:String = """<span class="ohco2_citableNodeContainer" id="urn:cts:greekLit:tlg0012.tlg001.msA:1.1"><span class="ohco1_passageComponent">1.1</span><span class="ohco2_citableNodeText">Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος</span><span class="ohco2_ctsUrn">urn:cts:greekLit:tlg0012.tlg001.msA:1.1</span></span>"""
  	assert ( serialized == expected )
  }

  it should "write a CitePropertyDef" in {
    val expected:String = """<span class="citeobj_propertyDef"><span class="citeobj_propertyDef_urn">urn:cite2:hmt:msB.v1.rv:</span><span class="citeobj_propertyDef_label">Recto or Verso</span><span class="citeobj_propertyDef_type">String</span><span class="citeobj_propertyDef_vocab">recto, verso</span></span>"""
    val propDef = CitePropertyDef(Cite2Urn("urn:cite2:hmt:msB.v1.rv:"),"Recto or Verso",ControlledVocabType,Vector("recto", "verso"))
    val serialized:String = HtmlWriter.writeCitePropertyDef(propDef)
    assert( expected == serialized )
  }

  it should "write a CitePropertyValue" in {
    val expected:String = """<span class="citeobj_propertyValue"><span class="citeobj_propertyValue_urn">urn:cite2:hmt:msB.v1.rv:12r</span><span class="citeobj_propertyValue_value">recto</span></span>"""
    val propVal = CitePropertyValue(Cite2Urn("urn:cite2:hmt:msB.v1.rv:12r"),"recto")
    val serialized:String = HtmlWriter.writeCitePropertyValue(propVal)
    assert( expected == serialized )
  }

  it should "write a CiteObject" in pending
  
  it should "write a CiteCollectionDef" in pending

  it should "write a CiteTriple" in pending

  it should "write a CiteRelationSet" in pending


}

