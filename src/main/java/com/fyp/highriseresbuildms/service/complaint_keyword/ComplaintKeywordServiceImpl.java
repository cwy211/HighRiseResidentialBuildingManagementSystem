package com.fyp.highriseresbuildms.service.complaint_keyword;

import com.fyp.highriseresbuildms.dao.complaint_category.ComplaintCategoryDao;
import com.fyp.highriseresbuildms.dao.complaint_keyword.ComplaintKeywordDao;
import com.fyp.highriseresbuildms.dto.ComplaintKeywordDto;
import com.fyp.highriseresbuildms.entity.ComplaintKeyword;
import com.fyp.highriseresbuildms.utility.LemmaPipeline;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComplaintKeywordServiceImpl implements ComplaintKeywordService{
    @Autowired
    private ComplaintKeywordDao complaintKeywordDao;

    @Autowired
    private ComplaintCategoryDao complaintCategoryDao;

    private static final String liftSystem="Lift System";
    private static final String powerSupply="Power Supply";
    private static final String cleaningServices="Cleaning Services";
    private static final String fireProtectionSystem="Fire Protection System";
    private static final String waterSupply="Water Supply";
    private static final String securitySystem="Security System";
    private static final String swimmingPool="Swimming Pool";
    private static final String landscapingGardening="Landscaping and Gardening";
    private static final String painting="Painting";
    private static final String sportRecreational="Sport and Recreational Facilities";
    private static final String social="Social Facilities";

    @Override
    public void addComplaintKeyword(ComplaintKeywordDto complaintKeywordDto) {
        ComplaintKeyword complaintKeywordObj = new ComplaintKeyword();
        String keyword=lemmaAnalysis(complaintKeywordDto.getKeyword());
        complaintKeywordObj.setKeyword(keyword);
        complaintKeywordObj.setComplaintCategory(complaintCategoryDao.findById(complaintKeywordDto.getCategory()).get());
        complaintKeywordDao.save(complaintKeywordObj);
    }

    @Override
    public List<String> getAllKeyword(String category) {
        List<ComplaintKeyword> complaintKeywordList=complaintKeywordDao.findAll();
        List<ComplaintKeyword> filteredComplaintKeywordList=complaintKeywordList.stream().filter(elem->elem.getComplaintCategory().getComplaintCategoryName().equals(category)).collect(Collectors.toList());
        return extractKeyword(filteredComplaintKeywordList);
    }

    @Override
    public List<ComplaintKeyword> getAllKeyword() {
        return complaintKeywordDao.findAll();
    }

    @Override
    public List<String> extractKeyword(List<ComplaintKeyword> complaintKeywordList) {
        List<String> keywordList= new ArrayList<>();
        complaintKeywordList.stream().forEach(elem->keywordList.add(elem.getKeyword()));
        return keywordList;
    }

    @Override
    public void deleteKeyword(String keyword) {
        ComplaintKeyword complaintKeyword = complaintKeywordDao.findById(keyword).get();
        complaintKeywordDao.delete(complaintKeyword);
    }

    @Override
    public List<ComplaintKeyword> getAllKeywordAsc() {
        return complaintKeywordDao.findAllByOrderByComplaintCategory_RankAsc();
    }

    @Override
    public boolean checkDuplicatedKeyword(ComplaintKeywordDto complaintKeywordDto) {
        String keyword=lemmaAnalysis(complaintKeywordDto.getKeyword());
        if(complaintKeywordDao.findById(keyword).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public String lemmaAnalysis(String keyword) {
        StanfordCoreNLP stanfordCoreNLP = LemmaPipeline.getLemmaPipeline();
        CoreDocument coreDocument = new CoreDocument(keyword);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        String lemmaTextString="";
        for(CoreLabel coreLabel : coreLabelList){
            String lemmaText = coreLabel.lemma();
            lemmaTextString=lemmaTextString+" "+lemmaText;
        }
        return lemmaTextString;
    }

    @Override
    public void initComplaintKeywords() {
        initHelper("lift",liftSystem);
        initHelper("electric",powerSupply);
        initHelper("electricity",powerSupply);
        initHelper("power",powerSupply);
        initHelper("dirty",cleaningServices);
        initHelper("messy",cleaningServices);
        initHelper("smelly",cleaningServices);
        initHelper("fire",fireProtectionSystem);
        initHelper("water",waterSupply);
        initHelper("security",securitySystem);
        initHelper("cctv",securitySystem);
        initHelper("guard",securitySystem);
        initHelper("swim",swimmingPool);
        initHelper("pool",swimmingPool);
        initHelper("garden",landscapingGardening);
        initHelper("landscape",landscapingGardening);
        initHelper("grass",landscapingGardening);
        initHelper("bush",landscapingGardening);
        initHelper("tree",landscapingGardening);
        initHelper("paint",painting);
        initHelper("painting",painting);
        initHelper("sport",sportRecreational);
        initHelper("basketball",sportRecreational);
        initHelper("badminton",sportRecreational);
        initHelper("gym",sportRecreational);
        initHelper("game",social);
        initHelper("conference",social);
        initHelper("kindergarten",social);

    }

    @Override
    public void initHelper(String keyword, String category) {
        ComplaintKeyword complaintKeyword = new ComplaintKeyword();
        complaintKeyword.setKeyword(keyword);
        complaintKeyword.setComplaintCategory(complaintCategoryDao.findById(category).get());
        complaintKeywordDao.save(complaintKeyword);
    }


}
