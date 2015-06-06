package nio2.learn;

/**
 * Created by senyuanwang on 15/6/2.
 */

import java.io.IOException;
import java.nio.file.*;

public class PathAttrib {
    public static void main(String[] args) throws IOException {
        boolean isACL = false;
        boolean isDOS = false;

        FileSystem defFS = FileSystems.getDefault();
        for (String fileAttrView : defFS.supportedFileAttributeViews()) {
            System.out.printf("Default file system supports: %s%n", fileAttrView);
            if (fileAttrView.equals("acl"))
                isACL = true;
            if (fileAttrView.equals("dos"))
                isDOS = true;
        }
        System.out.println();

        if (args.length != 1) {
            System.err.println("usage: java PathAttrib path-object");
            return;
        }

        Path path = Paths.get(args[0]);

        // Output basic attributes, which are always supported.

        System.out.println("Basic attributes:");
        String[] attrNames =
                {
                        "lastModifiedTime",
                        "lastAccessTime",
                        "creationTime",
                        "size",
                        "isRegularFile",
                        "isDirectory",
                        "isSymbolicLink",
                        "isOther", // something other that a regular file, directory, or
                        // symbolic link
                        "fileKey"  // an object that uniquely identifies the given file, or
                        // null when a file key is not available.
                };
        for (String attrName : attrNames)
            System.out.printf("%s: %s%n", attrName,
                    Files.getAttribute(path, "basic:" + attrName));
        System.out.println();

        // Output ACL owner attribute when this view is supported.

        if (isACL) {
            System.out.println("ACL attributes:");
            System.out.printf("Owner: %s%n%n",
                    Files.getAttribute(path, "acl:owner"));
        }

        // Output DOS attributes when this view is supported.

        if (isDOS) {
            System.out.println("DOS attributes:");
            attrNames = new String[]{"readonly", "hidden", "system", "archive"};
            for (String attrName : attrNames)
                System.out.printf("%s: %s%n", attrName,
                        Files.getAttribute(path, "dos:" + attrName));
            System.out.println();
        }
    }
}