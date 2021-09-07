package helpers;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("unused")
public class Files {

    /**
     * In case you need to know where the execution is occurring (so that you can do some other things)
     *
     * @return - the path to get to the current directory
     */
    public static String getLocalPath() {
        File f = new File("");
        return f.getAbsolutePath();
    }

    /**
     * @param resource - Something that has been found using getElement?
     * @param fileName - relative to the current directory
     * @throws IOException - If the file can't be written to
     * @throws NullPointerException - if the filename that you give it is null
     */
    public static void writeOutFileAsHTML(Document resource, String fileName) throws IOException {
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, resource.outerHtml(), "UTF-8");
    }

    /**
     * @param resource - some string
     * @param fileName - relative to the current directory
     * @throws IOException - If the file can't be written to
     * @throws NullPointerException - if the filename that you give it is null
     */
    public static void writeOutFile(String resource, String fileName) throws IOException {
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, resource, "UTF-8");
    }

    /**
     * Wraps the attempt to open the file in a catch. The stack will be dumped, but the exception is not propagated.
     *
     * @param fileName - relative to the current directory
     * @return - the contents of the file as a String
     */
    public static String readFileAsString(String fileName) {
        File file = new File(fileName);
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Wraps the attempt to open the file in a catch. The stack will be dumped, but the exception is not propagated.
     *
     * @param fileName - relative to the current directory
     * @return - the contents of the file as a Stream
     */
    public static InputStream readFileAsInputStream(String fileName) {
        File file = new File(fileName);
        try {
            return FileUtils.openInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
