package java8.learn.chapter6.crawler;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Created by senyuanwang on 15/5/21.
 */
public class Crawler {
    public CompletableFuture<Set<String>> fetch(String url) {
        CompletableFuture<String> content = SimpleClient.get(url);
        return content.thenCompose(HtmlParser::parseOutHypeLinks);
    }

    public void fetch(String url, Consumer<Set<String>> func) {
        fetch(url).thenAccept(func);
    }

    public static void main(String[] args) throws InterruptedException {
        Crawler crawler = new Crawler();
        crawler.fetch("http://my.oschina.net/u/922297/blog", set -> {
            System.out.println(set);
        });

        ForkJoinPool.commonPool().awaitTermination(1, TimeUnit.MINUTES);
    }
}
