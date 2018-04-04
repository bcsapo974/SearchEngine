package uk.sample.search.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import uk.sample.search.model.Article;
import uk.sample.search.model.Search;

/**
 * Test for SearchEngine class.
 * 
 */

@RunWith(JUnit4ClassRunner.class)
public class SearchEngineTest {
    
    public SearchEngineTest() {
    }
    
    /**
     * Test of loadData method, of class SearchEngine.
     */
    @Test
    public void testLoadData() {
        File file = new File(this.getClass().getResource("exercise_data.csv").getFile());
        SearchEngine searchEngine = new SearchEngine();
        List<Article> articleList = searchEngine.loadData(file.getAbsolutePath());
        assertTrue(articleList.size()==20);
    }
    
    

    /**
     * Test of getResults method, of class SearchEngine with data from a file.
     */
    @Test
    public void testSearch1() {
        File file = new File(this.getClass().getResource("exercise_data.csv").getFile());
        Search search = new Search();
        SearchEngine searchEngine = new SearchEngine();
        search.setArticleList(searchEngine.loadData(file.getAbsolutePath()));
        search.addKeyword("JavaScript");
        List<Article> result1 = searchEngine.getResults(search);
        assertTrue(result1.size() == 5);
        search.addKeyword("dark");
        List<Article> result2 = searchEngine.getResults(search);
        assertTrue(result2.size() == 1 && "16eec83012e9c0618224ec9b6cf25e414f049416".equals(result2.get(0).getId()));
        
    }
    
    /**
     * Test of getResults method, of class SearchEngine with manually created data.
     */
    @Test
    public void testSearch2() {
        Search search = new Search();
        search.addKeyword("sugar");
        search.addKeyword("eggs");
        search.setArticleList(generateTestData());
        SearchEngine searchEngine = new SearchEngine();
        List<Article> result1 = searchEngine.getResults(search);
        assertTrue(result1.size() == 2);
        
    }
    
    private List<Article> generateTestData() {
        List<Article> articles = new ArrayList<>();
        Article article1 = new Article();
        article1.setId("first");
        article1.setTitle("Sugar eggs");
        article1.setBody("This is a receipt.");
        articles.add(article1);
        Article article2 = new Article();
        article2.setId("second");
        article2.setTitle("Easter eggs");
        article2.setBody("This is a joke.");
        articles.add(article2);
        Article article3 = new Article();
        article3.setId("third");
        article3.setTitle("Test data");
        article3.setBody("This is a weird name: Sogar Eggs");
        articles.add(article3);
        Article article4= new Article();
        article4.setId("fourth");
        article4.setTitle("Just another test data");
        article4.setBody("Two sugar eggs are cool.");
        articles.add(article4);
        return articles;
    }
    
}
