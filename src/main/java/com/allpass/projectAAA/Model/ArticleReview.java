package com.allpass.projectAAA.Model;

import javax.persistence.*;

@Entity
public class ArticleReview {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ARTICLE_REVIEW_ID")
    private Long id;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="article_id", nullable=false)
    private Article article;
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name="member_id", nullable=false)
    private Member member;
    private String reviewTime;
    private String reviewText;
    private String articleReviewAddress;
    private Boolean acceptTask=false;
    private Boolean reviewComplete=false;


    public ArticleReview(){ }

    public void setId(Long id) { this.id = id; }

    public Long getId() {
        return id;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setArticleReviewAddress(String articleReviewAddress) { this.articleReviewAddress = articleReviewAddress; }

    public String getArticleReviewAddress() { return articleReviewAddress; }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setReviewComplete(boolean reviewComplete) { this.reviewComplete = reviewComplete; }

    public Boolean getReviewComplete() { return reviewComplete; }

    public void setAcceptTask(Boolean acceptTask) { this.acceptTask = acceptTask; }

    public Boolean getAcceptTask() { return acceptTask; }
}
