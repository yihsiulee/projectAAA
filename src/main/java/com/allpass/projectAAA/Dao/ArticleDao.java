package com.allpass.projectAAA.Dao;

import com.allpass.projectAAA.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article,Long> {

}
