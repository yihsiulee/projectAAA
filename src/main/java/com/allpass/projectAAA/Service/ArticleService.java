package com.allpass.projectAAA.Service;

import com.allpass.projectAAA.Dao.ArticleDao;
import com.allpass.projectAAA.Model.Article;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleService {
    @Resource
    ArticleDao articleDao;

    public void save(Article article) { articleDao.save(article); }



}
