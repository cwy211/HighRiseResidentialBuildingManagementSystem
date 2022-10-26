package com.fyp.highriseresbuildms.utility;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class SentimentPipeline {
    private static Properties properties;
    private static String propertiesName="tokenize, ssplit, pos, parse, sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;

    private SentimentPipeline(){

    }

    static{
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getSentimentPipeline(){
        if(stanfordCoreNLP == null){
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}
