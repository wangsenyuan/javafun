package nio2.learn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.TreeMap;

public class PathAttribV3 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java PathAttrib path-object");
            return;
        }

        Path path = Paths.get(args[0]);

        BasicFileAttributes attrs;
        attrs = Files.readAttributes(path, BasicFileAttributes.class);

        System.out.printf("Basic Attributes for %s...%n%n", path);

        System.out.printf("creationTime: %s%n", attrs.creationTime());
        System.out.printf("fileKey: %s%n", attrs.fileKey());
        System.out.printf("isDirectory: %b%n", attrs.isDirectory());
        System.out.printf("isOther: %b%n", attrs.isOther());
        System.out.printf("isRegularFile: %b%n", attrs.isRegularFile());
        System.out.printf("isSymbolicLink: %b%n", attrs.isSymbolicLink());
        System.out.printf("lastAccessTime: %s%n", attrs.lastAccessTime());
        System.out.printf("lastModifiedTime: %s%n", attrs.lastModifiedTime());
        System.out.printf("size: %d%n", attrs.size());
        System.out.println();

        Map<String, Object> attrMap = new TreeMap<>(Files.readAttributes(path,
                "*"));
        for (Map.Entry<String, Object> entry : attrMap.entrySet())
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());
    }
}