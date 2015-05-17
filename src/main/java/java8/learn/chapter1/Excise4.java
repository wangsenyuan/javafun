package java8.learn.chapter1;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by senyuanwang on 15/4/19.
 */
public class Excise4 {

    public static void main(String[] args) {
        String rootPath = "/Users/senyuanwang/IdeaProjects/ALG/src/main";

        File root = new File(rootPath);
        if (!root.exists()) {
            System.out.println("not a valid path " + rootPath);
            return;
        }

        List<File> files = new LinkedList<>();
        allJavaFiles(root, files);

        files.sort((a, b) -> {
            String pathA = a.getAbsolutePath();
            String pathB = b.getAbsolutePath();
            return pathA.compareTo(pathB);
        });

        files.forEach(System.out::println);
    }

    public static void allJavaFiles(File dir, List<File> leafFiles) {
        if (!dir.isDirectory()) {
            if (dir.getName().endsWith(".java")) {
                leafFiles.add(dir);
            }
            return;
        }

        dir.listFiles(file -> {
            allJavaFiles(file, leafFiles);
            return true;
        });
    }
}
