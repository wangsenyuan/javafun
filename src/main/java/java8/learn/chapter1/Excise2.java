package java8.learn.chapter1;

import java.io.File;

/**
 * Created by senyuanwang on 15/4/19.
 */
public class Excise2 {

    public static void main(String[] args) {
        String rootPath = "/Users/senyuanwang/IdeaProjects/ALG/src/main";

        File root = new File(rootPath);
        if (!root.exists()) {
            System.out.println("not a valid path " + rootPath);
            return;
        }

        listDir(root, 0);
    }

    public static boolean listDir(File root, int level) {
        if (root.isDirectory()) {
            for (int i = 0; i < level; i++) {
                System.out.print("--");
            }
            System.out.println(root.getName());
            root.listFiles(file -> listDir(file, level + 1));
            return true;
        }
        return false;
    }
}
