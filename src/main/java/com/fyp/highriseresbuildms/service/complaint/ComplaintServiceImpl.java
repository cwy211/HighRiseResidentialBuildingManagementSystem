package com.fyp.highriseresbuildms.service.complaint;

import com.fyp.highriseresbuildms.dao.complaint.ComplaintDao;
import com.fyp.highriseresbuildms.dao.complaint_category.ComplaintCategoryDao;
import com.fyp.highriseresbuildms.entity.Complaint;
import com.fyp.highriseresbuildms.entity.ComplaintKeyword;
import com.fyp.highriseresbuildms.service.complaint_keyword.ComplaintKeywordService;
import com.fyp.highriseresbuildms.service.user.UserService;
import com.fyp.highriseresbuildms.utility.LemmaPipeline;
import com.fyp.highriseresbuildms.utility.POSPipeline;
import com.fyp.highriseresbuildms.utility.SentimentPipeline;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService{

    @Autowired
    private UserService userService;

    @Autowired
    private ComplaintKeywordService complaintKeywordService;

    @Autowired
    private ComplaintDao complaintDao;

    @Autowired
    private ComplaintCategoryDao complaintCategoryDao;


    @Override //to perform sentiment analysis on complaint
    public Complaint sentimentAnalysis(Complaint complaint) {
        int sentimentInt;
        int sentimentValueSum=0;
        //get sentiment pipeline
        StanfordCoreNLP stanfordCoreNLP = SentimentPipeline.getSentimentPipeline();

        //inserting complaint title and description into core document
        CoreDocument coreDocument = new CoreDocument(complaint.getComplaintTitle()+". "+
                complaint.getComplaintDescription());

        //stanfordCoreNLP performs sentiment analysis on the core document
        stanfordCoreNLP.annotate(coreDocument);

        //creates list of core sentences with sentences from core document
        List<CoreSentence> sentences = coreDocument.sentences();

        //for loop to get sentiment value for each sentence and total sentiment value for whole complaint
        for(CoreSentence sentence : sentences){
            Tree tree = sentence.sentimentTree();
            sentimentInt = RNNCoreAnnotations.getPredictedClass(tree);
            sentimentValueSum=sentimentValueSum+sentimentInt;
        }

        //calculate average sentiment value of complaint
        double averageSentimentValue = (double) sentimentValueSum/sentences.size();
        BigDecimal bd = new BigDecimal(averageSentimentValue).setScale(2, RoundingMode.HALF_UP);

        //set the sentiment value of complaint with calculated average sentiment value
        complaint.setSentimentValue(bd.doubleValue());

        return complaint;
    }

    @Override
    public List<String> POSAnalysis(String lemmaTextString) {
        //get part-of-speech tagging pipeline
        StanfordCoreNLP stanfordCoreNLP = POSPipeline.getPOSPipeline();
        //insert lemmatized text string into a core document
        CoreDocument coreDocument = new CoreDocument(lemmaTextString);
        //perform part-of-speech tagging on core document
        stanfordCoreNLP.annotate(coreDocument);
        //store tokenized text from the core document into a list
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        //list to store important words from the complaints
        List<String> complaintKeyWords= new ArrayList<>();
        //loops the text that undergo part-of-speech tagging
        for(CoreLabel coreLabel : coreLabelList){
            //add words from the complaints into the important words list if the
            //word is a noun, adjective or a verb
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if(pos.equals("NN")||pos.equals("JJ")||pos.equals("VB")){
                complaintKeyWords.add(coreLabel.originalText());
            }

        }
        return complaintKeyWords;
    }

    @Override //steps for creating a complaint
    public void createComplaint(Complaint complaint) {
        //complaint undergoes sentiment analysis
        sentimentAnalysis(complaint);
        //complaint undergoes lemmatization to get lemmatized text string
        String lemmaTextString= lemmaAnalysis(complaint);
        //lemmatized complaint text undergoes part-of-speech tagging
        List<String> importantComplaintText = POSAnalysis(lemmaTextString);
        // complaint undergoes category analysis
        Complaint categorizedComplaint = categoryAnalysis(complaint,importantComplaintText);
        // set resident, status and creation date time of complaint
        categorizedComplaint.setComplaintUser(userService.getUser(userService.getUserName()));
        categorizedComplaint.setComplaintStatus("Pending");
        categorizedComplaint.setCreatedDateTime(LocalDateTime.now());
        //save complaint
        complaintDao.save(categorizedComplaint);
    }

    @Override
    public Complaint categoryAnalysis(Complaint complaint, List<String> importantComplaintText) {
        //get all keyword in with the keyword of category of higher priority first
        List<ComplaintKeyword> keywordList = complaintKeywordService.getAllKeywordAsc();
        //loops the keyword list
        for(ComplaintKeyword complaintKeyword:keywordList){
            //loops the text of the complaint
            for(String text:importantComplaintText){
                //if a match is found, the complaint category is set as the complaint category of the keyword
                //and the complaint is returned
                if(complaintKeyword.getKeyword().trim().equals(text)){
                    complaint.setComplaintCategory(complaintKeyword.getComplaintCategory());
                    return complaint;
                }
            }
        }
        //if no keyword matches, the complaint category is set as "Others"
        complaint.setComplaintCategory(complaintCategoryDao.findComplaintCategoryByComplaintCategoryName("Others"));
        return complaint;
    }

    @Override
    public String lemmaAnalysis(Complaint complaint) {
        //get the lemmatization pipeline
        StanfordCoreNLP stanfordCoreNLP = LemmaPipeline.getLemmaPipeline();
        //inserts the complaint description into the core document
        CoreDocument coreDocument = new CoreDocument(complaint.getComplaintDescription());
        //lemmatization is performed on the core document
        stanfordCoreNLP.annotate(coreDocument);
        //store the tokenized text from core document into a list
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        String lemmaTextString="";
        //for loop to concatenate all the lemmatized words into a string
        for(CoreLabel coreLabel : coreLabelList){
            String lemmaText = coreLabel.lemma();
            lemmaTextString=lemmaTextString+" "+lemmaText;
        }
        return lemmaTextString;
    }

    @Override
    public List<Complaint> getAllComplaint() {
        return complaintDao.findAll();
    }

    @Override
    public List<Complaint> getComplaintByUserName() {
        String currentUserName=userService.getUserName();
        return complaintDao.findComplaintsByComplaintUser_UserId(currentUserName);
    }

    @Override
    public Complaint getComplaintById(int id) {
        return complaintDao.findById(id).get();
    }

    @Override
    public void handleComplaint(Complaint complaint) {

        Complaint handledComplaint = complaintDao.findById(complaint.getId()).get();
        handledComplaint.setComplaintStatus("Reviewed");
        String currentUserName=userService.getUserName();
        handledComplaint.setComplaintResponse(complaint.getComplaintResponse());
        handledComplaint.setComplaintAdmin(userService.getUserByUserId(currentUserName));
        complaintDao.save(handledComplaint);
    }

    @Override
    public void processComplaintStatus(Complaint complaint) {
        Complaint processedComplaint = complaintDao.findById(complaint.getId()).get();
        processedComplaint.setComplaintStatus("Processing");
        complaintDao.save(processedComplaint);
    }

    @Override
    public List<Complaint> getAllComplaintSentimentAsc() {
        return complaintDao.findAllByOrderBySentimentValueAsc();
    }

    @Override
    public List<Complaint> getAllComplaintMaintenancePriorityAsc() {
        return complaintDao.findAllByOrderByComplaintCategory_RankAsc();
    }
}
