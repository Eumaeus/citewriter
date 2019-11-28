# `citewriter`: release notes

**1.2.0**: Writing discrete passage-components and passage-leaf-components as data-attributes on citable nodes.

**1.1.0**: Writing URNs as data-attributes on appropriate HTML elements.

**1.0.2**: Complete reworking on serializing a Corpus to HTML. Better display; more flexibility in display.

**1.0.1**: Minor updates.

**1.0.0**: Reorganized Traits and Objects correctly. 

**1.0.0**: CITE Writer Trait, CEX Writer object, and HTML Writer objects now feature-complete.

**0.6.0**: Serialize CiteObjects to HTML.

**0.5.0**: Serialize to HTML: all OHCO2 (following the CiteWriter trait), and CITE property definitions and values.

**0.4.0**: Serialize a whole CITE Library to CEX. Only tested thoroughly with default delimiters ("#",",").

**0.3.0**: Serializing to CEX all basic CITE primitives: OHCO2 Texts, CITE Objects, Relations, and Data Models. Only tested thoroughly with default delimiters ("#",","). 

**0.2.0**: Project writes CEX for OHCO2 text repositories and CiteCollection Repositories. HTMLWriter not fully implemented for Cite objects.

**0.1.0**: Project configured with updated Crossed-Compile stuff in SBT; compiles, can run tests in JVM and JS for Scala 1.11 and 1.12. Initial sketch-out of trait `CiteWriter` and implementing object `CexWriter`.

