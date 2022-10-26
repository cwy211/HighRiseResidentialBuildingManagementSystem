package com.fyp.highriseresbuildms.utility;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class LemmaPipeline {
    private static Properties properties;
    private static String propertiesName="tokenize, ssplit, pos, lemma";
    private static StanfordCoreNLP stanfordCoreNLP;

    private LemmaPipeline(){

    }

    static{
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getLemmaPipeline(){
        if(stanfordCoreNLP == null){
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}
