package java7.concurrent.practice;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by senyuanwang on 15/5/27.
 */
public class FileMock {
    private String[] content;
    private int index;

    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                sb.append((char) indice);
            }
            content[i] = sb.toString();
        }
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        if (this.hasMoreLines()) {
            Logger.getGlobal().fine("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }
}
