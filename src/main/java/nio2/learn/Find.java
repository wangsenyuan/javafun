package nio2.learn;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Find {
    public static class Finder extends SimpleFileVisitor<Path> {
        private int numMatches; // defaults to 0

        private PathMatcher matcher;

        Finder(String language, String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher(language + ":" +
                    pattern);
        }

        void find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                System.out.println(file);
            }
        }

        int getMatches() {
            return numMatches;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                                                 BasicFileAttributes attrs) {
            find(dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            find(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException ioe) {
            System.err.println(ioe);
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("usage: java Find path [-r] pattern");
            return;
        }
        Path startingDir = Paths.get(args[0]);
        String language = "glob";
        String pattern = args[1];
        if (args.length > 2 && args[1].equals("-r")) {
            language = "regex";
            pattern = args[2];
        }
        Finder finder = new Finder(language, pattern);
        Files.walkFileTree(startingDir, finder);
        System.out.printf("Number of matches: %d%n", finder.getMatches());
    }
}