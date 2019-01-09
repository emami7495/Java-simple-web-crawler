package Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownloadImages implements Runnable {
    public static Set<String> checked_images = new HashSet<>();

    private String webSiteURL;

    //The path of the folder that you want to save the images to
    private static final String folderPath = "C:\\Users\\Mostafa\\Desktop\\Java\\Crawler\\images\\";

    public DownloadImages(String webSiteURL) {
        this.webSiteURL = webSiteURL;
    }

    private static void getImages(String src) throws IOException {

        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + "_" + String.valueOf(System.currentTimeMillis()+".jpg")));

        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();

    }

    @Override
    public void run() {
        try {

            //Connect to the website and get the html
            Document doc = Jsoup.connect(webSiteURL).get();

            //Get all elements with img tag ,
            Elements img = doc.getElementsByTag("img");

            for (Element el : img) {

                //for each element get the srs url
                String src = el.absUrl("src");

                System.out.println("Image Found!");
                System.out.println("src attribute is : " + src);
                if (checked_images.contains(src)) {
                } else {
                    getImages(src);
                    checked_images.add(src);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("There was an error");
        }
        Thread.currentThread().stop();
    }
}