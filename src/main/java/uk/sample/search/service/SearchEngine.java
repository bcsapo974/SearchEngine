package uk.sample.search.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import uk.sample.search.model.Article;
import uk.sample.search.model.Search;

/**
 * SearchEnine contains the business logic for the search.
 *
 */
public class SearchEngine {

    /**
     * Loading articles from a file.
     *
     * @param inputPath the file input path
     * @return article list
     */
    public List<Article> loadData(String inputPath) {
        List<Article> articleList = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
                articleList = br.lines().filter(x -> !x.trim().isEmpty()).map(mapToItem).collect(Collectors.toList());
            }
        } catch (IOException e) {
            System.out.println("Problem with the file reading.");
        }
        return articleList;
    }

    /**
     * Search those articles, which title and/or body contains all of the given keywords.
     * It is case-insensitive.
     *
     * @param search search data (article list and keywords)
     * @return the searching results
     */
    public List<Article> getResults(Search search) {
        List<Article> filtered = new ArrayList<>();
        if (search.getArticleList() != null && !search.getArticleList().isEmpty()) {
            search.getArticleList().parallelStream()
                    .filter(article -> search.getKeywords().stream().parallel().allMatch(article.getTitle().toLowerCase()::contains) || search.getKeywords().stream().parallel().allMatch(article.getBody().toLowerCase()::contains))
                    .forEach(filtered::add);
        }
        return filtered;
    }

    private Function<String, Article> mapToItem = (line) -> {
        String[] p = line.split("\",\"");// a CSV has comma separated lines
        Article article = new Article();
        if (p.length > 0) {
            article.setId(p[0].replaceAll("^\"|\"$", ""));
        }
        if (p.length > 1) {
            article.setTitle(p[1].replaceAll("^\"|\"$", ""));
        }
        if (p.length > 2) {
            article.setBody(p[2].replaceAll("^\"|\"$", ""));
        }
        return article;
    };
}
