package com.allpass.projectAAA.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "articleId")
    private Long id;
    private String articleName;
    private String postTime;
    private String deadline;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="AUTHOR_MEMBER_ID_FK")
    private Member author;
    private Double articleValue;
//    private Integer articleStudy;
    private String uploadFile;
    private String fileName;
    private Integer textNumber;
    private Integer formulaNumber;
    private Integer articleReviewAssignNumber;
    private String articleAddress;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ACTIVITY_ID_FK")
    private Activity activity;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<ArticleReview> articleReviews;
    private String articleState="notAssign";
//    @OneToMany(mappedBy="article")
//    private Set<ArticleReview> articleReview;


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

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

//    public void setArticleStudy(Integer articleStudy) {
//        this.articleStudy = articleStudy;
//    }
//
//    public Integer getArticleStudy() {
//        return articleStudy;
//    }

    public void setTextNumber(Integer textNumber) {
        this.textNumber = textNumber;
    }

    public Integer getTextNumber() {
        return textNumber;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public Member getAuthor() {
        return author;
    }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileName() { return fileName; }


    public void setDeadline(String deadline) { this.deadline = deadline; }

    public String getDeadline() { return deadline; }

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

    public void setArticleReviewAssignNumber(Integer articleReviewAssignNumber) { this.articleReviewAssignNumber = articleReviewAssignNumber; }

    public Integer getArticleReviewAssignNumber() { return articleReviewAssignNumber; }

    public void setActivity(Activity activity) { this.activity = activity; }

    public Activity getActivity() { return activity; }

    public void setArticleReviews(Set<ArticleReview> articleReviews) { this.articleReviews = articleReviews; }

    public Set<ArticleReview> getArticleReviews() { return articleReviews; }

    public void setArticleState(String articleState) { this.articleState = articleState; }

    public String getArticleState() { return articleState; }

    public void setArticleValue(Double articleValue) { this.articleValue = articleValue; }

    public Double getArticleValue() { return articleValue; }
}
