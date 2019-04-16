package com.allpass.projectAAA.Do;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long articleId;
    private String articleName;
    private String postTime;
    private String deadline;
    private String checkedTime;
    private Integer articleStudy;
    private File uploadFile;
    private Integer textNumber;
    private Integer formulaNumber;
    private String reviewText;


//    public Article(
//            String articleName,
//            String postTime,
//            String deadline,
//            String checkedTime,
//            Integer articleStudy,
//            File uploadFile,
//            Integer textNumber,
//            Integer formulaNumber,
//            String reviewText
//    ){
//        this.articleName=articleName;
//        this.articleStudy=articleStudy;
//        this.checkedTime=checkedTime;
//        this.deadline=deadline;
//        this.formulaNumber=formulaNumber;
//        this.postTime=postTime;
//        this.reviewText=reviewText;
//        this.textNumber=textNumber;
//        this.uploadFile=uploadFile;
//    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleStudy(Integer articleStudy) {
        this.articleStudy = articleStudy;
    }

    public Integer getArticleStudy() {
        return articleStudy;
    }

    public void setTextNumber(Integer textNumber) {
        this.textNumber = textNumber;
    }

    public Integer getTextNumber() {
        return textNumber;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public void setArticleName(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setCheckedTime(String checkedTime) {
        this.checkedTime = checkedTime;
    }

    public String getCheckedTime() {
        return checkedTime;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setFormulaNumber(Integer formulaNumber) {
        this.formulaNumber = formulaNumber;
    }

    public Integer getFormulaNumber() {
        return formulaNumber;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewText() {
        return reviewText;
    }
}
