package com.allpass.projectAAA.Service;

import com.allpass.projectAAA.Dao.ArticleDao;
import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    ArticleDao articleDao;

    public void save(Article article) { articleDao.save(article); }

    public Article getArticleById(Long articleId){return articleDao.getOne(articleId);}

    public List<Article> getArticleByActivity(Activity activity){
        List<Article> articleList=articleDao.findByActivity(activity);
        return articleList;

    }
    public List<Article> getArticleByAuthor(Member member){
        List<Article> articleList=articleDao.findByAuthor(member);
        return articleList;
    }


}
