package java_eclipse_project;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;
import edu.stanford.nlp.util.CoreMap;

public class ServerCoreNlpPipeline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, coref");

        StanfordCoreNLPClient pipeline = new StanfordCoreNLPClient(props, "http://localhost", 9000, 2);

        // read some text in the text variable
        String text = "After about three billion years of this sometimes zany, "
        			+ "frequently tedious fugue of carnality and carnage, "
        			+ "Godfrey Waterhouse IV was born, in Murdo, South Dakota, "
        			+ "to Blanche, the wife of a Congregational preacher "
        			+ "named Bunyan Waterhouse. Like every other creature "
        			+ "on the face of the earth, Godfrey was, by birthright, "
        			+ "a stupendous badass, albeit in the somewhat narrow "
        			+ "technical sense that he could trace his ancestry "
        			+ "back up a long line of slightly less highly evolved "
        			+ "stupendous badasses to that first self-replicating "
        			+ "gizmo-which, given the number and variety of its "
        			+ "descendants, might justifiably be described as "
        			+ "the most stupendous badass of all time.\n";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        System.out.println("annotating - done.");
        
        // these are all the sentences in this document
        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        
        System.out.println("==> Let's traverse sentences...");
        for(CoreMap sentence: sentences) {
        	// traversing the words in the current sentence
        	// a CoreLabel is a CoreMap with additional token-specific methods
        	//System.out.println("Let's traverse tokens in sentence: "+sentence);
        	System.out.println("---> Let's traverse words, print annotation for each...");
        	for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
        		// this is the text of the token
        		String word = token.get(TextAnnotation.class);
        		// this is the POS tag of the token
        		String pos = token.get(PartOfSpeechAnnotation.class);
        		// this is the NER label of the token
        		String ne = token.get(NamedEntityTagAnnotation.class);
        		//System.out.println("  word: "+word+", POS: "+pos+", NER label: "+ne);
        		System.out.print(word+"/"+pos+"/"+ne+" ");
        	}
        	System.out.print("\n");
        }
        
        System.out.println("all done.");
	}

}
