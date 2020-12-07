package localApplication;

import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlParser {

    /**
     * Html header to to write in the start of the output html file
     */
    public static final String HTML_HEADER = "<html>\n\t<head>\n\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"><title>OCR</title>\n\t</head>\n\t<body>\n";
    /**
     * Html footer to to write in the end of the output html file
     */
    public static final String HTML_FOOTER = "\t</body>\n</html>";
    /**
     * HTML tags to start paragraph and image in out put file
     */
    public static final String HTML_START_PARAGRAPH_AND_IMAGE = "\t\t<p>\n" + "\t\t<img src=";
    /**
     * HTML tags to end image and start parsed text in ht
     */
    public static final String HTML_AFTER_IMAGE_BEFORE_TEXT = "><br>\n\t\t";
    public static final String HTML_AFTER_TEXT = "\n\t\t</p>\n";


    private File htmlFile;
    private String fileName;

    public File getHtmlFile() {
        return htmlFile;
    }

    public String getFileName() {
        return fileName;
    }

    public HtmlParser(String fileName) {
        this.fileName = fileName + ".html";
        this.htmlFile = new File(this.fileName);

    }

    public boolean initFile() {
        return this.writeConstantToFile(HTML_HEADER);
    }

    private boolean writeConstantToFile(String toWrite) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.htmlFile, true));
            bw.write(toWrite);
            bw.close();
            return true;
        } catch (IOException e) {
            System.out.println("problem with io");
            e.printStackTrace();
            return false;
        }
    }

    public boolean endFile() {
        return this.writeConstantToFile(HTML_FOOTER);
    }

    public boolean appendListOfUrlAndTextToHTML(List<Pair<String, String>> urlsToText) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.htmlFile, true));
            for (Pair<String, String> imageWithParse : urlsToText) {

                String paragraph = HTML_START_PARAGRAPH_AND_IMAGE + imageWithParse.getKey() +
                        HTML_AFTER_IMAGE_BEFORE_TEXT +
                        imageWithParse.getValue() +
                        HTML_AFTER_TEXT;
                bw.write(paragraph);
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("problem with io");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void parseListOfUrlAndTextToHTML(Map<String, String> urlsToText, String fileName) {
        File f = new File(fileName + ".html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.write(HTML_HEADER);
            for (Map.Entry<String, String> imageWithParse : urlsToText.entrySet()) {

                String paragraph = HTML_START_PARAGRAPH_AND_IMAGE + imageWithParse.getKey() +
                        HTML_AFTER_IMAGE_BEFORE_TEXT +
                        imageWithParse.getValue() +
                        HTML_AFTER_TEXT;
                bw.write(paragraph);
            }
            bw.write(HTML_FOOTER);
            bw.close();
        } catch (IOException e) {
            System.out.println("problem with io");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser("temp");
        Map<String, String> input = new HashMap<>();
        input.put("\"http://ct.mob0.com/Fonts/CharacterMap/ocraextended.png\"", "...TEXT THAT WAS PARSED...");
        HtmlParser.parseListOfUrlAndTextToHTML(input, "output");
    }


}
