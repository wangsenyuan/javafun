package java8.learn.chapter6.crawler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Created by senyuanwang on 15/5/21.
 */
public class HtmlParser {
    private static void parseContentRecu(String content, Set<String> result) {
        if (content.isEmpty()) {
            return;
        }
        int indexOfNextTagA = content.indexOf("<a");
        if (indexOfNextTagA < 0) {
            return;
        }
        int indexOfEndTagA = content.indexOf("</a>", indexOfNextTagA);

        if (indexOfEndTagA < 0) {
            return;
        }

        String hypeLink = content.substring(indexOfNextTagA, indexOfEndTagA + 4);
        result.add(hypeLink);
        parseContentRecu(content.substring(indexOfEndTagA + 4), result);
    }

    public static Set<String> parseContent(String content) {
        Set<String> result = new HashSet<>();
        parseContentRecu(content, result);
        return result;
    }

    public static CompletableFuture<Set<String>> parseOutHypeLinks(String content) {
        return CompletableFuture.supplyAsync(() ->
                        parseContent(content)
        );
    }

}
