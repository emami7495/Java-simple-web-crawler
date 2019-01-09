package Crawler;

import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Mosi on 3/5/2017.
 */
public class Writer implements Runnable {
    public Document document;
    public int number;
    public String downloadLink;

    public Writer(Document document, int number, String downloadLink) {
        this.document = document;
        this.number = number;
        this.downloadLink = downloadLink;
    }

    @Override
    public void run() {
        try {
            String html = "<MosiCrawler>" +
                    "<MosiCrawler_Url>" + downloadLink + "</MosiCrawler_Url>" +
                    "<MosiCrawler_Title>" + document.title() + "</MosiCrawler_Title>" +
                    "<MosiCrawler_Body>" + document.body().text() + "</MosiCrawler_Body>" +
                    "</MosiCrawler>";
            saveToFile(html);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    //
    /////////////////////////////////////////
    private void saveToFile(String html) {

        try {
            ////////////////////
            File file = new File(Configs.filesPath + "downloaderNumber_" + (number + 1) + ".txt");
            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            ////////////////////////////////////////////
            //Here true is to append the content to file
            FileWriter fw = new FileWriter(file, true);
            //BufferedWriter writer give better performance
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(html);
            bw.write("\n");
            //Closing BufferedWriter Stream
            bw.close();
            //
            System.out.println(number + " saved to file");
            //
            Thread.currentThread().stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
