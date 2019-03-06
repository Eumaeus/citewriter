package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import scala.io.Source
import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class CiteWriterSpec extends FlatSpec {

//  val cexPath:String = "src/test/resources/hmt-2018g.cex"
  val cexPath:String = "src/test/resources/minimal.cex"

  def loadLibrary(fp:String = cexPath):CiteLibrary = {
  	val library = CiteLibrary(Source.fromFile(fp).getLines.mkString("\n"),"#",",")
  	library
  }

  lazy val lib:CiteLibrary = loadLibrary()

  "A CiteWriter" should "write a urn" in {
  	val urn:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:msA:1.1")
  	val urnString:String = CexWriter.writeUrn(urn)
  	assert (  urnString == "urn:cts:greekLit:tlg0012.tlg001:msA:1.1" )
  }
}

