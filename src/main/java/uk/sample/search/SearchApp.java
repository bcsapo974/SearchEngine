package uk.sample.search;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import uk.sample.search.model.Search;
import uk.sample.search.service.SearchEngine;

/**
 * Ravn Backend Rule Recruitement Excercise: basic search engine
 * 
 */

public class SearchApp {

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            if (Files.exists(Paths.get(args[0]))) {
                Search search = new Search();
                SearchEngine searchEngine = new SearchEngine();
                search.setArticleList(searchEngine.loadData(args[0]));
                if (args.length > 1) {
                    Arrays.stream(args).skip(1).forEach(search::addKeyword);
                    searchEngine.getResults(search).forEach(System.out::println);
                } else {
                    // if no keywords were given, the whole list were be printed
                    search.getArticleList().forEach(System.out::println);
                }
            } else {
                System.out.println("File not found");
            }
        } else {
            System.out.println("No arguments were given.");
        }

    }

}
