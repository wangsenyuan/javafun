package nio2.learn;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathAttribV4 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java PathAttrib path-object");
            return;
        }

        Path path = Paths.get(args[0]);
        FileStore fs = Files.getFileStore(path);
        if (fs.type().equals("NTFS")) {
            String[] attrNames =
                    {
                            "totalSpace",
                            "usableSpace",
                            "unallocatedSpace",
                            "volume:vsn",
                            "volume:isRemovable",
                            "volume:isCdrom"
                    };
            for (String attrName : attrNames)
                System.out.println(fs.getAttribute(attrName));
        }
    }
}
