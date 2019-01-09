package Crawler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mosi on 3/5/2017.
 */
public class UrlChecker {
    public static Set<String> checked_URLs;

    public UrlChecker() {
        checked_URLs = new HashSet<>();
    }

    public static synchronized boolean checkedURL(String url) {
        if (checked_URLs.contains(url)) {
            //if exist
            return true;
        } else {
            //if not exist
            return false;
        }
    }
    /////////////////////
}
