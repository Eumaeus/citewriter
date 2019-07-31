package edu.furman.classics.citewriter
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.seqcomp._
import edu.holycross.shot.citerelation._

class HtmlCorpusSpec extends FlatSpec {

val cex:String = """#!cexversion
3.0

#!citelibrary
name#CITE Library generated by the Ducat application, Tue Mar 12 2019 19:38:17 GMT-0400 (EDT)
urn#urn:cite2:cex:ducatauto.20192:19_38_17_519
license#CC Share Alike.

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:#Plaintext#Homeric Epic#Iliad#Perseus Greek, following Allen##true#grc
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:#book/line/token#Homeric Epic#Iliad#Tokenized Greek. Allen, ed. Perseus Digital Library. Creative Commons Attribution 3.0 License##true#grc
urn:cts:fufolio:pope.iliad.fu2019_tokens:#book/stanza/line/tokens#Alexander Pope#Iliad#Tokenized Furman Ed. 2019##true#eng
urn:cts:fufolio:pope.iliad.fu2019:#book, stanza, line#Alexander Pope#Iliad#Furman Ed. 2019##true#eng

#!ctsdata
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.2#οὐλομένην· ἡ μυρί' Ἀχαιοῖς ἄλγε' ἔθηκεν·
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.3#πολλὰς δ' ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.4#ἡρώων· αὐτοὺς δὲ ἑλώρια τεῦχε κύνεσσιν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.5#οἰωνοῖσί τε πᾶσι· Διὸς δ' ἐτελείετο βουλή·
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:2.1#Ἄλλοι μέν ῥα θεοί τε καὶ ἀνέρες ἱπποκορυσταὶ
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:2.2#εὗδον παννύχιοι : Δία δ' οὐκ έχε , νήδυμος ὕπνος :
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:2.3#ἀλλ' ὅ γε μερμήριζε κατα φρένα : ὡς Ἀχιλῆα
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:2.4#τιμήσῃ : ὀλέσῃ δὲ πολέας ἐπὶ νηυσὶν Ἀχαιῶν :
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.0#All
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.1#night
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.2#the
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.3#chiefs
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.4#before
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.5#their
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.6#vessels
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.7#lay,
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.0#And
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.1#lost
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.2#in
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.3#sleep
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.4#the
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.5#labours
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.6#of
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.7#the
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.2.8#day:
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.0#All
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.1#but
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.2#the
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.3#king:
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.4#with
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.5#various
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.6#thoughts
urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.3.7#oppress'd,
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.1.1#ἄλλοι
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.1.2#μὲν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.1.3#παρὰ
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.1.4#νηυσὶν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.1.5#ἀριστῆες
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.1.6#Παναχαιῶν
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.2.1#εὗδον
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.2.2#παννύχιοι
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.2.3#μαλακῷ
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.2.4#δεδμημένοι
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.2.5#ὕπνῳ
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.2.6#·
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.3.1#ἀλλʼ
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.3.2#οὐκ
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.3.3#Ἀτρεΐδην
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.3.4#Ἀγαμέμνονα
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.3.5#ποιμένα
urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.3.6#λαῶν
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

 def loadLibrary(cexString:String = cex):CiteLibrary = {
 val library = CiteLibrary(cexString)
 library
 }

 val lib:CiteLibrary = loadLibrary()

 val tr:TextRepository = lib.textRepository.get

 def showMe(v:Any):Unit = {
  v match {
    case _:Iterable[Any] => println(s"""\n----\n${v.asInstanceOf[Iterable[Any]].mkString("\n")}\n----\n""")
    case _ => println(s"\n-----\n${v}\n----\n")
  }
}


 /* Sorting stuff */

 "A HtmlWriter" should "group a corpus by version" in {
    val u1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:1.1-1.3")
    val u2 = CtsUrn("urn:cts:fufolio:pope.iliad.fu2019_tokens:x.1.1.0-x.1.1.3")
    val u3 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:10.1.1-10.2.1")
    val c1 = tr.corpus ~~ u1
    val c2 = tr.corpus ~~ u2
    val c3 = tr.corpus ~~ u3
    val corp = Corpus(c1.nodes ++ c2.nodes ++ c3.nodes)
    val grouped = HtmlWriter.groupCorpusByText(corp)
    assert( grouped.size == 3)
 }

 it should "group a corpus by citation values at different depths" in {
  val u1 = CtsUrn("urn:cts:fufolio:pope.iliad.fu2019:")
  val corp = tr.corpus ~~ u1
  val grouped = HtmlWriter.groupCorpusByDepth(corp)
  //showMe(grouped)
 }


 /* OHCO2 Stuff */

 "A HtmlWriter" should "serialize a corpus" in {
 	val urn1:CtsUrn = CtsUrn("urn:cts:fufolio:pope.iliad.fu2019:")
  val corp = tr.corpus ~~ urn1
 	val serialized:String = HtmlWriter.writeCorpus(corp)
  showMe(serialized)
  assert (true)
 }

 it should "serialize a tokenized corpus" in {

  val urn1:CtsUrn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:")
  val corp = tr.corpus ~~ urn1
  val serialized:String = HtmlWriter.writeCorpus(corp, tokenized = true)
  println(s"showing for urn:cts:greekLit:tlg0012.tlg001.perseus_grc2_tokens:")
  showMe(serialized)
 }

 it should "optionally include bibliographic data" in {
  val u1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.perseus_grc2:")
  val u2 = CtsUrn("urn:cts:fufolio:pope.iliad.fu2019:")
  val c1 = tr.corpus ~~ u1
  val c2 = tr.corpus ~~u2
  val corp = c1 ++ c2
  val cat = tr.catalog
  val serialized:String = HtmlWriter.writeCorpus(corp, tokenized = false, Some(cat))
  showMe(serialized)
 }

 it should "handle a mix of tokenized and untokenized texts attractively" in {
  val cat = tr.catalog
  val serialized:String = HtmlWriter.writeCorpus(tr.corpus, tokenized = false, Some(cat))
  //showMe(serialized)
 }



}
