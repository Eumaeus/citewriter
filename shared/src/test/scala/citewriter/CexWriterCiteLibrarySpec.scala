package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class CexWriterCiteLibrarySpec extends FlatSpec {

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

#!relations
// Hdt.
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0-8.22.5
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.3-8.22.10
urn:cite2:fufolio:hdtAlign.blackwell:1#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.6-8.22.7
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2
urn:cite2:fufolio:hdtAlign.blackwell:2#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.12
// Iliad greek - pope
urn:cite2:fufolio:iliadAlign.blackwell:3#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.1-1.2
urn:cite2:fufolio:iliadAlign.blackwell:3#urn:cite2:cite:verbs.v1:aligns#urn:cts:fufolio:pope.iliad.fu2019:1.1.1-1.1.2
urn:cite2:fufolio:iliadAlign.blackwell:4#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.3-1.4
urn:cite2:fufolio:iliadAlign.blackwell:4#urn:cite2:cite:verbs.v1:aligns#urn:cts:fufolio:pope.iliad.fu2019:1.1.3-1.1.4
urn:cite2:fufolio:iliadAlign.blackwell:5#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.4-1.5
urn:cite2:fufolio:iliadAlign.blackwell:5#urn:cite2:cite:verbs.v1:aligns#urn:cts:fufolio:pope.iliad.fu2019:1.1.5-1.1.6
urn:cite2:fufolio:iliadAlign.blackwell:5#urn:cite2:cite:verbs.v1:aligns#urn:cts:fufolio:pope.iliad.fu2019:1.1.8
urn:cite2:fufolio:iliadAlign.blackwell:6#urn:cite2:cite:verbs.v1:aligns#urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.6-1.10
urn:cite2:fufolio:iliadAlign.blackwell:6#urn:cite2:cite:verbs.v1:aligns#urn:cts:fufolio:pope.iliad.fu2019:1.1.7
urn:cite2:fufolio:iliadAlign.blackwell:6#urn:cite2:cite:verbs.v1:aligns#urn:cts:fufolio:pope.iliad.fu2019:1.2.1-1.2.4

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

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:#book/section/token#Herodotus#Histories#Tokenized Greek, Godley, ed.##true#grc
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:#book/section/token#Herodotus#Histories#Tokenized English, trans. Godley##true#eng
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:#book/line#Homeric Epic#Iliad#Greek. Allen, ed. Perseus Digital Library. Creative Commons Attribution 3.0 License##true#grc
urn:cts:fufolio:pope.iliad.fu2019:#book, stanza, line#Alexander Pope#Iliad#Furman Ed. 2019##true#eng

#!ctsdata
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.0#Ἀθηναίων
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.1#δὲ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.2#νέας
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.3#τὰς
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.4#ἄριστα
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.5#πλεούσας
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.6#ἐπιλεξάμενος
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.7#Θεμιστοκλέης
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.8#ἐπορεύετο
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.9#περὶ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.10#τὰ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.11#πότιμα
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.12#ὕδατα
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.14#ἐντάμνων
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.15#ἐν
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.16#τοῖσι
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.17#λίθοισι
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.18#γράμματα
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.20#τὰ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.21#Ἴωνες
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.22#ἐπελθόντες
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.23#τῇ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.24#ὑστεραίῃ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.25#ἡμέρῃ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.26#ἐπὶ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.27#τὸ
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.28#Ἀρτεμίσιον
urn:cts:greekLit:tlg0016.tlg001.grc_tokens:8.22.29#ἐπελέξαντο

#!ctsdata
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.0#Themistocles
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.1#however
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.2#selected
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.3#those
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.4#ships
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.5#of
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.6#the
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.7#Athenians
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.8#which
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.9#sailed
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.10#best
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.12#and
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.13#went
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.14#round
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.15#to
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.16#the
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.17#springs
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.18#of
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.19#drinking-water
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.21#cutting
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.22#inscriptions
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.23#on
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.24#the
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.25#stones
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.26#there
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.28#which
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.29#the
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.30#Ionians
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.31#read
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.32#when
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.33#they
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.34#came
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.35#to
urn:cts:greekLit:tlg0016.tlg001.eng_tokens:8.22.36#Artemision

#!ctsdata
// Allen Iliad
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.2#οὐλομένην, ἣ μυρί᾽ Ἀχαιοῖς ἄλγε᾽ ἔθηκε,
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.3#πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.4#ἡρώων, αὐτοὺς δὲ ἑλώρια τεῦχε κύνεσσιν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.5#οἰωνοῖσί τε πᾶσι, Διὸς δ᾽ ἐτελείετο βουλή,
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.6#ἐξ οὗ δὴ τὰ πρῶτα διαστήτην ἐρίσαντε
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.7#Ἀτρεΐδης τε ἄναξ ἀνδρῶν καὶ δῖος Ἀχιλλεύς.
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.8#Τίς τάρ σφωε θεῶν ἔριδι ξυνέηκε μάχεσθαι;
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.9#Λητοῦς καὶ Διὸς υἱός· ὃ γὰρ βασιλῆϊ χολωθεὶς
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.10#νοῦσον ἀνὰ στρατὸν ὄρσε κακήν, ὀλέκοντο δὲ λαοί,
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.11#οὕνεκα τὸν Χρύσην ἠτίμασεν ἀρητῆρα
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.12#Ἀτρεΐδης· ὃ γὰρ ἦλθε θοὰς ἐπὶ νῆας Ἀχαιῶν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.13#λυσόμενός τε θύγατρα φέρων τ᾽ ἀπερείσι᾽ ἄποινα,
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.14#στέμματ᾽ ἔχων ἐν χερσὶν ἑκηβόλου Ἀπόλλωνος
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.15#χρυσέῳ ἀνὰ σκήπτρῳ, καὶ λίσσετο πάντας Ἀχαιούς,
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.16#Ἀτρεΐδα δὲ μάλιστα δύω, κοσμήτορε λαῶν·

#!ctsdata
urn:cts:fufolio:pope.iliad.fu2019:1.1.1#Achilles' wrath, to Greece the direful spring
urn:cts:fufolio:pope.iliad.fu2019:1.1.2#Of woes unnumber'd, heavenly goddess, sing!
urn:cts:fufolio:pope.iliad.fu2019:1.1.3#That wrath which hurl'd to Pluto's gloomy reign
urn:cts:fufolio:pope.iliad.fu2019:1.1.4#The souls of mighty chiefs untimely slain;
urn:cts:fufolio:pope.iliad.fu2019:1.1.5#Whose limbs unburied on the naked shore,
urn:cts:fufolio:pope.iliad.fu2019:1.1.6#Devouring dogs and hungry vultures tore.
urn:cts:fufolio:pope.iliad.fu2019:1.1.7#Since great Achilles and Atrides strove,
urn:cts:fufolio:pope.iliad.fu2019:1.1.8#Such was the sovereign doom, and such the will of Jove!
urn:cts:fufolio:pope.iliad.fu2019:1.2.1#Declare, O Muse! in what ill-fated hour
urn:cts:fufolio:pope.iliad.fu2019:1.2.2#Sprung the fierce strife, from what offended power
urn:cts:fufolio:pope.iliad.fu2019:1.2.3#Latona's son a dire contagion spread,
urn:cts:fufolio:pope.iliad.fu2019:1.2.4#And heap'd the camp with mountains of the dead;
urn:cts:fufolio:pope.iliad.fu2019:1.2.5#The king of men his reverent priest defied,
urn:cts:fufolio:pope.iliad.fu2019:1.2.6#And for the king's offence the people died.
urn:cts:fufolio:pope.iliad.fu2019:1.3.1#For Chryses sought with costly gifts to gain
urn:cts:fufolio:pope.iliad.fu2019:1.3.2#His captive daughter from the victor's chain.
urn:cts:fufolio:pope.iliad.fu2019:1.3.3#Suppliant the venerable father stands;
urn:cts:fufolio:pope.iliad.fu2019:1.3.4#Apollo's awful ensigns grace his hands
urn:cts:fufolio:pope.iliad.fu2019:1.3.5#By these he begs; and lowly bending down,
urn:cts:fufolio:pope.iliad.fu2019:1.3.6#Extends the sceptre and the laurel crown
urn:cts:fufolio:pope.iliad.fu2019:1.3.7#He sued to all, but chief implored for grace
urn:cts:fufolio:pope.iliad.fu2019:1.3.8#The brother-kings, of Atreus' royal race
"""

  def loadLibrary(cexString:String = cex, delimOne:String = "#", delimTwo:String = ","):CiteLibrary = {
    val library = CiteLibrary(cexString)
    library
  }


  val lib:CiteLibrary = loadLibrary()

  "A CexWriter" should "serialize a complete CiteLibrary" in {

    val lib = loadLibrary()

    val tr = lib.textRepository.get
    val cr = lib.collectionRepository.get
    val rs = lib.relationSet.get
    val dms = lib.dataModels.get
    val delim1:String = "#"
    val delim2:String = ","

    val libCex:String = CexWriter.writeCiteLibrary(
        Some(tr),
        Some(cr),
        Some(rs),
        Some(dms),
        true,
        delim1,
        delim2
    )

    val newLib = loadLibrary(libCex,delim1,delim2)

    assert( newLib.textRepository != None )
    assert( newLib.textRepository.get == tr )
    assert( newLib.collectionRepository != None )
    assert( newLib.collectionRepository.get.collectionsMap == cr.collectionsMap )
    assert( newLib.relationSet != None )
    assert( newLib.relationSet.get == rs )
    assert( newLib.dataModels != None )
    assert( newLib.dataModels.get.size == dms.size )

  }


}
