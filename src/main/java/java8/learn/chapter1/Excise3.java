package java8.learn.chapter1;

import java.io.File;

/**
 * Created by senyuanwang on 15/4/19.
 */
public class Excise3 {

    public static void main(String[] args) {
        String rootPath = "/Users/senyuanwang/IdeaProjects/ALG/src/main/java/java8/learn/chapter1";

        File root = new File(rootPath);
        if (!root.exists()) {
            System.out.println("not a valid path " + rootPath);
            return;
        }

        String suffix = ".java";
        File[] files = root.listFiles((File dir, String name) -> {
            if (name.endsWith(suffix)) {
                return true;
            } else {
                return false;
            }
        });

        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
