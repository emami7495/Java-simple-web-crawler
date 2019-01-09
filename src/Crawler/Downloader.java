package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Mosi on 2/13/2017.
 */
public class Downloader implements Runnable {//fetcher
    public int number;
    public int depth;
    //

    public Downloader(int number, int depth) {
        this.number = number;
        this.depth = depth;
    }

    @Override
    public void run() {
        //System.out.println("downloader " + number + " is run");
        while (Configs.maxDepth >= depth && Configs.maxPageNumber >= Arbiter.PageNumber) {
            //System.out.println("enterd while");
            //
            Link downloadLink = Crawler.arbiter.getDownloadLink();
            if (downloadLink.depth <= Configs.maxDepth) {
                System.out.println("download link : " + downloadLink.getLink());
                try {
                    while (downloadLink == null) {
                        System.out.println(number);
                        Thread.sleep(100);
                        downloadLink = Crawler.arbiter.getDownloadLink();
                    }
                    ///////////////////
                    Document document = Jsoup.connect(downloadLink.getLink())
                            .timeout(Configs.timeOut)
                            .get();
                    ///////////////////
                    DownloadImages downloadImages = new DownloadImages(downloadLink.link);
                    Thread downloadImagesThread = new Thread(downloadImages);
                    downloadImagesThread.start();
                    ///////////////////
                    Parser parser = new Parser(document, depth);
                    Thread parserThread = new Thread(parser);
                    parserThread.start();
                    ////////////////////
                    Writer writer = new Writer(document, number, downloadLink.getLink());
                    Thread writerThread = new Thread(writer);
                    writerThread.start();
                    //
                    SaveDownloadedLinks saveDownloadedLinks = new SaveDownloadedLinks(downloadLink.getLink());
                    Thread thread = new Thread(saveDownloadedLinks);
                    thread.start();
                    //
                    Arbiter.PageNumber++;
                    System.out.println(Arbiter.PageNumber + " page downloaded");
                    //
                } catch (IOException | InterruptedException e) {
                    //e.printStackTrace();
                }
                //
            } else {
                System.out.println("Depth is more than Configs max depth");
            }
        }
        Thread.currentThread().stop();
    }
    ///////////////////////////////////////////////////////////
}
