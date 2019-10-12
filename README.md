# mediawikixml

mediawikixml is a Java library which provides easy access to MediaWiki dump xml files. 

Its based on [WikiXMLJ](https://github.com/delip/wikixmlj). In difference to WikiXMLJ the project has been updated and
refactored and is actively maintained.

## Usage

The library can used via Maven. Add a external repository to the pom.xml:

```
<repositories>
 <repository>
 	<id>github</id>
 	<url>https://maven.pkg.github.com/seeseekey/mediawikixml</url>
 </repository>
</repositories>
```

After this you can add the dependency:

```
<dependency>
    <groupId>net.seeseekey</groupId>
    <artifactId>mediawikixml</artifactId>
    <version>1.0.1</version>
</dependency>
```

Now you can parse your first dump file:

```
WikiXMLParser wikiXMLParser = WikiXMLParserFactory.getParser("dump-current.xml");

try {

    wikiXMLParser.setPageCallback(new PageCallbackHandler() {
        public void process(WikiPage page) {
            System.out.println(page.getId());
            System.out.println(page.getRevisionId());
            System.out.println(page.getTimestamp());
            System.out.println(page.getTitle());
            System.out.println(page.getWikiText());
        }
    });

    wikiXMLParser.parse();
} catch (Exception e) {
    e.printStackTrace();
}
```

## Features

* Easy access to important elements of a MediaWiki page
* Also provides interfaces for Wiki text parsing
* Memory efficient
* SAX interface for parsing
* Lazy loading of files for DOM
* Callback support with DOM
* Directly operate on compressed wikipedia dumps (gzip/bzip2/native xml supported)

Note: gzip streams are way faster than bzip2 for a slight trade off in space.

## Dependencies

The project uses some basic dependencies:

* Guava
* GSON
* ant-compress
* SLF4J
* JUnit (Version 5)

Guava is a java helper library which deliver some interesting functions like LoadingCaches and helper methods. GSON 
helps to serialise JSON into classes and vice verse. SLF4J is a facade for logging.

JUnit is used for unit testing.

# Plugins

Define Java 8 as project version. Surfire-Plugin for executing tests.

## Developing

Project can be compiled with:

> mvn clean compile

Package can be created with:

> mvn clean package

## Authors

* seeseekey - [seeseekey.net](https://seeseekey.net)

### Original contributors from WikiXMLJ

* [Alan Said](http://github.com/alansaid)
* Andy Hedges
* [Delip Rao](https://github.com/delip)
* Itamar Syn-Hershko (@synhershko)
* Jason Smith 
* [Victor Olivares](http://github.com/treedust)  

## License

mediawikixml is licensed under Apache license in version 2.
