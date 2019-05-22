package com.allpass.projectAAA.Dao;

import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Model.ArticleReview;
import com.allpass.projectAAA.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleReviewDao extends JpaRepository<ArticleReview,Long> {

    List<ArticleReview> findByMember(Member member);

    ArticleReview findByArticle(Article article);
}
