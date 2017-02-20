# Install CoreNLP
**CoreNLP version 3.7.0**

Using Ubuntu 16.04. See References at the bottom for links to the sources.

1. Install Oracle java 8

        sudo add-apt-repository ppa:webupd8team/java
        sudo apt-get update
        sudo apt-get install oracle-java8-installer
        # edit /etc/environment, add
        # JAVA_HOME="/usr/lib/jvm/java-8-oracle"
        source /etc/environment
    
2. Download CoreNLP from http://stanfordnlp.github.io/CoreNLP/download.html

        unzip stanford-corenlp-full-2016-10-31.zip
        # add all jars in the unzipped directory to project path


# Create (Java) CoreNLP Project

1. [Download Eclipse](https://eclipse.org/downloads/)
2. Install Eclipse

        cd eclipse-installer/
        ./eclipse-inst
        # before running the install update the installer using menu in top-right corner
3. Start Eclipse

        File -> New -> Java Project
        Enter "Project name" (e.g. java_eclipse_project)
        Uncheck "Use default location", specify location in your cloned git repository, e.g. howto/java
        Click Next
        Click Tab Libraries -> Add external JARs: add libraries, e.g. all CoreNLP jars
        Click Finish


# Create Example

1. Use a dropdown interface to create new class, check "public static void main(String[] args)"
2. Add example code

        import edu.stanford.nlp.pipeline.*;
        import java.util.*;

        public class BasicPipelineExample {

            public static void main(String[] args) {

                // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
                Properties props = new Properties();
                props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
                StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

                // read some text in the text variable
                String text = "...";
        
                // create an empty Annotation just with the given text
                Annotation document = new Annotation(text);
        
                // run all Annotators on this text
                pipeline.annotate(document);
                
                System.out.println("all done.");
            }

       }
3. Save CTRL+s (Eclipse compiles it at that), and run.
4. See CoreNLP web site and this repo for more examples.


# References

0. [CoreNLP API, examples](http://stanfordnlp.github.io/CoreNLP/api.html)
1. [How to Install Java](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04)
2. [Get started with CoreNLP](http://eirew.blogspot.com/2014/11/analyzing-text-using-stanford-corenlp.html)
3. [How to install Eclipse](http://askubuntu.com/questions/695382/how-to-install-eclipse-using-its-installer)
4. [HelloWorld in Eclipse](https://www.cis.upenn.edu/~matuszek/cit591-2004/Pages/starting-eclipse.html)
