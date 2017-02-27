package java_eclipse_project;

import java.util.*;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;
	
public class CoreNlpServerTest {
	public static void test(){
		// creates a StanfordCoreNLP object with POS tagging, lemmatization, NER, parsing, and coreference resolution
		Properties props = new Properties();
		
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, coref");
		StanfordCoreNLPClient pipeline = new StanfordCoreNLPClient(props, "http://localhost", 9000, 1);
		// read some text in the text variable
		String text = "How are you today? After about three billion years of this sometimes zany,";
		// read some text in the text variable
//        String text = "After about three billion years of this sometimes zany, ";
//        			+ "frequently tedious fugue of carnality and carnage, "
//        			+ "Godfrey Waterhouse IV was born, in Murdo, South Dakota, "
//        			+ "to Blanche, the wife of a Congregational preacher "
//        			+ "named Bunyan Waterhouse.";
//        			+ "Like every other creature "
//        			+ "on the face of the earth, Godfrey was, by birthright, "
//        			+ "a stupendous badass, albeit in the somewhat narrow "
//        			+ "technical sense that he could trace his ancestry "
//        			+ "back up a long line of slightly less highly evolved "
//        			+ "stupendous badasses to that first self-replicating "
//        			+ "gizmo-which, given the number and variety of its "
//        			+ "descendants, might justifiably be described as "
//        			+ "the most stupendous badass of all time.\n";
		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);
		// run all Annotators on this text
		pipeline.annotate(document);
	}
	
	public static void main(String[] args){
		test();
	}
}
	