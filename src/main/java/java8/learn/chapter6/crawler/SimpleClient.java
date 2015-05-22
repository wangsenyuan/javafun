package java8.learn.chapter6.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.CompletableFuture;

/**
 * Created by senyuanwang on 15/5/21.
 */
public class SimpleClient {

    public static CompletableFuture<String> get(String url) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        CloseableHttpClient httpclient = HttpClients.createDefault();
                        HttpGet httpGet = new HttpGet(url);
                        CloseableHttpResponse response = httpclient.execute(httpGet);
                        try {
                            HttpEntity entity = response.getEntity();
//                            EntityUtils.consume(entity);
                            String content = EntityUtils.toString(entity);

                            return content;
                        } finally {
                            response.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
    }
}