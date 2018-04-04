package uk.sample.search.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Search parameters class
 * 
 */
public class Search {
    
    /** The article list in which we search */
    private List<Article> articleList;
    
    /** The keywords what we are looking for*/
    private List<String> keywords;

    public Search() {
        this.articleList = new ArrayList<>();
        this.keywords = new ArrayList<>();
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword.toLowerCase());
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

}
