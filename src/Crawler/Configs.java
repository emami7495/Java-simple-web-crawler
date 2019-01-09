package Crawler;

import java.util.ArrayList;

/**
 * Created by Mosi on 3/5/2017.
 */
public class Configs {
    //
    public static int fileNumbers = 10;// for indexer
    //
    public static final int timeOut = 10000;
    public static final int timeToSaveProcessingQueues = 60000;
    ///
    public static final int donwloaderNumber = 20;
    public static final int maxPageNumber = 5000;
    public static final int maxDepth = 500;
    public static String filesPath = "C:\\Users\\Mostafa\\Desktop\\Java\\Crawler\\WebPagesFiles\\" ;
    public static ArrayList<String> seedsUrl;

    public Configs() {
        seedsUrl = new ArrayList<>();
        seedsUrl.add("https://www.digikala.com/");
    }
}
