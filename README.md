# `citewriter`

## What it is

`citewriter` is a cross-platform library for serializing data from the [CITE-Architecture](https:cite-architecture.org).

## Current version: 1.0.1

Status:  **active development**. [Release notes](releases.md)

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)


## Documentation

See <https://github.com/Eumaeus/citewriter>.


## Using, building, testing

`citewriter` can be built for both the JVM and ScalaJS using any version of Scala 2.11 or higher.  Binaries for both the JVM and ScalaJS are available from jcenter built with Scala 2.12.3.  To use the binaries in an sbt project, include `Resolver.jcenterRepo`in your list of resolvers

    resolvers += Resolver.jcenter

and  add this to your library dependencies:

    "edu.furman.classics" %% "citewriter" % VERSION

For maven, ivy or gradle equivalents, refer to <https://bintray.com/eumaeus/maven/citewriter>.


To build from source and test for a given version, use normal sbt commands (`compile`, `test` ...).

You can also test or run tasks against all versions, using `+` before the task name.  E.g.,  `sbt "+ test"` runs the `test` task against all versions.

`citewriter` is used by the CITE library manager `scm`.  The `scm` wiki at <https://github.com/cite-architecture/scm/wiki> includes examples of how to create an ohco2 `TextRepository` from local files in various formats.
