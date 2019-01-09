package Crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Mosi on 3/5/2017.
 */
public class Parser implements Runnable {
    public int depth;
    public Document document;

    public Parser(Document document, int depth) {
        this.document = document;
        this.depth = depth;
    }

    @Override
    public void run() {

        ///////////////////////////////////////////////
        Elements elements = document.select("a[href]"); // a with href
        HashSet<String> allLinks = new HashSet<>();
        //
        ArrayList<String> links = new ArrayList<>();
        ///////////////////////////////////////////
        for (int i = 0; i < elements.size(); i++) {
            allLinks.add(elements.get(i).attr("abs:href"));
        }
        // Creating a List of HashSet elements
        for (String link : allLinks) {
            links.add(link);
        }
        //
        //todo increase performance by remove arraylist
        for (String link : links) {
            if (!UrlChecker.checkedURL(link)) {
                Arbiter.addDownloadLink(link, depth);
            }
        }
        //
        Thread.currentThread().stop();
    }
}
