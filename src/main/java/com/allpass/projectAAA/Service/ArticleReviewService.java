package com.allpass.projectAAA.Service;

import com.allpass.projectAAA.Dao.ArticleReviewDao;
import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Model.ArticleReview;
import com.allpass.projectAAA.Model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticleReviewService {

    @Resource
    ArticleReviewDao articleReviewDao;

    public void save(ArticleReview articleReview){articleReviewDao.save(articleReview);}

    public List<ArticleReview> getArticleReviewListByMember(Member member){
        List<ArticleReview>articleReviewList=articleReviewDao.findByMember(member);
        return articleReviewList;
    }
    public ArticleReview getArticleReviewById(Long articleReviewId){
        ArticleReview articleReview=articleReviewDao.getOne(articleReviewId);
        return  articleReview;
    }
    public Set<ArticleReview> getArticleReviewByAuthor(Member member){
        List<ArticleReview>AllArticleReview=articleReviewDao.findAll();
        Set<ArticleReview> articleReviewList =new HashSet<>();
        for(ArticleReview articleReview:AllArticleReview) {
            if(articleReview.getArticle().getAuthor()==member){
                articleReviewList.add(articleReview);
            }
        }
        return articleReviewList;
    }
    public ArticleReview getArticleReviewByArticle(Article article){
        ArticleReview articleReview=articleReviewDao.findByArticle(article);
        return articleReview;

    }



}
