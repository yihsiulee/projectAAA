package com.allpass.projectAAA.Service;

import com.allpass.projectAAA.Dao.ArticleReviewDao;
import com.allpass.projectAAA.Model.ArticleReview;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleReviewService {

    @Resource
    ArticleReviewDao articleReviewDao;

    public void save(ArticleReview articleReview){articleReviewDao.save(articleReview);}



}
