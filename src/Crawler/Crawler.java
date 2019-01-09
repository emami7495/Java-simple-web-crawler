package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by Mosi on 2/13/2017.
 */
public class Crawler {
    static Arbiter arbiter;

    public static void main(String[] args) {
        final int depth = 1;
        //
        Configs configs = new Configs();
        UrlChecker urlChecker = new UrlChecker();
        arbiter = new Arbiter();
        //
        Thread thread = new Thread(arbiter);
        thread.start();
        //
        firstInitial();
        //
        Executor executor = Executors.newFixedThreadPool(Configs.donwloaderNumber);
        //
        for (int i = 0; i < Configs.donwloaderNumber; i++) {
            Downloader downloader = new Downloader(i, depth);
            executor.execute(downloader);
        }
        //
        SaveProcessingQueues saveProcessingQueues = new SaveProcessingQueues();
        Thread saveProcessingQueuesThread = new Thread(saveProcessingQueues);
        saveProcessingQueuesThread.start();
    }

    ////////////////////////////////////
    private static void firstInitial() {
        for (int i = 0; i < Configs.seedsUrl.size(); i++) {
            String str = getHost(Configs.seedsUrl.get(i));
            if (str != null) {
                Host host = new Host(str);
                Arbiter.hosts.add(host);
            }
        }
        for (int i = 0; i < Configs.seedsUrl.size(); i++) {
            Arbiter.addDownloadLink(Configs.seedsUrl.get(i), 1);
        }

        ///
        try {
            for (int i = 0; i < Configs.seedsUrl.size(); i++) {
                ///////////////////
                Document document = Jsoup.connect(Configs.seedsUrl.get(i))
                        .timeout(Configs.timeOut)
                        .get();
                //
                Parser parser = new Parser(document, 1);
                Thread parserThread = new Thread(parser);
                parserThread.start();
                //
                Writer writer = new Writer(document, i, Configs.seedsUrl.get(i));
                Thread writerThread = new Thread(writer);
                writerThread.start();
                //
                Arbiter.PageNumber++;
                System.out.println(Arbiter.PageNumber + " page downloaded");
            }
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////
    private static String getHost(String url) {
        URI uri;
        try {
            url = url.replace(" ", "");//////////////////////
            uri = new URI(url);
            return uri.getHost();
        } catch (URISyntaxException e) {
            //e.printStackTrace();
        }
        return null;
    }
    //
}