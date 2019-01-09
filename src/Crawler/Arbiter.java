package Crawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Mosi on 2/13/2017.
 */
public class Arbiter implements Runnable {

    public static int flag = 0;
    public static int PageNumber = 0;
    public static ArrayList<Host> hosts;

    public static synchronized Link getDownloadLink() {
        //System.out.println("Entered getDownloadLink");
        Link link = null;
        try {
            int location = flag % hosts.size();
            flag++;
            if (hosts.get(location).links != null) {
                link = hosts.get(location).links.take();
            }
            return link;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////
    public static synchronized boolean addDownloadLink(String url, int depth) {
        if (UrlChecker.checked_URLs.contains(url)) {
            //old link and old host
            System.out.println("old link with old host " + url);
            return false;
        } else {
            String hostName = getHost(url);
            if (hostName != null) {
                Host host = new Host(hostName);
                int flag = 0;
                for (int i = 0; i < hosts.size(); i++) {
                    if (hosts.get(i).hostName.equals(host.hostName)) {
                        host = hosts.get(i);
                        //
                        System.out.println("new link with old host added : " + url);
                        int hostLocation = hosts.indexOf(host);
                        Link link = new Link(url, depth);
                        hosts.get(hostLocation).links.add(link);
                        flag++;
                        return true;
                    }
                }
                if (flag != 0) {
                    //new link and old host
                } else {
                    // TODO: 12/15/2018 this comment should be remove
//                    System.out.println("new link with new host added : " + url);
//                    //new link and new host
//                    Link link = new Link(url, depth);
//                    host.links.add(link);
//                    hosts.add(host);
//                    return true;
                }
            }
            return false;
        }

    }

    ///////////////////
    @Override
    public void run() {
        System.out.println("- Arbiter Is Run ...");
        hosts = new ArrayList<>();
        Collections.synchronizedList(hosts);
    }

    //
    /////////////////////////////////////////
    public static String getHost(String url) {
        URI uri;
        try {
            url = url.replace(" ", "");///////////////////////
            uri = new URI(url);
            return uri.getHost();
        } catch (URISyntaxException e) {
            //e.printStackTrace();
        }
        return null;
    }
    //
}