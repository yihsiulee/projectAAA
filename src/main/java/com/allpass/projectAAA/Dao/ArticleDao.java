package com.allpass.projectAAA.Dao;

import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Article;
import com.allpass.projectAAA.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article,Long> {

    List<Article> findByActivity(Activity activity);

    List<Article> findByAuthor(Member member);




}
