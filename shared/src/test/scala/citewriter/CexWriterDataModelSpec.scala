package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class CexWriterDataModelSpec extends FlatSpec {

  //val cexPath:String = "src/test/resources/minimal.cex"
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


"""

  def loadLibrary(cexString:String = cex, delimOne:String = "#", delimTwo:String = ","):CiteLibrary = {
    val library = CiteLibrary(cexString,"#",",")
    library
  }


  val lib:CiteLibrary = loadLibrary()

  "A CexWriter" should "serialize a DataModel" in {

    val expected:String = "urn:cite2:fufolio:hdtAlign.blackwell:#urn:cite2:cite:datamodels.v1:alignment#Text Alignment Model#The CITE model for text alignment. See documentation at <https://eumaeus.github.io/citealign/>."

    val collection:Cite2Urn = Cite2Urn("urn:cite2:fufolio:hdtAlign.blackwell:")
    val model:Cite2Urn = Cite2Urn("urn:cite2:cite:datamodels.v1:alignment")
    val label:String = "Text Alignment Model"
    val description:String = "The CITE model for text alignment. See documentation at <https://eumaeus.github.io/citealign/>."
    val dm:DataModel = DataModel(collection, model, label, description)
    val serialized:String = CexWriter.writeDataModel(dm)

  	assert ( serialized == expected )
  }


  it should "serialize a DataModel Block" in {
      val dmv:Vector[DataModel] = lib.dataModels.get     
      assert(dmv.size == 2)
      val dmvCex:String = CexWriter.writeDataModelBlock(dmv, standalone = true)
      val dmvLib:CiteLibrary = CiteLibrary(dmvCex,"#",",")
      assert( dmvLib.dataModels != None)
      assert( dmvLib.dataModels.get.size == lib.dataModels.get.size )

  }
  


}

