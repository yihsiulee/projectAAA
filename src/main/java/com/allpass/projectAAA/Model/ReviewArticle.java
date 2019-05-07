package com.allpass.projectAAA.Model;

import javax.persistence.*;

@Entity
public class ReviewArticle {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "REVIEW_ARTICLE_ID")
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="article_id", nullable=false)
    private Article article;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="member_id", nullable=false)
    private Member member;
    private String reviewTime;
    private String reviewText;
    private Integer articleStatus;
    private Long reviewAddress;


    public ReviewArticle(){ }

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

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewAddress(Long reviewAddress) {
        this.reviewAddress = reviewAddress;
    }

    public Long getReviewAddress() {
        return reviewAddress;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
