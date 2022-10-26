package com.fyp.highriseresbuildms.utility;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class POSPipeline {
    private static Properties properties;
    private static String propertiesName="tokenize, ssplit, pos";
    private static StanfordCoreNLP stanfordCoreNLP;

    private POSPipeline(){

    }

    static{
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getPOSPipeline(){
        if(stanfordCoreNLP == null){
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}
